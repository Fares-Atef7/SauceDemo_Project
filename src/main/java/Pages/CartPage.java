package Pages;

import Bases.BasePage;
import Pages.CheckoutPages.CheckoutInfoPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {


    private final By continueShoppingButton = By.id("continue-shopping");
    private final By CartItems = By.className("inventory_item_name");
    private final By CartItemPrices = By.className("inventory_item_price");
    private final By removeBackpackButton = By.id("remove-sauce-labs-backpack");
    private final By removeBikeLightButton = By.id("remove-sauce-labs-bike-light");
    private final By CheckoutButton = By.id("checkout");


    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public CartPage removeItem(String productName) {
        String formattedName = productName.toLowerCase().replace(" ", "-");
        String locatorString = String.format("[data-test='remove-%s']", formattedName);
        By removeButtonLocator = By.cssSelector(locatorString);
        ClickElement(removeButtonLocator);
        return this;
    }

    @Step
    public CartPage Load() {
        driver.get("https://www.saucedemo.com/cart.html");
        return this;
    }
    @Step
    public CheckoutInfoPage clickCheckout() {
        ClickElement(CheckoutButton);
        return new CheckoutInfoPage(driver);
    }
    @Step
    public InventoryPage ClickOnContinueShopping() {
        ClickElement(continueShoppingButton);
        return new InventoryPage(driver);
    }
    @Step
    public CartPage removeBikeLight() {
        ClickElement(removeBikeLightButton);
        return this;
    }
    @Step
    public CartPage removeBackpack() {
        ClickElement(removeBackpackButton);
        return this;
    }

    public boolean IsCartPageLoaded() {
        try {
            driver.getCurrentUrl().contains("cart");
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    //cart validation

    public List<WebElement> getCartItems() {
        return driver.findElements(CartItems);
    }

  //to get product count
    public int getProductCount() {
        return getCartItems().size();
    }

    // verify product exists in cart
    public boolean containsProduct(String productName) {
        for (WebElement item : getCartItems()) {
            if (item.getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    // verify cart is empty
    public boolean isCartEmpty() {
        return getProductCount() == 0;
    }

}

