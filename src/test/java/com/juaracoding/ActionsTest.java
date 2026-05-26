package com.juaracoding;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.ActionsPage;

public class ActionsTest {

    private WebDriver driver;
    private ActionsPage actionsPage;

    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        DriverSingleton.getInstance(browser);
        driver = DriverSingleton.getDriver();
        driver.get("https://demoqa.com/menu");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        actionsPage = new ActionsPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        delay(3);
        DriverSingleton.closeObjectInstance();
    }

    @Test
    public void testMoveToElement() {
        delay(3);
        WebElement mainItem2 = driver.findElement(By.xpath("//a[normalize-space()='Main Item 2']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(mainItem2).click().perform();
        delay(2);
    }

    static void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
