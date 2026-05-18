package Pages.CheckoutPages;

import Bases.BasePage;
import Pages.InventoryPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {
    private final By SuccessfulMessage = By.cssSelector("[data-test='complete-header']");

    private final By BackHomeButton = By.cssSelector("[data-test='back-to-products']");

    @Step
    public CheckoutCompletePage Load() {
        driver.get("https://www.saucedemo.com/checkout-complete.html");
        return this;
    }
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getCompleteHeaderText() {
        return driver.findElement(SuccessfulMessage).getText();
    }
    @Step
    public InventoryPage clickBackHome() {
        ClickElement(BackHomeButton);
        return new InventoryPage(driver);
    }
}
