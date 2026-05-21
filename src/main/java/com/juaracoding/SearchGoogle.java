package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchGoogle {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

        String currentURL = driver.getCurrentUrl();
        String title = driver.getTitle();

        System.out.println("Current URL: " + currentURL);
        System.out.println("Title: " + title);

        // Lakukan Search
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("automation testing");
        searchBox.submit();

        // delay
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Tutup Browser
        driver.quit();

    }

}
