package Pages.CheckoutPages;

import Bases.BasePage;
import Pages.InventoryPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutInfoPage extends BasePage {
    private final By TitlePage = By.cssSelector("[data-test='title']");
    private final By Shopping_cart_Badge = By.cssSelector("[data-test='shopping-cart-badge']");

    private final By firstName = By.cssSelector("[data-test='firstName']");
    private final By lastName = By.cssSelector("[data-test='lastName']");
    private final By ZIPCodeField = By.cssSelector("[data-test='postalCode']");

    private final By ContinueButton = By.cssSelector("[data-test='continue']");
    private final By CancelButton = By.cssSelector("[data-test='cancel']");

    private final  By errorLocator = By.cssSelector("[data-test='error']");


    public CheckoutInfoPage(WebDriver driver) {
        super(driver);

    }

    @Step
    public CheckoutInfoPage Load() {
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
        return this;
    }

    public boolean IsCheckoutPageLoaded(){
        try {
            driver.getCurrentUrl().contains("checkout-step-one");
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }


    @Step
    public CheckoutInfoPage enterInformation(String fName, String lName, String zip) {
        Load();
        SendKeys(firstName, fName);
        SendKeys(lastName, lName);
        SendKeys(ZIPCodeField, zip);
        return this;
    }
    @Step
    public CheckoutOverviewPage clickContinue() {
        ClickElement(ContinueButton);
        return new CheckoutOverviewPage(driver);
    }
    @Step
    public CheckoutInfoPage clickContinueBeforeFilledTheFields() {
        ClickElement(ContinueButton);
        return this;
    }

    @Step
    public InventoryPage clickCancel() {
        ClickElement(CancelButton);
        return new InventoryPage(driver);
    }

    public String MandatoryFieldsErrorMessage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator))
                    .getText()
                    .trim();
        } catch (Exception e) {
            return "";
        }
    }

    public String getErrorMessageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for visibility, then grab and return the inner text
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator)).getText().trim();
    }

}

