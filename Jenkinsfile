pipeline {
  agent {
    label 'ios'
  }
  stages {
    stage('Lint') {
      steps {
        sh '''
export LANG=en_US.UTF-8
source ~/.bash_profile
bundle exec fastlane lint
'''
        // publish Android Lint results
        androidLint canComputeNew: false, defaultEncoding: '', healthy: '', pattern: 'app/build/reports/**/lint-results.xml', unHealthy: ''
      }
    }    
//     stage('Unit tests') {
//       steps {
//         sh '''
// export LANG=en_US.UTF-8
// source ~/.bash_profile
// bundle exec fastlane tests
// '''
//       }
//     }
    stage('Unit tests') {
      steps {
        sh './gradlew clean jacocoTestReport'
        jacoco( 
          execPattern: 'app/build/jacoco/*.exec',
          //classPattern: 'target/classes',
          //sourcePattern: 'src/main/java',
          exclusionPattern: '**/R.class,**/R$*.class,**/BuildConfig.*,**/Manifest*.*,**/*Test*.*,android/**/*.*'
        )
      }
    }
    // stage('Static analysis') {
    //   steps {
    //     sh './gradlew -PsonarHost=172.17.0.4:9000 sonarqube'
    //     sh './gradlew sonarqube -Dsonar.host.url=http://localhost:9000 -Dsonar.login=1f1d98cf48bded3cfc941e633bc8d8d3815f9c89'
    //   }
    // }
    // https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner+for+Jenkins
    stage('SonarQube analysis') {
      steps {
        withSonarQubeEnv('Sonarqube') {
          // requires SonarQube Scanner for Gradle 2.1+
          // It's important to add --info because of SONARJNKNS-281
          sh './gradlew --info sonarqube'
        }
            
      }
    }

    // https://docs.sonarqube.org/display/SONAR/Quality+Gates
    // https://jenkins.io/blog/2017/04/18/continuousdelivery-devops-sonarqube/    
    // https://jenkins.io/doc/pipeline/steps/sonar/#code-waitforqualitygate-code-wait-for-sonarqube-analysis-to-be-completed-and-return-quality-gate-status
    stage("SonarQube Quality Gate") {
      steps {
        timeout(time: 1, unit: 'HOURS') {
          waitForQualityGate abortPipeline: true
        }
      }
    }

    stage('Appium Tests') {
      steps {
        sh '''
./gradlew assembleDebug        
cd appium
virtualenv venv
. ./venv/bin/activate
pip install -r requirements.txt
behave
        '''
      }
    }

    stage('Deploy Beta') {
      steps {
        sh 'bundle exec fastlane beta'
      }
    }
  }
  // https://github.com/jenkinsci/pipeline-model-definition-plugin/wiki/Reporting-test-results-and-storing-artifacts
  post {
    always {
      //archive "build/**/*"
      archiveArtifacts allowEmptyArchive: true, artifacts: 'app/build/outputs/apk/debug/app-debug.apk,app/build/outputs/apk/release/app-release.apk'
      junit 'app/build/test-results/**/*.xml'
    }
  }
}