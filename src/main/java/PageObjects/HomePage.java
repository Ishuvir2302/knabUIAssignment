package PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import Utilities.Wait;

public class HomePage {

    private final WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 5), this);
    }

    @FindBy(className = "home-sticky-container")
    private WebElement boardHomePage;

    @FindBy(className = "home-left-sidebar-container")
    private WebElement boardSideBar;

    @FindBy(xpath = "//button[@data-testid='header-create-menu-button']")
    private WebElement createButton;

    @FindBy(xpath = "//button[@data-testid='header-create-board-button']")
    private WebElement createBoardButton;

    @FindBy(xpath = "//h2[@title='Create board']")
    private WebElement createBoardTitle;

    @FindBy(id = "background-picker")
    private WebElement backGroundPicker;

    @FindBy(xpath = "//div[@id='background-picker']/ul[2]/li[1]")
    private WebElement firstBackGround;

    @FindBy(xpath = "//input[@data-testid='create-board-title-input']")
    private WebElement boardName;

    @FindBy(xpath = "//button[@data-testid='create-board-submit-button']")
    private WebElement publishBoardButton;

    @FindBy(xpath = "//h3[contains(text(),'YOUR WORKSPACES')]")
    private WebElement workspaceSection;

    @FindBy(xpath = "//li[@data-testid='create-board-tile']/preceding::li[1]")
    private WebElement selectBoard;

    @FindBy(xpath = "//div[@class='board-tile-details-name']")
    private List<WebElement> boardNameSection;

    public boolean defaultBoardsPageIsDisplayed() {
        Wait.untilElementIsClickable(webDriver, boardHomePage, 6L);
        boardHomePage.isDisplayed();
        boardSideBar.isDisplayed();
        createButton.isEnabled();
        return true;
    }

    public void clickCreateButton() {
        Wait.untilElementIsClickable(webDriver, createButton, 2L);
        createButton.click();
    }

    public void fillBoardDetails(String titleName) {
        Wait.untilElementIsClickable(webDriver, createBoardButton, 2L);
        createBoardButton.click();
        Wait.untilElementIsVisible(webDriver, createBoardTitle, 5L);
        backGroundPicker.isDisplayed();
        firstBackGround.click();
        boardName.sendKeys(titleName);

    }

    public void createBoard() {
        Wait.untilElementIsClickable(webDriver, publishBoardButton, 5l);
        publishBoardButton.click();
    }

    public void selectBoard() {
        Wait.untilElementIsVisible(webDriver, selectBoard, 5l);
        selectBoard.click();
    }

    public List<String> getBoardTitles() {
        List<String> attributeValues = new ArrayList<>();

        for (WebElement element : boardNameSection) {
            String values = element.getAttribute("title");
            attributeValues.add(values);

        }
        return attributeValues;

    }

}
