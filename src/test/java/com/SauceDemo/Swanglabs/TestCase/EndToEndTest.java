package com.SauceDemo.Swanglabs.TestCase;

import com.SauceDemo.Swanglabs.Bases.BaseTest;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndTest extends BaseTest {

    @Test
    public void endToEthPurchaseTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        String AcutalResult = loginPage
                .login("standard_user", "secret_sauce")
                .AddToCartProcess()
                .Load()
                .clickCheckout()
                .enterInformation("fares", "atef", "12")
                .clickContinue()
                .Load()
                .clickFinish()
                .Load()
                .getCompleteHeaderText();

        Assert.assertEquals(AcutalResult, "Thank you for your order!");
    }



}
