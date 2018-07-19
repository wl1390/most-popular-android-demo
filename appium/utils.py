import os
import unittest
from appium import webdriver
from time import sleep
from appium.webdriver.common.touch_action import TouchAction


AVERAGE_NETWORK_TIME = 1.5

def think(duration=AVERAGE_NETWORK_TIME):
    sleep(duration)

def longthink():
    sleep(5)

def animate():
    sleep(1)


def getDriver():
    PATH = lambda p: os.path.abspath(
        os.path.join(os.path.dirname(__file__), p)
    )

    capabilities = {
        'platformName': 'android',
        'avd': 'Nexus_5X_API_25',
        'deviceName': 'Nexus_5X_API_25',
        # 'appWaitActivity': 'com.fab.fabwallet.ui.landing.LandingActivity, com.fab.fabwallet.ui.splash.SplashActivity',
        'app': PATH('../app/build/outputs/apk/debug/app-debug.apk'),
        'autoGrantPermissions': "true",
        'automationName': 'UiAutomator2'
    }
    return webdriver.Remote('http://localhost:4723/wd/hub', capabilities)

class BaseTestCase(unittest.TestCase):
    def setUp(self):
        self.driver = getDriver()
        self.action = TouchAction(self.driver)
        think()
        # self.dismissIntegrityChecks()

    def tearDown(self):
        self.driver.quit()

    def tapOnButton(self, id, completion=lambda: None):
        button = self.driver.find_element_by_accessibility_id(id)
        self.action.tap(button).perform()

        completion()

    def tapOnButtonByIndex(self, accessibility_id, index, completion=lambda: None):
        buttons = self.driver.find_elements_by_accessibility_id(accessibility_id)
        self.action.tap(buttons[index]).perform()

        completion()

    def fillText(self, element, text, completion=lambda: None, clear=False):
        if clear:
            element.clear()

        element.set_value(text)
        try:
            self.driver.hide_keyboard()
        except Exception as e:
            pass

        completion()

    def fillTextById(self, id, text, completion=lambda: None, clear=False):
        element = self.driver.find_element_by_accessibility_id(id)
        self.fillText(element, text, completion=completion, clear=clear)

    def fillTextByIndex(self, accessibility_id, index, text, completion=lambda: None, clear=False):
        elements = self.driver.find_elements_by_accessibility_id(accessibility_id)
        self.fillText(elements[index], text, completion=completion, clear=clear)

    def tapCheckBoxItem(self, accessibility_id, index, completion=lambda: None, clear=False):
        checkBoxes = self.driver.find_elements_by_accessibility_id(accessibility_id)

        self.action.tap(checkBoxes[index]).perform()

        completion()

    def swipeUpAndroid(self, completion=animate):
        # source = self.driver.find_element_by_id(sourceId)
        screen_size = self.driver.get_window_size()
        x = screen_size.get('width') / 2
        y = screen_size.get('height')
        # print(screen_size)
        #self.action.press(x=x, y=y).move_to(x=x, y=y/2).release().perform()
        # self.driver.find_element_by_android_uiautomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(someElement.instance(0));")
        self.driver.swipe(x, y - 10, x, y / 4, 1000)
        #self.driver.swipe(start_x, start_y, end_x, end_y, duration)

        completion()

    def swipeDownAndroid(self, completion=animate):
        screen_size = self.driver.get_window_size()
        x = screen_size.get('width') / 2
        y = screen_size.get('height')
        self.driver.swipe(x, y / 4, x, y - 10, 1000)

        completion()
    
    def clickInto(self,completion=animate):
        self.driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]").click()

        completion()

    def scroll(self, sourceId, destinationId, completion=animate):
        source = self.driver.find_element_by_id(sourceId)
        destination = self.driver.find_element_by_id(destinationId)
        self.driver.scroll(source, destination)

        completion()

    def swipeUp(self, completion=animate):
        self.driver.execute_script('mobile: scroll', {'direction': 'up'})

        completion()

    def swipeDown(self, completion=animate):
        self.driver.execute_script('mobile: scroll', {'direction': 'down'})

        completion()

    def dismissIntegrityChecks(self):
        try:
            self.tapOnButton('android:id/button1')
            self.dismissIntegrityChecks()

        except Exception as e:
            pass


    def runTest(self):
        pass
