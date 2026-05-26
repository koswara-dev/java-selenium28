package com.juaracoding;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.juaracoding.drivers.DriverSingleton;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TextboxText {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void Setup() {
        String browser = System.getProperty("browser", "chrome");
        DriverSingleton.getInstance(browser);
        driver = DriverSingleton.getDriver();
        driver.get("https://demoqa.com/text-box");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test(priority = 2)
    public void testBiodata() {

        // Fullname
        WebElement txtFullname = driver.findElement(By.id("userName"));
        txtFullname.sendKeys("Juara Coding");
        // Email
        WebElement txtEmail = driver.findElement(By.id("userEmail"));
        txtEmail.sendKeys("admin@juaracoding.co.id");
        // Current Address
        WebElement txtCurrentAddress = driver.findElement(By.id("currentAddress"));
        txtCurrentAddress.sendKeys("Jakarta");
        // Permanent Address
        WebElement txtPermanentAddress = driver.findElement(By.id("permanentAddress"));
        txtPermanentAddress.sendKeys("Jakarta");
        // scroll 500px
        // js.executeScript("window.scrollBy(0, 500);", "");
        // scroll to element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", txtPermanentAddress);
        // Submit
        WebElement btnSubmit = driver.findElement(By.id("submit"));
        wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
        js.executeScript("arguments[0].click();", btnSubmit);

        // Delay
        delay(5);

        String textCurrentAddress = driver.findElement(By.id("output")).getText();
        Assert.assertTrue(textCurrentAddress.contains("Juara Coding"));

    }

    // test scenario 2 get title & current url
    @Test(priority = 1)
    public void testGetTitle() {

        String title = driver.getTitle();
        String currentURL = driver.getCurrentUrl();

        System.out.println("Title: " + title);
        System.out.println("Current URL: " + currentURL);

        Assert.assertEquals(title, "demosite");

        delay(5);
    }

    @AfterClass
    public void teardown() {
        DriverSingleton.closeObjectInstance();
    }

    static void delay(long detik) {
        try {
            Thread.sleep(detik * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
