from behave import *
from utils import animate, think, longthink

@given('I wait a few seconds for the network')
def step_impl(context):
    longthink()
    longthink()

@then('I should be able to scroll down without errors')
def step_impl(context):
    context.actor.swipeUpAndroid(completion=animate)
    
    think()
    
    
@then('I should be able to scroll up without errors')
def step_impl(context):
    context.actor.swipeDownAndroid(completion=animate)
    
    think()

@then('I click into the first article')
def step_impl(context):
    context.actor.clickInto(completion=animate)
    
    longthink()
