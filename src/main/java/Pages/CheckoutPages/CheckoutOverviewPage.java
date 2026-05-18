package Pages.CheckoutPages;

import Bases.BasePage;
import Pages.InventoryPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {
    private final By TitlePage = By.cssSelector("[data-test='title']");
    private final By Shopping_cart_Badge = By.cssSelector("[data-test='shopping-cart-badge']");

    private final By CartList = By.cssSelector("[data-test='cart-list']");

    private final By PaymentInfoValue = By.cssSelector("[data-test='payment-info-value']");


    private final By totalLabel = By.cssSelector("[data-test='total-label']");
    private final By TaxLabel = By.cssSelector("[data-test='tax-label']");
    private final By subtotalLabel = By.cssSelector("[data-test='subtotal-label']");


    private final By Finish = By.cssSelector("[data-test='finish']");
    private final By CancelButton = By.cssSelector("[data-test='cancel']");

    @Step
    public CheckoutOverviewPage Load() {
        driver.get("https://www.saucedemo.com/checkout-step-two.html");
        return this;
    }


    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public String getPaymentInfo() {
        return driver.findElement(PaymentInfoValue).getText();
    }

    public String getTotalPriceLabel() {
        return driver.findElement(totalLabel).getText();
    }
    @Step
    public CheckoutCompletePage clickFinish() {
        ClickElement(Finish);
        return new CheckoutCompletePage(driver);
    }
    @Step
    public InventoryPage clickCancel() {
        ClickElement(CancelButton);
        return new InventoryPage(driver);
    }
}
