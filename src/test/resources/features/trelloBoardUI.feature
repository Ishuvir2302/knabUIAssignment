#Author: Ishuvir singh
Feature: Use Trello website to manage boards
  So work can planned
  cards can be tracked
  Task can be priortises

  Background: 
    Given I Enter valid credentials to login trello
      | email                   | password     |
      | singh.ivsingh@gmail.com | Oneworld@123 |

  @order1
  Scenario: Trello board creation
  Given I clicked on create
  When I added board "project1" title with background
  And I selected create board
  Then New board is created with "project1" title
  
  @order2
  Scenario: Trello board deletion
  Given I selected "project1" board
  When I closed the board
  And I deleted the board
  Then I verify "project1" board deleted
  @order3
  Scenario: Verify card relocation
    Given I selected "Assignment" board
    And I created new card in ToDo
    When I moved card from ToDO to Doing
    Then I verify moved card in Doing

  @ScenarioOutline
  Scenario Outline: validate the creation & deletion of card list
    Given I selected "Assignment" board
    When I created new "<listName>" list in ToDo
    And I deleted created "<listName>" list
    Then I verify "<listName>" list is deleted

    Examples: 
      | listName |
      | Rejected     |
      | Defered  |
