package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import Utilities.Wait;

public class LoginPage {

    private final WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 5), this);
    }

    @FindBy(className = "trello-main-logo")
    private WebElement trelloLogo;

    @FindBy(linkText = "Log in")
    private WebElement loginButton;

    @FindBy(className = "login-password-container")
    private WebElement loginPasswordContainer;

    @FindBy(id = "user")
    private WebElement usernameField;

    @FindBy(id = "login")
    private WebElement continueLogin;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-submit")
    private WebElement loginSubmit;

//    public void acceptCookiesPolicy() {
//        cookiesAccept.isDisplayed();
//        cookiesAccept.click();
//    }

    public boolean trelloLoginPageIsDisplayed() {
        trelloLogo.isDisplayed();
        usernameField.isDisplayed();
        continueLogin.isDisplayed();
        return true;
    }

    public void selectLoginButton() {
        Wait.untilElementIsVisible(webDriver, loginButton, 2L);
        loginButton.isEnabled();
        loginButton.click();
    }

    public void enterUsername(String username) {
        Wait.untilElementIsVisible(webDriver, usernameField, 2L);
        usernameField.isEnabled();
        usernameField.sendKeys(username);
        Wait.untilElementIsClickable(webDriver, continueLogin, 2L);
        continueLogin.click();
    }

    public void enterPassword(String password) {
        Wait.untilElementIsVisible(webDriver, passwordField, 2L);
        passwordField.isEnabled();
        passwordField.sendKeys(password);
        Wait.untilElementIsClickable(webDriver, loginSubmit, 2L);
        loginSubmit.click();
    }

    public void loginNow() {
        Wait.untilElementIsClickable(webDriver, loginSubmit, 3L);
        loginSubmit.click();
    }

}
