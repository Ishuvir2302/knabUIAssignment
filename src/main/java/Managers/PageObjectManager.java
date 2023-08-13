package Managers;

import org.openqa.selenium.WebDriver;

import PageObjects.BoardDashboardPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;

public class PageObjectManager {

    private final WebDriver webDriver;
    private HomePage baordHome;
    private LoginPage loginPage;
    private BoardDashboardPage boardPage;

    public PageObjectManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public HomePage getHomePage() {
        return (baordHome == null) ? baordHome = new HomePage(webDriver) : baordHome;
    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage(webDriver) : loginPage;
    }

    public BoardDashboardPage getBoardPage() {
        return (boardPage == null) ? boardPage = new BoardDashboardPage(webDriver) : boardPage;
    }

}
