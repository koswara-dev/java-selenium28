package com.juaracoding.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // Selectors representing the mobile KAI ticket search page elements
    @FindBy(xpath = "//input[contains(@id, 'origin') or contains(@name, 'origin')]")
    private WebElement txtOrigin;

    @FindBy(xpath = "//input[contains(@id, 'destination') or contains(@name, 'destination')]")
    private WebElement txtDestination;

    @FindBy(xpath = "//input[contains(@id, 'date') or contains(@name, 'date')]")
    private WebElement txtDepartureDate;

    @FindBy(xpath = "//button[contains(@id, 'search') or contains(@class, 'search') or contains(text(), 'Cari')]")
    private WebElement btnSearch;

    @FindBy(xpath = "//div[contains(@class, 'error') or contains(@class, 'warning') or contains(@class, 'alert')]")
    private WebElement lblErrorMessage;

    @FindBy(xpath = "//div[contains(@class, 'passenger')]")
    private WebElement dropdownPassenger;

    @FindBy(xpath = "//button[contains(@class, 'add-adult')]")
    private WebElement btnAddAdult;

    @FindBy(xpath = "//button[contains(@class, 'add-infant')]")
    private WebElement btnAddInfant;

    public void selectOrigin(String origin) {
        wait.until(ExpectedConditions.visibilityOf(txtOrigin));
        txtOrigin.clear();
        txtOrigin.sendKeys(origin);
        // Typically selects the first autocomplete item
        delay(1);
        try {
            WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[contains(@class, 'autocomplete-item') or contains(text(), '" + origin.toUpperCase() + "')]")));
            firstResult.click();
        } catch (Exception e) {
            // Fallback if autocomplete doesn't load
            System.out.println("Autocomplete item not found, proceeding with typed text");
        }
    }

    public void selectDestination(String destination) {
        wait.until(ExpectedConditions.visibilityOf(txtDestination));
        txtDestination.clear();
        txtDestination.sendKeys(destination);
        delay(1);
        try {
            WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[contains(@class, 'autocomplete-item') or contains(text(), '" + destination.toUpperCase() + "')]")));
            firstResult.click();
        } catch (Exception e) {
            System.out.println("Autocomplete item not found, proceeding with typed text");
        }
    }

    public void setDepartureDate(String date) {
        wait.until(ExpectedConditions.visibilityOf(txtDepartureDate));
        txtDepartureDate.clear();
        txtDepartureDate.sendKeys(date);
    }

    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSearch));
        btnSearch.click();
    }

    public void setPassengers(int adults, int infants) {
        wait.until(ExpectedConditions.elementToBeClickable(dropdownPassenger));
        dropdownPassenger.click();
        delay(1);

        // Click add adult buttons until count reached (default is 1)
        for (int i = 1; i < adults; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(btnAddAdult));
            btnAddAdult.click();
        }

        // Click add infant buttons until count reached
        for (int i = 0; i < infants; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(btnAddInfant));
            btnAddInfant.click();
        }
    }

    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(lblErrorMessage));
            return lblErrorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isSearchResultsPageDisplayed() {
        // Typically wait for elements on results page, e.g. schedule list or URL containing schedule
        try {
            return driver.getCurrentUrl().contains("schedule") || driver.getCurrentUrl().contains("cari");
        } catch (Exception e) {
            return false;
        }
    }

    private void delay(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
