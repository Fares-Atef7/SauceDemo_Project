package Utilis;

import Bases.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementUtils extends BasePage {
    public ElementUtils(WebDriver driver) {
        super(driver);
    }

    public void Click(By element) {
        Scroll.scrollToElement(driver, element);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void SendKeys(By element, String value) {
        WebElement el = webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        el.clear();
        el.sendKeys(value);
    }

    public String GetElementText(By locator) {
        return driver.findElement(locator).getText();
    }

    public boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    public WebElement waitForElementVisible(By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

