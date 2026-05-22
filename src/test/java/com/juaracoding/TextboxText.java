package com.juaracoding;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TextboxText {

    @Test
    public void testBiodata() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://demoqa.com/text-box");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
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
        // Submit
        WebElement btnSubmit = driver.findElement(By.id("submit"));
        btnSubmit.click();

        // Delay
        delay(2);

        String textCurrentAddress = driver.findElement(By.id("output")).getText();
        Assert.assertTrue(textCurrentAddress.contains("Juara Coding"));

        // Tutup Browser
        driver.quit();

    }

    static void delay(long detik) {
        try {
            Thread.sleep(detik * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
