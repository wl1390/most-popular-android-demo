import os
from time import sleep

from utils import BaseTestCase


def before_all(context):
    # context.config.setup_logging()
    pass

def before_scenario(context, feature):
    context.actor = BaseTestCase()
    context.actor.setUp()
    context.hasCamera = False

def after_scenario(context, feature):
    sleep(1)
    context.actor.driver.save_screenshot("features/reports/screen_final.png")
    context.actor.tearDown()