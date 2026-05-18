package com.SauceDemo.Swanglabs.TestCase;

import com.SauceDemo.Swanglabs.Bases.BaseTest;
import Pages.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class inventoryTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(inventoryTest.class);

    @Test
    public void AddToCartSuccessfully() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .login("standard_user", "secret_sauce")
                .AddToCartProcess();


    }


    @Test
    public void AddSpecificItemsToCart() {

    }

    @Test
    public void verifyClickProductNameNavigatesToDetailPage() {
        LoginPage loginPage = new LoginPage(getDriver());

        Boolean IsProductPageLoaded = loginPage
                .login("standard_user", "secret_sauce")
                .ClickOnProductNameSauce_Labs_Backpack()
                .IS_ProductDetailsPageLoaded();

        Assert.assertTrue(IsProductPageLoaded, "ProductPageNotLoaded");
    }

    @Test
    public void verifyClickProductImageNavigatesToDetailPage() {
        LoginPage loginPage = new LoginPage(getDriver());

        Boolean IsProductPageLoaded = loginPage
                .login("standard_user", "secret_sauce")
                .ClickOnProductImage()
                .IS_ProductDetailsPageLoaded();

        Assert.assertTrue(IsProductPageLoaded, "ProductPageNotLoaded");

    }

    @Test
    public void verifyBackFromDetailReturnsToInventory() {
        LoginPage loginPage = new LoginPage(getDriver());

        Boolean IsInventoryPageLoaded = loginPage
                .login("standard_user", "secret_sauce")
                .ClickOnProductImage()
                .ClickOnBactToProductsButton()
                .IsInventoryPageLoaded();

        Assert.assertTrue(IsInventoryPageLoaded, "ProductPageNotLoaded");

    }

    @Test
    public void verifyCartIconNavigatesToCartPage() {
        LoginPage loginPage = new LoginPage(getDriver());

        Boolean IsInventoryPageLoaded = loginPage
                .login("standard_user", "secret_sauce")
                .ClickOnCartBadge()
                .IsCartPageLoaded();

        Assert.assertTrue(IsInventoryPageLoaded, "ProductPageNotLoaded");
    }

}
