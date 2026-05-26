package com.juaracoding.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlertTabModalPage {

    private WebDriver driver;

    public AlertTabModalPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "alertButton")
    private WebElement alertButton;

    @FindBy(xpath = "//span[normalize-space()='Browser Windows']")
    private WebElement menuBrowserWindows;

    @FindBy(xpath = "//button[@id='tabButton']")
    private WebElement newTabButton;

    public void clickAlertButton() {
        alertButton.click();
    }

    public void clickBrowserWindows() {
        menuBrowserWindows.click();
    }

    public void clickNewTabButton() {
        newTabButton.click();
    }

}
