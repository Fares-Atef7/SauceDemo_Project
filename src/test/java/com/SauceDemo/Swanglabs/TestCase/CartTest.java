package com.SauceDemo.Swanglabs.TestCase;

import com.SauceDemo.Swanglabs.Bases.BaseTest;
import Pages.CartPage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test //  تم إضافة الأنوتيشن المفقود هنا ليعمل التست
    public void removeProductFromCartTestAndVerifyCartIsEmpty() {
        LoginPage loginPage = new LoginPage(getDriver());
        Boolean ActualResult = loginPage
                .Load()
                .login("standard_user", "secret_sauce")
                .AddSauce_Labs_Backpack()
                .AddSauce_Labs_Bike_Light()
                .ClickOnCartBadge()
                .removeBackpack()
                .removeBikeLight()
                .isCartEmpty();

        Assert.assertTrue(ActualResult, "The Cart not Empty after pressed on Remove buttons");
    }

    @Test
    public void navigateToCheckoutTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        Boolean ActualResult = loginPage
                .Load()
                .login("standard_user", "secret_sauce")
                .AddSauce_Labs_Backpack()
                .AddSauce_Labs_Bike_Light()
                .ClickOnCartBadge()
                .clickCheckout()
                .IsCheckoutPageLoaded();
        Assert.assertTrue(ActualResult, "The Checkout Page Not Loaded");
    }

    @Test
    public void continueShoppingNavigationTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        Boolean ActualResult = loginPage
                .Load()
                .login("standard_user", "secret_sauce")
                .AddSauce_Labs_Backpack()
                .AddSauce_Labs_Bike_Light()
                .ClickOnCartBadge()
                .ClickOnContinueShopping()
                .IsInventoryPageLoaded();

        Assert.assertTrue(ActualResult, "The Inventory Page Not Loaded");
    }

    @Test
    public void verifyMultipleProductsAddedToCartAsNumbers() {
        LoginPage loginPage = new LoginPage(getDriver());
        int ActualCartItems = loginPage
                .Load()
                .login("standard_user", "secret_sauce")
                .AddSauce_Labs_Backpack()
                .AddSauce_Labs_Bike_Light()
                .ClickOnCartBadge()
                .getProductCount();

        Assert.assertEquals(ActualCartItems, 2, "The Amount of items not equal.");
    }

    @Test
    public void verifyMultipleProductsAddedToCartByNames() {
        LoginPage loginPage = new LoginPage(getDriver());
        CartPage ActualCartItems = loginPage
                .Load()
                .login("standard_user", "secret_sauce")
                .AddSauce_Labs_Backpack()
                .AddSauce_Labs_Bike_Light()
                .ClickOnCartBadge();

        Assert.assertTrue(ActualCartItems.containsProduct("Sauce Labs Backpack")
                        && ActualCartItems.containsProduct("Sauce Labs Bike Light"),
                "The Items Doesn't match");
    }
}