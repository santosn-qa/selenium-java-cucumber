package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public abstract class BasePage {

    protected static WebDriver driver;
    protected WebDriverWait wait;

    private static final int WAIT_TIMEOUT_SECONDS = 10;

    public BasePage() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
    }

    protected WebElement findElementBy(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void clickElement(By locator) {
        findElementBy(locator).click();
    }

    protected void setElementText(By locator, String text) {
        WebElement element = findElementBy(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getElementText(By locator) {
        WebElement element = findElementBy(locator);
        return element.getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return findElementBy(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public static void initializeWebDriver() {
        String browser = System.getProperty("browser");

        if (browser == null) {
            // Set the default browser to Chrome
            browser = "chrome";
        }

        browser = browser.toLowerCase();

        switch (browser) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.manage().window().maximize();
    }

    public static void quitWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
