package com.SauceDemo.Swanglabs.TestCase;

import com.SauceDemo.Swanglabs.Bases.BaseTest;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @Test
    public void ClickOnProductAndVerifyIfItTheSameProduct(){
        LoginPage loginPage = new LoginPage(getDriver());
        String ActualResult = loginPage
                .login("standard_user", "secret_sauce")
                .ClickOnProductNameSauce_Labs_Backpack()
                .GetProductName();

        Assert.assertEquals(ActualResult,"Sauce Labs Backpack","The Items Doesn't Match");
    }






}




