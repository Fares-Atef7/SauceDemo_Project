package com.SauceDemo.Swanglabs.TestCase;

import com.SauceDemo.Swanglabs.Bases.BaseTest;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", "success"},
                {"locked_out_user", "secret_sauce", "error"},
                {"invalid_user", "secret_sauce", "error"},
                {"", "", "error"},
                {"standard_user", "wrong_pass", "error"}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String user, String pass, String expectedResult) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .Load()
                .login(user, pass);

        if (expectedResult.equals("error")) {
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                    "Expected error message was not displayed for user: " + user);
        } else {
            InventoryPage inventoryPage = new InventoryPage(getDriver());
            Assert.assertTrue(inventoryPage.isTitleDisplayed(),
                    "Happy path failed! User was not redirected to Inventory Page: " + user);
        }
    }


//     ── Test 2: ConfigUtils-driven
//    @Test
//    public void loginTestWithConfig() {
//
//        String testUser = System.getProperty("testUser", "standard_user");
//
//        getDriver().get(ConfigUtils.getBaseUrl());
//
//        LoginPage loginPage = new LoginPage(getDriver());
//        loginPage.login(ConfigUtils.getUserName(), ConfigUtils.getPassword());
//
//        if (testUser.contains("locked")
//                || testUser.isEmpty()
//                || testUser.contains("unlisted_user")
//                || ConfigUtils.getUserName().isEmpty()
//                || testUser.contains("integer_user")
//                || testUser.contains("Sql_user")
//                || testUser.contains("javaScript_user")
//                || testUser.contains("long_name")
//                || ConfigUtils.getPassword().isEmpty()) {
//
//            Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
//                    "Expected error message for: " + testUser);
//            Assert.assertTrue(loginPage.getErrorMessage().contains("sad"),
//                    "Expected 'sad' in error message for: " + testUser);
//
//        } else {
//
//            InventoryPage homePage = new InventoryPage(getDriver());
//            Assert.assertTrue(homePage.isTitleDisplayed(),
//                    "Expected inventory page for: " + testUser);
//        }
//    }

}

