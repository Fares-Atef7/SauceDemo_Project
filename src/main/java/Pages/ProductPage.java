package Pages;

import Bases.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {

    private final By back_to_products_Button = By.cssSelector("[data-test='back-to-products']");
    private final By AddToCartButton = By.cssSelector("[data-test='add-to-cart']");
    private final By Remove = By.cssSelector("[data-test='remove']");
    private final By ShoppingCartICon = By.cssSelector("[data-test='shopping-cart-link']");

    private final By ProductName = By.cssSelector("[data-test='inventory-item-name']");


    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public ProductPage(WebDriver driver) {
        super(driver);
    }


    public boolean IS_ProductDetailsPageLoaded() {
        try {
            WebElement backButton = wait.until(ExpectedConditions.visibilityOfElementLocated(back_to_products_Button));
            String buttonText = backButton.getText().trim();
            return backButton.isDisplayed() && buttonText.equalsIgnoreCase("Back to products");
        } catch (Exception e) {
            return false;
        }
    }

    @Step
    public InventoryPage ClickOnBactToProductsButton(){
        ClickElement(back_to_products_Button);
        return new InventoryPage(driver);
    }

    public String GetProductName(){
        return driver.findElement(ProductName).getText();
    }

    @Step
    public ProductPage addProductToCart() {
        ClickElement(AddToCartButton);
        return this;
    }

    @Step
    public CartPage ClickOnCartBadge() {
        ClickElement(ShoppingCartICon);
        return new CartPage(driver);
    }

    @Step
    public ProductPage RemoveItem() {
        ClickElement(Remove);
        return this;
    }


}

