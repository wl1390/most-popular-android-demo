# News Reader
News Reader is simple app to hit the NY Times Most Popular Articles API and show a list of articles, that shows details when items on the list are tapped (a typical master/detail app).

We'll be using the most viewed section of this API.

[http://api.nytimes.com/svc/mostpopular/v2/mostviewed/{section}/{period}.json?api-key=sample-key](http://api.nytimes.com/svc/mostpopular/v2/mostviewed/{section}/{period}.json?api-key=sample-key)
To test this API, you can use all-sections for the section path component in the URL above and 7 for period (available period values are 1, 7 and 30, which represents how far back, in days, the API returns results for).

[http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=sample-key](http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=sample-key)

Note: App icon generated using [https://romannurik.github.io/AndroidAssetStudio](https://romannurik.github.io/AndroidAssetStudio)

### Requirements
- Android Studio
- Java 8 

### API Key 

An API key is necessary to successfully connect to the [API](https://developer.nytimes.com/signup) that the app uses. Once an API key has been aquired, change the `API_KEY` property in `com.android.nytimes.service.UrlConstants` and run the app.

## App Architecture

This sample follows a basic MVP architecture

### Model
  It is an interface responsible for managing data. 

### View Layer
* A main activity that handles navigation between the fragments.
* A fragment to display the list of articles.(ArticleListFragmentView and ArticleListFragment)
* A fragment to display a article detail( ArticleDetailFragment : Basically it has a webview, that loads the url in it)

### Presentation layer
* Presenter is the middle-man between the View and Model. It encapsulates all the Presentation logic. The presenter is responsible for querying the model and updating the view, reacting to user interactions updating the model.

## Getting Started

This repository implements the following quality gates:

![Build Pipeline](./jenkins/pipeline.png "Build Pipeline")

- Static code checks: running [lint](https://developer.android.com/studio/write/lint) to check the code for any issues. Static code analysis using [SonarQube](https://www.sonarqube.org/)
- Unit testing: running the [unit tests](https://developer.android.com/training/testing/)
- Code coverage: generating code coverage reports using the [JaCoCo gradle plugin](https://docs.gradle.org/current/userguide/jacoco_plugin.html)
- Functional testing: running the functional tests using [Appium](https://github.com/appium/appium-desktop/releases)
- Beta distribution: distributing the mobile app using [Fabric](https://fabric.io)

These steps can be run manually or using a Continous Integration tool such as [Jenkins](https://jenkins.io/). More information about setting up Jenkins and SonarQube can be found here [https://github.com/omerio/mobile-devops-infra](https://github.com/omerio/mobile-devops-infra)

### Checkout the Code

Checkout and run the code
```bash
git clone git@github.com:omerio/most-popular-android.git
cd most-popular-android
```

### Major Libraries / Tools

| Category                        	| Library/Tool   	| Link                                                       	|
|---------------------------------	|----------------	|------------------------------------------------------------	|
| Development                     	| Android - Java 	| https://developer.android.com/guide/                       	|
| Build & Dependencies Management 	| Gradle         	| https://developer.android.com/studio/build/               	|
| Automate Build & Release        	| Fastlane       	| https://docs.fastlane.tools/getting-started/android/setup/ 	|
| Unit Testing                    	| JUnit          	| https://developer.android.com/training/testing             	|
| Code Coverage                   	| JaCoCo         	| https://docs.gradle.org/current/userguide/jacoco_plugin.html|
| Static Code Check               	| Gradle Lint    	| https://developer.android.com/studio/write/lint           	|
| Functional Testing              	| Appium         	| http://appium.io/downloads.html                            	|
| Beta Distribution               	| Fabric         	| https://fabric.io                                          	|
| Continous Integration            	| Jenkins         | https://jenkins.io/                                          	|
| Static Code Analysis Integration  | SonarQube       | https://www.sonarqube.org/                                    	|

## Fastlane Setup

To setup Fastlane please read the [README.md](./fastlane/README.md) file inside the `./fastlane` folder

## Linting

`./gradlew lint`

or using Fastlane:

`fastlane lint`

Linting results are available at `most-popular-android/app/build/reports/lint-results.html`

## Testing
Tests in Android are separated into 2 types:

### Local unit tests

Located at `most-popular-android/app/src/test/java/` - These are tests that run on your machine’s local Java Virtual Machine (JVM). Use these tests to minimize execution time when your tests have no Android framework dependencies or when you can mock the Android framework dependencies.

### Instrumented tests

Located at `most-popular-android/app/src/androidTest/java/` - These are tests that run on a hardware device or emulator. These tests have access to Instrumentation APIs, give you access to information such as the Context of the app you are testing, and let you control the app under test from your test code. Use these tests when writing integration and functional UI tests to automate user interaction, or when your tests have Android dependencies that mock objects cannot satisfy.

### Running the Unit Tests

Unit testing for Android applications is fully explained in the [Android documentation](https://developer.android.com/training/testing/). In this repository, jUnit test case has been written for Presenter

From the commandline run:

`./gradlew clean test`

or using Fastlane:

`fastlane tests`

Unit tests results are available at 

`most-popular-android/app/build/reports/tests/testDebugUnitTest/index.html`

From Android Studio

* Right Clicking on the Class and select "Run <test class>
* To see the coverage we have t the select "Run <test class> with Coverage"

## Test Coverage

The test coverage uses the [JaCoCo](http://www.eclemma.org/jacoco/) library

From the commandline

`./gradlew clean jacocoTestReport`

Test coverage results are available at 

`most-popular-android/app/build/reports/jacoco/jacocoTestReport/html/index.html`

## Functional Tests using Appium

To setup Appium and to run the functional tests follow the [README.md](./appium/README.md) inside the `./appium` folder

## Beta Distribution using Fabric

[Fabric](https://fabric.io) is a service for mobile app distribution, beta testing and crash reporting. Fastlane is used to deploy the app to Fabric, the settings are summarised [here](https://fabric.io/features/distribution). To start using Fabric signup for the service, then follow these steps:

1. Go to the [Settings](https://fabric.io/settings), click on Organization and create a new Organization
2. Click on the organization, copy the API Key and Build Secret
3. Configure the application as detailed [here](https://fabric.io/kits/android/crashlytics/install). Note this is already done in this app you just need to update the files below
3. Update the ./fastlane/Fastfile with these details:

```
# upload to Beta by Crashlytics
  crashlytics(
    api_token: "bfe46de5e3fasdfasdfaafd3cd0a692053bf6d",
    build_secret: "b5e444e36ba2f0a844f4fasdfasdfa4e9baf5bb19f804a7b34768d8ded"
  )
```  
4. Update the most-popular-android/app/src/main/AndroidManifest.xml with the Fabric API key shown on the [install](https://fabric.io/kits/android/crashlytics/install) page:
```
  <meta-data
    android:name="io.fabric.ApiKey"
    android:value="f5f26ce539a22aasdfadfba93162b6884444dd8af4cef" 
    />
```

4. From Android Studio generate a keystore for [signing](https://developer.android.com/studio/publish/app-signing) the release app. Click Build -> Generate Signed APK... -> Next -> Create New. Enter the same details as in the ./fastlane/Fastfile 

```
   properties: {
      "android.injected.signing.store.file" => "keystore.jks",
      "android.injected.signing.store.password" => "keystore_password",
      "android.injected.signing.key.alias" => "alias",
      "android.injected.signing.key.password" => "alias_password",
    }
``` 

5. To deploy the application to Fabric run `fastlane beta`

## TODO
* ~~Add SonarQube analysis~~
* Manage the API keys better

License
--------
Apache License, Version 2.0

http://www.apache.org/licenses/LICENSE-2.0
