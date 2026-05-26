package com.juaracoding;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.AlertTabModalPage;

public class AlertTabModalTest {

    private WebDriver driver;
    private AlertTabModalPage alertTabModalPage;

    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        DriverSingleton.getInstance(browser);
        driver = DriverSingleton.getDriver();
        driver.get("https://demoqa.com/alerts");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        alertTabModalPage = new AlertTabModalPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        delay(3);
        DriverSingleton.closeObjectInstance();
    }

    @Test
    public void testClickAlertButton() {
        alertTabModalPage.clickAlertButton();
        delay(2);
        driver.switchTo().alert().accept();
    }

    @Test
    public void testClickBrowserWindows() {
        alertTabModalPage.clickBrowserWindows();
        delay(2);
        alertTabModalPage.clickNewTabButton();
        delay(2);
        // handle tab menggunan collection List
        ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
        // switch to new tab
        driver.switchTo().window(newTb.get(1));
        System.out.println("Page title of new tab: " + driver.getTitle());
        // switch to parent window
        driver.switchTo().window(newTb.get(0));
        System.out.println("Page title of parent window: " + driver.getTitle());

    }

    public void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted: " + e.getMessage());
        }
    }

}
