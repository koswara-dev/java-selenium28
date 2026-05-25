package com.juaracoding;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import com.juaracoding.pages.StudentRegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentRegistrationTest {

    private WebDriver driver;
    private StudentRegistrationPage studentRegistrationPage;

    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        studentRegistrationPage = new StudentRegistrationPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        delay(3);
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void testTC001_RegisterAllValidFields() {
        System.out.println("TC_REG_001: Melakukan registrasi dengan mengisi semua field wajib & opsional dengan data valid");
        
        studentRegistrationPage.fillPersonalDetails("John", "Doe", "john.doe@gmail.com", "0812345678");
        studentRegistrationPage.chooseGender("Female");
        
        // Date of Birth: 25 May 2000
        studentRegistrationPage.setDateOfBirth("25", "May", "2000");
        
        // Subjects: Computer Science
        studentRegistrationPage.enterSubjects("Computer Science");
        
        // Hobbies: Sports, Reading
        studentRegistrationPage.chooseHobbies(Arrays.asList("Sports", "Reading"));
        
        // Picture: avatar.jpg
        File dummyFile = new File("avatar.jpg");
        String absolutePath = dummyFile.getAbsolutePath();
        studentRegistrationPage.uploadPicture(absolutePath);
        
        // Current Address: Jl. Juara Coding No. 1, Jakarta
        studentRegistrationPage.enterCurrentAddress("Jl. Juara Coding No. 1, Jakarta");
        
        // State and City: NCR, Delhi
        studentRegistrationPage.selectStateAndCity("NCR", "Delhi");
        
        // Submit
        studentRegistrationPage.clickSubmit();
        delay(2);

        // Verification
        Assert.assertTrue(studentRegistrationPage.isConfirmationModalOpen(), "Confirmation modal should be displayed");
        
        String modalText = studentRegistrationPage.getConfirmationModalText();
        System.out.println("=== Confirmation Modal Data ===");
        System.out.println(modalText);
        System.out.println("=================================");
        
        Assert.assertTrue(modalText.contains("John Doe"), "Modal should contain name 'John Doe'");
        Assert.assertTrue(modalText.contains("john.doe@gmail.com"), "Modal should contain email");
        Assert.assertTrue(modalText.contains("Female"), "Modal should contain gender 'Female'");
        Assert.assertTrue(modalText.contains("0812345678"), "Modal should contain mobile number");
        Assert.assertTrue(modalText.contains("25 May,2000"), "Modal should contain birth date");
        Assert.assertTrue(modalText.contains("Computer Science"), "Modal should contain subject");
        Assert.assertTrue(modalText.contains("Sports, Reading"), "Modal should contain hobbies");
        Assert.assertTrue(modalText.contains("avatar.jpg"), "Modal should contain picture name");
        Assert.assertTrue(modalText.contains("Jl. Juara Coding No. 1, Jakarta"), "Modal should contain address");
        Assert.assertTrue(modalText.contains("NCR Delhi"), "Modal should contain state and city");
        
        // Close modal
        studentRegistrationPage.clickCloseModal();
        delay(1);
    }

    @Test(priority = 2)
    public void testTC002_RegisterMissingRequiredFields() {
        System.out.println("TC_REG_002: Melakukan registrasi dengan mengosongkan field wajib");
        
        // Leave First Name and Last Name empty, fill other fields
        studentRegistrationPage.fillPersonalDetails("", "", "john.doe@gmail.com", "0812345678");
        studentRegistrationPage.chooseGender("Female");
        studentRegistrationPage.setDateOfBirth("25", "May", "2000");
        studentRegistrationPage.enterSubjects("Computer Science");
        studentRegistrationPage.chooseHobbies(Arrays.asList("Sports"));
        
        File dummyFile = new File("avatar.jpg");
        studentRegistrationPage.uploadPicture(dummyFile.getAbsolutePath());
        
        studentRegistrationPage.enterCurrentAddress("Jl. Juara Coding No. 1, Jakarta");
        studentRegistrationPage.selectStateAndCity("NCR", "Delhi");
        
        studentRegistrationPage.clickSubmit();
        delay(2);

        // Verification
        Assert.assertFalse(studentRegistrationPage.isConfirmationModalOpen(), "Confirmation modal should NOT be displayed when names are missing");
    }

    @Test(priority = 3)
    public void testTC003_RegisterInvalidMobileNumber() {
        System.out.println("TC_REG_003: Melakukan registrasi dengan memasukkan nomor handphone kurang dari 10 digit");
        
        studentRegistrationPage.fillPersonalDetails("John", "Doe", "john.doe@gmail.com", "12345"); // 5 digits
        studentRegistrationPage.chooseGender("Female");
        studentRegistrationPage.setDateOfBirth("25", "May", "2000");
        studentRegistrationPage.enterSubjects("Computer Science");
        studentRegistrationPage.chooseHobbies(Arrays.asList("Sports"));
        
        File dummyFile = new File("avatar.jpg");
        studentRegistrationPage.uploadPicture(dummyFile.getAbsolutePath());
        
        studentRegistrationPage.enterCurrentAddress("Jl. Juara Coding No. 1, Jakarta");
        studentRegistrationPage.selectStateAndCity("NCR", "Delhi");
        
        studentRegistrationPage.clickSubmit();
        delay(2);

        // Verification
        Assert.assertFalse(studentRegistrationPage.isConfirmationModalOpen(), "Confirmation modal should NOT be displayed when mobile number is too short");
    }

    private void delay(long detik) {
        try {
            Thread.sleep(detik * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
