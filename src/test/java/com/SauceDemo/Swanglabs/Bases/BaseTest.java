package com.SauceDemo.Swanglabs.Bases;

import com.SauceDemo.Swanglabs.Config.CookieUtils;
import com.SauceDemo.Swanglabs.Factory.DriverFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BaseTest {

    // استخدام الـ ThreadLocal لتأمين الـ Parallel Testing
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    public WebDriver getDriver() {
        return this.driver.get();
    }

    @BeforeMethod
    public void setUp() {
        WebDriver rawDriver = new DriverFactory().initilizeDrive();
        setDriver(rawDriver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            // 1. أخذ لقطة شاشة فقط في حالة فشل الاختبار (Best Practice)
            if (result.getStatus() == ITestResult.FAILURE) {
                String testCaseName = result.getMethod().getMethodName();
                // إصلاح المسار ليكون اسم الملف هو اسم التست: testCaseName.png
                File destFile = new File("target" + File.separator + "ScreenShots" + File.separator + testCaseName + ".png");
                TakeScreenShots(destFile);
            }
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        } finally {
            // 2. التأكد من قفل المتصفح وتنظيف الـ Thread لمنع التعليق (Hang)
            if (getDriver() != null) {
                getDriver().quit();
                driver.remove(); // 💡 السطر السحري لمنع هنجة الـ ThreadLocal
            }
        }
    }

    public void TakeScreenShots(File destFile) {
        File file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, destFile);
            try (InputStream inputStream = new FileInputStream(destFile)) {
                Allure.addAttachment("Failed Test Screenshot", inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to capture screenshot", e);
        }
    }

    @Step("Injecting API Cookies into Browser")
    public void injectCookiestoBrowser(List<Cookie> restassuredCookie) {
        List<org.openqa.selenium.Cookie> seleniumCookie = CookieUtils.ConvertRestAssuredToSeleniumCookie(restassuredCookie);
        for (org.openqa.selenium.Cookie cookie : seleniumCookie) {
            getDriver().manage().addCookie(cookie);
        }
    }
}