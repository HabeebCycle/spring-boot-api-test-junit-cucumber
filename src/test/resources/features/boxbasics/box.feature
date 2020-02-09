Feature: Treasure box functionalities

  Background: The box is either empty, or has some treasures. All add, remove are take into consideration

  Scenario: Putting one treasure in the box when it is initially empty
    Given the box is empty
    When 1 Chain is put into the box
    Then the box should contain only 1 Chain treasure

  Scenario: Putting one treasure in the box when it has some treasures in it
    Given the box is not empty and add 5 Bronze
    When 4 Jewel is put into the box
    Then the box should contain only 10 treasure

  Scenario: Empty the box when it has some treasures in it
    Given the box is not empty and add 4 treasure
    When the box is emptied
    And 10 treasure Gold is put into the box
    Then the box should contain only 10 Gold treasure


  Scenario: Putting different treasure in the box when it is initially empty
    Given the box is emptied
    When 10 treasure Silver is put into the box
    And 20 treasure Perl is put into the box
    Then the box should contain only 30 treasure
    And the box should contain only 10 Silver treasure
    Then the box should contain only 20 Perl treasure