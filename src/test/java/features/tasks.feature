#Author: Shreeti Lal

@tag
Feature: Mytheresa Technical QA search feature
  This Feature file is used to test the search feature in web Browser and API

  @tag1
  Scenario Outline: Search Feature in UI and API
    Given launch "<foodtoFork_URL>"
    When search for "<searchString>"
    Then verify the searchStatus for "<searchString>"
    When tested "<searchString>" with API URL 
    Then searchString and searchCount should match with previous search    

    Examples: 
      | foodtoFork_URL  					| searchString 					  |
      | https:\\www.food2fork.com | cookie monster cupcakes |
      #| https:\\www.food2fork.com | roasted chicken |
      
