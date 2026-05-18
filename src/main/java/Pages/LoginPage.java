package Pages;

import Bases.BasePage;
import org.openqa.selenium.*;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By userName = By.id("user-name");
    private By password = By.id("password");
    private By loginBtn = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    @Step
    public LoginPage Load() {
        driver.get("https://www.saucedemo.com/");
        return this;
    }

    @Step("Login with username: {0}")
    public InventoryPage login(String user, String pass) {
        Load();
        SendKeys(userName, user);
        SendKeys(password, pass);
        ClickElement(loginBtn);

        // handleAlertIfPresent();
        handleAlertIfPresent();
        return new InventoryPage(driver);
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
        } catch (TimeoutException e) {
            return false; // Element didn't appear within the timeout
        }
    }

    public String getErrorMessage() {
        // Wait for it to be visible, then grab the text
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public boolean isLoginButtonDisplayed() {
        try {
            // Uses visibilityOfElementLocated to ensure the element is actually on the screen
            return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn)).isDisplayed();
        } catch (TimeoutException e) {
            // Returns false if the button does not appear within the wait time
            return false;
        }
    }

    public void handleAlertIfPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            System.out.println("Alert accepted.");
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present to accept.");
        }
    }
}



