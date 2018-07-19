@ios
Feature: News reader Basic tests
  Scenario: Read the news
  Given I wait a few seconds for the network
    Then I should be able to scroll down without errors
    Then I should be able to scroll up without errors
    Then I click into the first article
