package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.github.javafaker.Faker;

import Utilities.Wait;

public class BoardDashboardPage {
    private final WebDriver webDriver;
    String cardName;

    public BoardDashboardPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 5), this);
    }

    @FindBy(xpath = "//h1[@data-testid='board-name-display']")
    private WebElement boardNameHeading;

    @FindBy(xpath = "//button[@data-testid='view-switcher-button-more']")
    private WebElement boardDropdown;

    @FindBy(id = "board")
    private WebElement boardUI;

    @FindBy(xpath = "//h2[contains(@aria-label,'Your boards')]")
    private WebElement boardSideBar;

    @FindBy(xpath = "//a[contains(@aria-label,'(currently active)')]")
    private WebElement activeBoard;

    @FindBy(xpath = "//a[contains(@aria-label,'(currently active)')]/following::div[1]/div/button")
    private WebElement boardActionMenu;

    @FindBy(xpath = "//button[@title='Close board']")
    private WebElement closeBoard;

    @FindBy(xpath = "//button[@title='Close']")
    private WebElement confirmClose;

    @FindBy(xpath = "//button[@data-testid='close-board-delete-board-button']")
    private WebElement deleteBoard;

    @FindBy(xpath = "//button[@data-testid='close-board-delete-board-confirm-button']")
    private WebElement confirmDeleteBoard;

    @FindBy(xpath = "//div[@id='board']/div[1]//a[contains(@class,'open-card-composer')]")
    private WebElement createToDoCard;

    @FindBy(xpath = "//textarea[contains(@class,'list-card-composer-textarea')]")
    private WebElement cardTextName;

    @FindBy(xpath = "//input[@value='Add card']")
    private WebElement addCardButton;

    @FindBy(xpath = "//input[@value='Add card']/following::a[1]")
    private WebElement crossButton;

    @FindBy(xpath = "(//div[contains(@class,'list-cards')])[1]//span[contains(@class,'list-card-title js-card-name')]")
    private List<WebElement> todoCards;

    @FindBy(xpath = "(//div[contains(@class,'list-cards')])[2]//span[contains(@class,'list-card-title js-card-name')]")
    private List<WebElement> doingCards;

    @FindBy(xpath = "(//div[contains(@class,'js-list-content')])[2]")
    private WebElement doingSection;

    @FindBy(xpath = "//div[contains(@class,'js-add-list')]")
    private WebElement addList;

    @FindBy(xpath = "//input[@class='list-name-input']")
    private WebElement listTextName;

    @FindBy(xpath = "//input[@value='Add list']")
    private WebElement addListButton;

    @FindBy(xpath = "//input[@value='Add list']/following::a[1]")
    private WebElement listCrossButton;

    @FindBy(xpath = "(//textarea[contains(@class,'js-list-name-input')])[last()]")
    private WebElement lastListElementName;

    @FindBy(xpath = "(//a[contains(@class,'js-open-list-menu')])[last()]")
    private WebElement lastListElement;

    @FindBy(className = "js-close-list")
    private WebElement deleteList;

    public String getBoardName() {
        Wait.untilElementIsVisible(webDriver, boardNameHeading, 4L);
        return boardNameHeading.getText();
    }

    public boolean BoardsPageSectionDisplayed() {
        Wait.untilElementIsVisible(webDriver, boardUI, 6L);
        boardSideBar.isDisplayed();
        boardDropdown.isDisplayed();
        boardSideBar.isDisplayed();
        activeBoard.isSelected();
        return true;
    }

    public void closeBoard() {
        // Mouse hovering over select board in sidebar
        Actions actions = new Actions(webDriver);
        actions.moveToElement(activeBoard).perform();
        Wait.untilElementIsClickable(webDriver, boardActionMenu, 6L);
        boardActionMenu.click();
        Wait.sleep(2L);
        Wait.untilElementIsVisible(webDriver, closeBoard, 6L);
        closeBoard.click();
        Wait.untilElementIsVisible(webDriver, confirmClose, 6L);
        confirmClose.click();
    }

    public void deleteBoard() {
        Wait.untilElementIsVisible(webDriver, deleteBoard, 6L);
        deleteBoard.click();
        Wait.sleep(2L);
        Wait.untilElementIsClickable(webDriver, confirmDeleteBoard, 6L);
        confirmDeleteBoard.click();
    }

    public String addToDoCard() {
        Wait.untilElementIsVisible(webDriver, createToDoCard, 6L);
        createToDoCard.click();
        Wait.untilElementIsVisible(webDriver, cardTextName, 6L);
        Faker faker = new Faker();
        cardName = faker.name().name();
        cardTextName.sendKeys(cardName);
        Wait.untilElementIsClickable(webDriver, addCardButton, 6L);
        addCardButton.click();
        Wait.untilElementIsClickable(webDriver, crossButton, 6L);
        crossButton.click();
        return cardName;
    }

    public boolean checkCreatedCard() {
        String targetText = cardName;
        int index = iterateOverCard(todoCards);
        if (index != -1) {
            System.out.println("Index of element with text '" + targetText + "': " + index);
            return true;
        } else {
            System.out.println("Element with text '" + targetText + "' not found.");
            return false;
        }

    }

    public boolean checkMovedCard() {
        String targetText = cardName;
        int index = iterateOverCard(doingCards);
        if (index != -1) {
            System.out.println("Index of element with text '" + targetText + "': " + index);
            return true;
        } else {
            System.out.println("Element with text '" + targetText + "' not found.");
            return false;
        }

    }

    public int iterateOverCard(List<WebElement> cardSection) {
        String targetText = cardName;

        int targetIndex = -1;

        for (int i = 0; i < cardSection.size(); i++) {
            WebElement element = cardSection.get(i);
            if (element.getText().equals(targetText)) {
                targetIndex = i;
                break;
            }
        }
        return targetIndex;
    }

    public void moveCardToDoing() {

        String targetText = cardName;
        WebElement sourceElement = null;
        WebElement destinationElement = doingSection;

        for (int i = 0; i < todoCards.size(); i++) {
            WebElement element = todoCards.get(i);
            if (element.getText().equals(targetText)) {
                sourceElement = element;
            }
        }
        Wait.untilElementIsClickable(webDriver, sourceElement, 6L);
        Actions actions = new Actions(webDriver);
        actions.clickAndHold(sourceElement).moveToElement(destinationElement).release().build().perform();

    }

    public void addList(String listName) {
        Wait.untilElementIsVisible(webDriver, addList, 6L);
        addList.click();
        Wait.untilElementIsVisible(webDriver, listTextName, 6L);
        listTextName.sendKeys(listName);
        Wait.untilElementIsClickable(webDriver, addListButton, 6L);
        addListButton.click();
        Wait.untilElementIsClickable(webDriver, listCrossButton, 6L);
        listCrossButton.click();

    }

    public String getListText() {
        Wait.untilElementIsVisible(webDriver, lastListElementName, 6L);
        return lastListElementName.getText();
    }

    public void deleteList() {
        Wait.untilElementIsClickable(webDriver, lastListElement, 6L);
        lastListElement.click();
        Wait.untilElementIsVisible(webDriver, deleteList, 6L);
        deleteList.click();

    }

}
