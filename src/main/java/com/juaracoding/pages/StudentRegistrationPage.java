package com.juaracoding.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StudentRegistrationPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @FindBy(id = "firstName")
    private WebElement txtFirstName;

    @FindBy(id = "lastName")
    private WebElement txtLastName;

    @FindBy(id = "userEmail")
    private WebElement txtUserEmail;

    @FindBy(xpath = "//label[contains(@for, 'gender-radio')]")
    private List<WebElement> listGender;

    @FindBy(id = "userNumber")
    private WebElement txtMobile;

    @FindBy(id = "dateOfBirthInput")
    private WebElement txtDateOfBirth;

    @FindBy(className = "react-datepicker__month-select")
    private WebElement selectMonth;

    @FindBy(className = "react-datepicker__year-select")
    private WebElement selectYear;

    @FindBy(id = "subjectsInput")
    private WebElement txtSubjects;

    @FindBy(id = "uploadPicture")
    private WebElement fileUploadPicture;

    @FindBy(id = "currentAddress")
    private WebElement txtCurrentAddress;

    @FindBy(id = "state")
    private WebElement dropState;

    @FindBy(id = "city")
    private WebElement dropCity;

    @FindBy(id = "submit")
    private WebElement btnSubmit;

    @FindBy(id = "example-modal-sizes-title-lg")
    private WebElement textConfirmationTitle;

    @FindBy(xpath = "//div[@class='modal-content']//table")
    private WebElement tableConfirmation;

    @FindBy(id = "closeLargeModal")
    private WebElement btnCloseModal;

    public StudentRegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void fillPersonalDetails(String firstName, String lastName, String email, String mobileNumber) {
        wait.until(ExpectedConditions.visibilityOf(txtFirstName));

        txtFirstName.clear();
        if (firstName != null)
            txtFirstName.sendKeys(firstName);

        txtLastName.clear();
        if (lastName != null)
            txtLastName.sendKeys(lastName);

        txtUserEmail.clear();
        if (email != null)
            txtUserEmail.sendKeys(email);

        txtMobile.clear();
        if (mobileNumber != null)
            txtMobile.sendKeys(mobileNumber);
    }

    public void chooseGender(String genderName) {
        wait.until(ExpectedConditions.visibilityOfAllElements(listGender));
        for (WebElement genderLabel : listGender) {
            if (genderLabel.getText().equalsIgnoreCase(genderName)) {
                js.executeScript("arguments[0].scrollIntoView(true);", genderLabel);
                js.executeScript("arguments[0].click();", genderLabel);
                break;
            }
        }
    }

    public void setDateOfBirth(String day, String month, String year) {
        js.executeScript("arguments[0].scrollIntoView(true);", txtDateOfBirth);
        js.executeScript("arguments[0].click();", txtDateOfBirth);

        wait.until(ExpectedConditions.visibilityOf(selectMonth));
        new Select(selectMonth).selectByVisibleText(month);

        wait.until(ExpectedConditions.visibilityOf(selectYear));
        new Select(selectYear).selectByVisibleText(year);

        // Padded day (e.g. 25 -> 025, 5 -> 005)
        String paddedDay = String.format("%03d", Integer.parseInt(day));
        String xpathDay = "//div[contains(@class, 'react-datepicker__day--" + paddedDay
                + "') and not(contains(@class, 'react-datepicker__day--outside-month'))]";
        WebElement dayElement = driver.findElement(By.xpath(xpathDay));
        js.executeScript("arguments[0].click();", dayElement);
    }

    public void enterSubjects(String subject) {
        if (subject != null && !subject.isEmpty()) {
            js.executeScript("arguments[0].scrollIntoView(true);", txtSubjects);
            txtSubjects.sendKeys(subject);
            sleep(1000);
            txtSubjects.sendKeys(Keys.ENTER);
        }
    }

    public void chooseHobbies(List<String> hobbiesList) {
        if (hobbiesList != null) {
            for (String hobby : hobbiesList) {
                String xpath = "//label[contains(@for, 'hobbies-checkbox') and text()='" + hobby + "']";
                WebElement hobbyLabel = driver.findElement(By.xpath(xpath));
                js.executeScript("arguments[0].scrollIntoView(true);", hobbyLabel);
                js.executeScript("arguments[0].click();", hobbyLabel);
            }
        }
    }

    public void uploadPicture(String filePath) {
        if (filePath != null && !filePath.isEmpty()) {
            js.executeScript("arguments[0].scrollIntoView(true);", fileUploadPicture);
            fileUploadPicture.sendKeys(filePath);
        }
    }

    public void enterCurrentAddress(String address) {
        if (address != null && !address.isEmpty()) {
            js.executeScript("arguments[0].scrollIntoView(true);", txtCurrentAddress);
            txtCurrentAddress.clear();
            txtCurrentAddress.sendKeys(address);
        }
    }

    public void selectStateAndCity(String stateName, String cityName) {
        if (stateName != null && !stateName.isEmpty()) {
            js.executeScript("arguments[0].scrollIntoView(true);", dropState);
            wait.until(ExpectedConditions.elementToBeClickable(dropState));
            js.executeScript("arguments[0].click();", dropState);

            WebElement inputState = driver.findElement(By.xpath("//div[@id='state']//input"));
            inputState.sendKeys(stateName);
            inputState.sendKeys(Keys.ENTER);
            sleep(1000);
        }

        if (cityName != null && !cityName.isEmpty()) {
            js.executeScript("arguments[0].scrollIntoView(true);", dropCity);
            wait.until(ExpectedConditions.elementToBeClickable(dropCity));
            js.executeScript("arguments[0].click();", dropCity);

            WebElement inputCity = driver.findElement(By.xpath("//div[@id='city']//input"));
            inputCity.sendKeys(cityName);
            inputCity.sendKeys(Keys.ENTER);
            sleep(1000);
        }
    }

    public void clickSubmit() {
        js.executeScript("arguments[0].scrollIntoView(true);", btnSubmit);
        wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
        js.executeScript("arguments[0].click();", btnSubmit);
    }

    public boolean isConfirmationModalOpen() {
        try {
            wait.until(ExpectedConditions.visibilityOf(textConfirmationTitle));
            return textConfirmationTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getConfirmationModalText() {
        try {
            wait.until(ExpectedConditions.visibilityOf(tableConfirmation));
            return tableConfirmation.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickCloseModal() {
        js.executeScript("arguments[0].scrollIntoView(true);", btnCloseModal);
        wait.until(ExpectedConditions.elementToBeClickable(btnCloseModal));
        js.executeScript("arguments[0].click();", btnCloseModal);
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
