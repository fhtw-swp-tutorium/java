#language: en

Business Need: Observer pattern implementations should be structurally correct

  Scenario Outline:
    Given a list of all subjects
    Then this list must not be empty

  Scenario: Subjects should provide method to add an observer
    Then there should be a method to add an observer
    And this method must only accept an interface


  Scenario: Subjects should provide method to remove an observer
    Then there should be a method to remove an observer
    And this method must only accept an interface


  Scenario: Subjects should provide method to update all observer
    Then there should be a method to update all observers
    And this method must not accept any parameters

