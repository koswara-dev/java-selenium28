package com.juaracoding.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ActionsPage {

    private WebDriver driver;

    public ActionsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[normalize-space()='Menu']")
    private WebElement menu;

    @FindBy(xpath = "//a[normalize-space()='Main Item 2']")
    private WebElement mainItem2;

    public void clikMenu() {
        menu.click();
    }

}
