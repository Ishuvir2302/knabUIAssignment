package StepDefinitions;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import PageObjects.BoardDashboardPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import Utilities.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class trelloSteps {
    private static final Logger log = LoggerFactory.getLogger(trelloSteps.class);
    String cardName;
    TestContext testContext;
    HomePage baordHome;
    LoginPage loginPage;
    BoardDashboardPage boardPage;

    public trelloSteps(TestContext context) {
        testContext = context;
        loginPage = testContext.getPageObjectManager().getLoginPage();
        baordHome = testContext.getPageObjectManager().getHomePage();
        boardPage = testContext.getPageObjectManager().getBoardPage();
    }

    @Given("I Enter valid credentials to login trello")
    public void i_enter_valid_credentials_to_login(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String username = data.get(0).get("email");
        String password = data.get(0).get("password");
        loginPage.selectLoginButton();
        loginPage.trelloLoginPageIsDisplayed();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.loginNow();
    }

    @Given("I clicked on create")
    public void i_clicked_on_create() {
        Assert.assertTrue(baordHome.defaultBoardsPageIsDisplayed());
        baordHome.clickCreateButton();
    }

    @When("I added board {string} title with background")
    public void i_added_board_title_with_background(String title) {
        baordHome.fillBoardDetails(title);
    }

    @When("I selected create board")
    public void i_selected_create_board() {
        baordHome.createBoard();
    }

    @Then("New board is created with {string} title")
    public void new_board_is_created_with_title(String title) {
        Assert.assertTrue(boardPage.BoardsPageSectionDisplayed());
        log.info("Board is created with title:" + boardPage.getBoardName());
        Assert.assertEquals(boardPage.getBoardName(), title);

    }

    @Given("I selected {string} board")
    public void i_selected_board(String title) {
        baordHome.selectBoard();
        Assert.assertTrue(boardPage.BoardsPageSectionDisplayed());
        log.info("Board is selected with title:" + boardPage.getBoardName());
        Assert.assertEquals(boardPage.getBoardName(), title);
    }

    @When("I closed the board")
    public void i_closed_the_board() {
        boardPage.closeBoard();
        log.info("Closing the board from sidebar");
    }

    @When("I deleted the board")
    public void i_deleted_the_board() {
        boardPage.deleteBoard();
    }

    @Then("I verify {string} board deleted")
    public void i_verify_board_deleted(String title) {
        boolean containsText = false;

        for (String value : baordHome.getBoardTitles()) {
            if (value.contains(title)) {
                log.info("Board Names::" + value);
                containsText = true;
                break;
            }
        }

        Assert.assertFalse(containsText, "Board is not found with" + title + ":Deleted");
    }

    @Given("I created new card in ToDo")
    public void i_created_new_card_in_to_do() {
        cardName = boardPage.addToDoCard();
        log.info("New Card is created: " + cardName);
        Assert.assertTrue(boardPage.checkCreatedCard(), "Created Card not found");
    }

    @When("I moved card from ToDO to Doing")
    public void i_moved_card_from_to_do_to_doing() {
        boardPage.moveCardToDoing();
        log.info("Moved card from ToDo to doing");
    }

    @Then("I verify moved card in Doing")
    public void i_verify_moved_card_in_doing() {
        Assert.assertTrue(boardPage.checkMovedCard(), "Moved card not found in doing");
    }

    @When("I created new {string} list in ToDo")
    public void i_created_new_list_in_to_do(String listName) {
        boardPage.addList(listName);
        Assert.assertEquals(boardPage.getListText(), listName);
        log.info("New List is created with Name" + boardPage.getListText());
    }

    @When("I deleted created {string} list")
    public void i_deleted_created_list(String string) {
        boardPage.deleteList();
    }

    @Then("I verify {string} list is deleted")
    public void i_verify_list_is_deleted(String listName) {
        Assert.assertNotEquals(boardPage.getListText(), listName);
        log.info("List is deleted");
    }

}
