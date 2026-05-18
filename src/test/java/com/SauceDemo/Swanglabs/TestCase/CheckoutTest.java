package com.SauceDemo.Swanglabs.TestCase;

import com.SauceDemo.Swanglabs.Bases.BaseTest;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test
    public void checkoutMandatoryFieldsTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        String ActualResult = loginPage
                .Load() // نفتح الموقع في بداية السلسلة فقط
                .login("standard_user", "secret_sauce")
                .AddToCartProcess() // تنتقل تلقائياً لصفحة الـ Cart
                .clickCheckout()    // تنتقل لصفحة معلومات الشحن
                .clickContinueBeforeFilledTheFields()
                .MandatoryFieldsErrorMessage();

        Assert.assertEquals(ActualResult, "Error: First Name is required");
    }

    @Test
    public void successfulCheckoutFlow() {
        LoginPage loginPage = new LoginPage(getDriver());
        String ActualResult = loginPage
                .Load() // نفتح الموقع في البداية فقط
                .login("standard_user", "secret_sauce")
                .AddToCartProcess()
                .clickCheckout()
                .enterInformation("fares", "atef", "12")
                .clickContinue() // تنتقل لصفحة الـ Overview تلقائياً بدون جلب الصفحة من جديد
                .clickFinish()   // تنتقل لصفحة الـ Complete تلقائياً
                .getCompleteHeaderText();

        Assert.assertEquals(ActualResult, "Thank you for your order!");
    }
}