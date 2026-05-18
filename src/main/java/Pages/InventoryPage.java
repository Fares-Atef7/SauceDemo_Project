package Pages;

import Bases.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage extends BasePage {
    public InventoryPage(WebDriver driver) {
        super(driver);
    }
    private final By pageTitle = By.cssSelector(".title");
//    private final By pageTitle = By.cssSelector("[data-test='app_logo']");
    private final By Sauce_Labs_Backpack = By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']");
    private final By Sauce_Labs_Bike_Light = By.cssSelector("[data-test='add-to-cart-sauce-labs-bike-light']");
    private final By ShoppingCartICon = By.cssSelector("[data-test='shopping-cart-link']");
    private final By Remove_sauce_labs_bike_light = By.cssSelector("[data-test='remove-sauce-labs-bike-light']");
    private final By Sauce_Labs_Backpack_NAME = By.cssSelector("[data-test='item-4-title-link']");
    private final By Sauce_Labs_Bike_Light_NAME = By.cssSelector("[data-test='item-0-title-link']");
    private final By Sauce_Labs_Backpack_Image = By.cssSelector("[data-test='inventory-item-sauce-labs-backpack-img']");
    private final By back_to_products_Button = By.cssSelector("[data-test='back-to-products']");
//    private final By  = By.cssSelector("[data-test='']");



    public boolean isTitleDisplayed() {
        try {
            // 1. إنشاء Wait محلي بمهلة 10 ثوانٍ ليكون معزولاً تماماً ومضموناً
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // 2. الانتظار الذكي حتى يظهر العنصر على الشاشة بعد ضغط زر اللوجن
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
        } catch (Exception e) {
            // لو حصل أي Timeout أو مشكلة هيرجع false بذكاء ويرسب التست بوضوح
            return false;
        }
    }

    public boolean IsInventoryPageLoaded() {
        try {
            // الانتظار الذكي حتى يتغير رابط المتصفح ويحتوي على كلمة inventory
            return webDriverWait.until(ExpectedConditions.urlContains("inventory"));
        } catch (TimeoutException e) {
            return false; // لو انتهى الوقت والرباط متغيّرش يرجع false
        }
    }

    public String getTitleText() {
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    @Step
    public InventoryPage Load() {
        driver.get("https://www.saucedemo.com/inventory.html");
        return this;
    }

    @Step
    public CartPage AddToCartProcess() {
        Load();
        ClickElement(Sauce_Labs_Backpack);
        ClickElement(Sauce_Labs_Bike_Light);
        ClickElement(ShoppingCartICon);
        return new CartPage(driver);
    }

    @Step
    public InventoryPage AddSauce_Labs_Backpack() {
        ClickElement(Sauce_Labs_Backpack);
        return this;
    }

    @Step
    public InventoryPage AddSauce_Labs_Bike_Light() {
        ClickElement(Sauce_Labs_Bike_Light);
        return this;
    }

    @Step
    public CartPage ClickOnCartBadge() {
        ClickElement(ShoppingCartICon);
        return new CartPage(driver);
    }

    @Step
    public InventoryPage RemoveItem() {
        ClickElement(Remove_sauce_labs_bike_light);
        return this;
    }

    @Step
    public InventoryPage addProductToCartByName(String productName) {
        // Convert "Sauce Labs Bike Light" to "sauce-labs-bike-light"
        String formattedName = productName.toLowerCase().replace(" ", "-");

        // Construct the dynamic data-test locator
        String locatorString = String.format("[data-test='add-to-cart-%s']", formattedName);
        By addToCartButton = By.cssSelector(locatorString);

        // Wait and click
        ClickElement(addToCartButton);
        return this;
    }

    @Step
    public InventoryPage removeItem(String productName) {
        String formattedName = productName.toLowerCase().replace(" ", "-");
        String locatorString = String.format("[data-test='remove-%s']", formattedName);
        By removeButtonLocator = By.cssSelector(locatorString);
        ClickElement(removeButtonLocator);
        return this;
    }

    @Step
    public ProductPage ClickOnProductNameSauce_Labs_Backpack(){
        ClickElement(Sauce_Labs_Backpack_NAME);
        return new ProductPage(driver);
    }

    @Step
    public ProductPage ClickOnProductImage(){
        ClickElement(Sauce_Labs_Backpack_Image);
        return new ProductPage(driver);
    }



}
