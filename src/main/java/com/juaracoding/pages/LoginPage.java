package com.juaracoding.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='username']")
    private WebElement txtUsername;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement txtPassword;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnLogin;

    @FindBy(xpath = "//p[contains(@class, 'oxd-alert-content-text')]")
    private WebElement lblErrorMessage;

    @FindBy(xpath = "//div[contains(@class,'oxd-input-group')][descendant::label[text()='Username']]//span[contains(@class,'oxd-input-group__message')]")
    private WebElement lblRequiredUsername;

    @FindBy(xpath = "//div[contains(@class,'oxd-input-group')][descendant::label[text()='Password']]//span[contains(@class,'oxd-input-group__message')]")
    private WebElement lblRequiredPassword;

    @FindBy(xpath = "//h6[contains(@class, 'oxd-topbar-header-breadcrumb-module')]")
    private WebElement lblDashboardHeader;

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(txtUsername));
        txtUsername.sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(txtPassword));
        txtPassword.sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
        btnLogin.click();
    }

    public void login(String username, String password) {
        // Clear username if not empty, by selecting all and backspace
        wait.until(ExpectedConditions.visibilityOf(txtUsername));
        txtUsername.sendKeys(org.openqa.selenium.Keys.chord(org.openqa.selenium.Keys.CONTROL, "a"),
                org.openqa.selenium.Keys.BACK_SPACE);
        if (username != null && !username.isEmpty()) {
            txtUsername.sendKeys(username);
        }

        // Clear password if not empty
        wait.until(ExpectedConditions.visibilityOf(txtPassword));
        txtPassword.sendKeys(org.openqa.selenium.Keys.chord(org.openqa.selenium.Keys.CONTROL, "a"),
                org.openqa.selenium.Keys.BACK_SPACE);
        if (password != null && !password.isEmpty()) {
            txtPassword.sendKeys(password);
        }

        clickLogin();
    }

    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(lblErrorMessage));
            return lblErrorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getRequiredUsernameMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(lblRequiredUsername));
            return lblRequiredUsername.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getRequiredPasswordMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(lblRequiredPassword));
            return lblRequiredPassword.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isDashboardDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(lblDashboardHeader));
            return lblDashboardHeader.getText().equalsIgnoreCase("Dashboard");
        } catch (Exception e) {
            return false;
        }
    }
}
