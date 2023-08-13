# knabUIAssignment

**Knab_selenium_BDD_framework**
***
Behavioural driven development UI automation framework is based on page factory approach using selenium, cucumber-java, testng, maven, cucumber reports 

**Tools and technologies used:**
***

* Language: Java 11
* Testing framework: Testng
* BDD framework: Cucumber jvm
* Automation tool: Selenium webdriver
* Build tool: maven
* Logging: log4j
* Headless browser: chrome, firefox
* Reporting: cucumber reporting
* CI: Github action

**Features of the framework**
***

* BDD framework using Cucumber-jvm. Feature files can be written easily using Given,When, Then etc.
* Browser(chrome/firefox/safari/edge) can be configured in configuration.property file
* Tests can run in headless browser (chrome & firefox) by passing a parameter at run time.
* Screenshot would be taken if any scenario failed and saved under /outputFiles folder.
* Html report gets generated after each test run and can be found test-output & cucumber report link, report generated in target/cucumberReport


* src/main/java: includes 
    1. Package:DataProviders inside src/main/java helps in reading configuration files to provide url, timeout, browser for test run.
    2. Package:Enums comprise of drivertypes, context, Environment classes
    3. Package:Managers comprise of driverManger(different driver for browser), pageObjectManager (managing pages),fileReaderManager(Config   file reader)
    4. Package:PageObjects comprise of pages(Home,restaurantList, restaurantDetail) using pagefactory model
    5. Package:Utilities wait, scenario managing, testmanaging utlities

* src/test/java: includes 
    1.Package:Runners comprise of testrunner managed with testNG
    2. Package:StepDefinations comprise of Hooks & trelloSteps(Code glued with feature file)

* src/test/resources: includes
    1. Folder: features comprise of feature file with mentioned scenario's
    2. cucumber.property comprises of enabling publishing of cucumber report

* config : comprise of cucumber properties (url, timeout, browser, env)

* target: Cucumber reports are generated in this path also cucumber report link is shared in test console


**Test report**
***

Cucumber reports are generated and also index.html , emailable reports are generated in target folders


**Installation**
***
If you want to run the code locally you can clone the repo  or open the project in code editor (eclipse or intellij )and run either using testng.xml or  mvn clean test command.
 

**Code Repository**
***
* Code can be pushed in any code repository github or gitlub or bitbucket 
* GitHub instructions-> 
1. Create repository in source control
2. Clone the repo
3. Put the code in clone repo
4. stage the code, commit with message
5. Push the code 
6. Verify the pushed code in source control 



**Setting up the project**
***
Install the maven locally and open cmd/terminal 

Navigate to framework path
$ cd knabUIAssignment

$ mvn clean install


** Scenario Automated **
***


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

    
**NOTES**
 ***
 User can uncomment the below headless browser command to run in headless browser available in src/main/java/Managers/AllDriverManager.java <br> 
     
     chromeOptions.addArguments("--headless", "--window-size=1644,868");
                       or 
      firefoxOptions.addArguments("--headless");
     
