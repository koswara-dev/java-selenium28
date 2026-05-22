package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SearchGoogle {

    public static void main(String[] args) {

        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

        String currentURL = driver.getCurrentUrl();
        String title = driver.getTitle();

        System.out.println("Current URL: " + currentURL);
        System.out.println("Title: " + title);

        // Link text Gmail
        WebElement linkGmail = driver.findElement(By.linkText("Gmail"));
        delay(2);
        linkGmail.click();
        // back
        delay(2);
        driver.navigate().back();
        delay(2);
        // ambil text gmail
        String textGmail = linkGmail.getText();
        System.out.println("Text Gmail: " + textGmail);

        delay(2);

        // Lakukan Search
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("automation testing");
        delay(2);
        // searchBox.submit();
        // enter
        searchBox.sendKeys(Keys.ENTER);

        // delay
        delay(5);

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
