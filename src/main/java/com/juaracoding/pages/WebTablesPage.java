package com.juaracoding.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebTablesPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @FindBy(xpath = "//button[@id='addNewRecordButton']")
    private WebElement btnAdd;

    @FindBy(id = "searchBox")
    private WebElement txtSearch;

    @FindBy(id = "firstName")
    private WebElement txtFirstName;

    @FindBy(id = "lastName")
    private WebElement txtLastName;

    @FindBy(id = "userEmail")
    private WebElement txtEmail;

    @FindBy(id = "age")
    private WebElement txtAge;

    @FindBy(id = "salary")
    private WebElement txtSalary;

    @FindBy(id = "department")
    private WebElement txtDepartment;

    @FindBy(id = "submit")
    private WebElement btnSubmit;

    @FindBy(css = "button.btn-close")
    private WebElement btnClose;

    public WebTablesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAdd));
        js.executeScript("arguments[0].click();", btnAdd);
    }

    public void fillRegistrationForm(String firstName, String lastName, String email, String age, String salary,
            String department) {
        wait.until(ExpectedConditions.visibilityOf(txtFirstName));

        txtFirstName.clear();
        if (firstName != null)
            txtFirstName.sendKeys(firstName);

        txtLastName.clear();
        if (lastName != null)
            txtLastName.sendKeys(lastName);

        txtEmail.clear();
        if (email != null)
            txtEmail.sendKeys(email);

        txtAge.clear();
        if (age != null)
            txtAge.sendKeys(age);

        txtSalary.clear();
        if (salary != null)
            txtSalary.sendKeys(salary);

        txtDepartment.clear();
        if (department != null)
            txtDepartment.sendKeys(department);
    }

    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
        js.executeScript("arguments[0].click();", btnSubmit);
    }

    public void clickCloseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnClose));
        js.executeScript("arguments[0].click();", btnClose);
    }

    public void searchRecord(String keyword) {
        wait.until(ExpectedConditions.visibilityOf(txtSearch));
        txtSearch.clear();
        txtSearch.sendKeys(keyword);
    }

    public void clearSearch() {
        wait.until(ExpectedConditions.visibilityOf(txtSearch));
        txtSearch.clear();
        txtSearch.sendKeys("");
    }

    public boolean isModalOpen() {
        try {
            WebElement form = driver.findElement(By.id("userForm"));
            return form.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickEditRecord(String emailOrName) {
        String xpath = "//tr[descendant::td[contains(text(), '" + emailOrName
                + "')]]//span[@title='Edit' or contains(@id, 'edit-record')]";
        WebElement editBtn = driver.findElement(By.xpath(xpath));
        js.executeScript("arguments[0].scrollIntoView(true);", editBtn);
        wait.until(ExpectedConditions.elementToBeClickable(editBtn));
        js.executeScript("arguments[0].click();", editBtn);
    }

    public void clickDeleteRecord(String emailOrName) {
        String xpath = "//tr[descendant::td[contains(text(), '" + emailOrName
                + "')]]//span[@title='Delete' or contains(@id, 'delete-record')]";
        WebElement deleteBtn = driver.findElement(By.xpath(xpath));
        js.executeScript("arguments[0].scrollIntoView(true);", deleteBtn);
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn));
        js.executeScript("arguments[0].click();", deleteBtn);
    }

    public boolean isRecordPresent(String emailOrName) {
        try {
            String xpath = "//td[contains(text(), '" + emailOrName + "')]";
            return driver.findElements(By.xpath(xpath)).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public String getRowText(String emailOrName) {
        try {
            String xpath = "//tr[descendant::td[contains(text(), '" + emailOrName + "')]]";
            WebElement row = driver.findElement(By.xpath(xpath));
            return row.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isTableEmpty() {
        try {
            return driver.findElements(By.xpath("//table/tbody/tr")).size() == 0;
        } catch (Exception e) {
            return true;
        }
    }
}
