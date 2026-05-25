package com.juaracoding;

import java.time.Duration;
import com.juaracoding.pages.WebTablesPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebTablesTest {

    private WebDriver driver;
    private WebTablesPage webTablesPage;

    @BeforeClass
    public void setup() {
        driver = new FirefoxDriver();
        driver.get("https://demoqa.com/webtables");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webTablesPage = new WebTablesPage(driver);
    }

    @Test(priority = 1)
    public void testTC001_AddValidRecord() {
        System.out.println("TC_WT_001: Menambahkan data baru dengan semua field valid");
        webTablesPage.clickAddButton();
        delay(1);
        
        webTablesPage.fillRegistrationForm("John", "Doe", "john.doe@example.com", "30", "50000", "IT");
        delay(1);
        
        webTablesPage.clickSubmitButton();
        delay(2);

        // Verification
        Assert.assertFalse(webTablesPage.isModalOpen(), "Modal should be closed on valid submission");
        Assert.assertTrue(webTablesPage.isRecordPresent("john.doe@example.com"), "Record should be present in the table");
        
        String rowText = webTablesPage.getRowText("john.doe@example.com");
        Assert.assertNotNull(rowText);
        Assert.assertTrue(rowText.contains("John"), "Row should contain first name 'John'");
        Assert.assertTrue(rowText.contains("Doe"), "Row should contain last name 'Doe'");
        Assert.assertTrue(rowText.contains("30"), "Row should contain age '30'");
        Assert.assertTrue(rowText.contains("50000"), "Row should contain salary '50000'");
        Assert.assertTrue(rowText.contains("IT"), "Row should contain department 'IT'");
    }

    @Test(priority = 2)
    public void testTC002_AddRecordEmptyFields() {
        System.out.println("TC_WT_002: Menambahkan data baru dengan semua field wajib kosong");
        webTablesPage.clickAddButton();
        delay(1);
        
        webTablesPage.fillRegistrationForm("", "", "", "", "", "");
        delay(1);
        
        webTablesPage.clickSubmitButton();
        delay(2);

        // Verification
        Assert.assertTrue(webTablesPage.isModalOpen(), "Modal should remain open on empty submission");
        
        // Reset state
        webTablesPage.clickCloseButton();
        delay(1);
    }

    @Test(priority = 3)
    public void testTC003_AddRecordInvalidEmail() {
        System.out.println("TC_WT_003: Menambahkan data baru dengan format Email tidak valid");
        webTablesPage.clickAddButton();
        delay(1);
        
        webTablesPage.fillRegistrationForm("Jane", "Smith", "janesmith.com", "28", "60000", "Finance");
        delay(1);
        
        webTablesPage.clickSubmitButton();
        delay(2);

        // Verification
        Assert.assertTrue(webTablesPage.isModalOpen(), "Modal should remain open on invalid email");
        
        // Reset state
        webTablesPage.clickCloseButton();
        delay(1);
    }

    @Test(priority = 4)
    public void testTC004_AddRecordInvalidAge() {
        System.out.println("TC_WT_004: Menambahkan data baru dengan Age non-numerik atau negatif");
        webTablesPage.clickAddButton();
        delay(1);
        
        webTablesPage.fillRegistrationForm("Bob", "Wilson", "bob.wilson@example.com", "-25", "45000", "Marketing");
        delay(1);
        
        webTablesPage.clickSubmitButton();
        delay(2);

        // Verification
        Assert.assertTrue(webTablesPage.isModalOpen(), "Modal should remain open on invalid age");
        
        // Reset state
        webTablesPage.clickCloseButton();
        delay(1);
    }

    @Test(priority = 5)
    public void testTC005_AddRecordInvalidSalary() {
        System.out.println("TC_WT_005: Menambahkan data baru dengan Salary non-numerik atau negatif");
        webTablesPage.clickAddButton();
        delay(1);
        
        webTablesPage.fillRegistrationForm("Alice", "Wonder", "alice.w@example.com", "25", "-5000", "Design");
        delay(1);
        
        webTablesPage.clickSubmitButton();
        delay(2);

        // Verification
        Assert.assertTrue(webTablesPage.isModalOpen(), "Modal should remain open on invalid salary");
        
        // Reset state
        webTablesPage.clickCloseButton();
        delay(1);
    }

    @Test(priority = 6)
    public void testTC006_EditRecord() {
        System.out.println("TC_WT_006: Mengubah (Edit) data yang sudah ada dengan input valid");
        // Edit record for "Cierra"
        webTablesPage.clickEditRecord("Cierra");
        delay(1);
        
        webTablesPage.fillRegistrationForm("Cierra", "Vega", "cierra@example.com", "39", "99999", "Management");
        delay(1);
        
        webTablesPage.clickSubmitButton();
        delay(2);

        // Verification
        Assert.assertFalse(webTablesPage.isModalOpen(), "Modal should be closed after editing");
        
        String rowText = webTablesPage.getRowText("cierra@example.com");
        Assert.assertNotNull(rowText);
        Assert.assertTrue(rowText.contains("99999"), "Salary should be updated to '99999'");
        Assert.assertTrue(rowText.contains("Management"), "Department should be updated to 'Management'");
    }

    @Test(priority = 7)
    public void testTC007_EditRecordEmptyFields() {
        System.out.println("TC_WT_007: Mengubah (Edit) data dengan mengosongkan field wajib");
        // Edit record for "Cierra" again
        webTablesPage.clickEditRecord("cierra@example.com");
        delay(1);
        
        webTablesPage.fillRegistrationForm("", "", "cierra@example.com", "39", "99999", "Management");
        delay(1);
        
        webTablesPage.clickSubmitButton();
        delay(2);

        // Verification
        Assert.assertTrue(webTablesPage.isModalOpen(), "Modal should remain open when editing with empty names");
        
        // Reset state
        webTablesPage.clickCloseButton();
        delay(1);
    }

    @Test(priority = 8)
    public void testTC008_DeleteRecord() {
        System.out.println("TC_WT_008: Menghapus (Delete) data yang sudah ada");
        Assert.assertTrue(webTablesPage.isRecordPresent("Alden"), "Record for Alden should be present initially");
        
        webTablesPage.clickDeleteRecord("Alden");
        delay(2);

        // Verification
        Assert.assertFalse(webTablesPage.isRecordPresent("Alden"), "Record for Alden should be deleted from the table");
    }

    @Test(priority = 9)
    public void testTC009_SearchRecord() {
        System.out.println("TC_WT_009: Melakukan pencarian data dengan kata kunci yang cocok");
        webTablesPage.searchRecord("Kierra");
        delay(2);

        // Verification
        Assert.assertTrue(webTablesPage.isRecordPresent("Kierra"), "Search results should contain Kierra");
        Assert.assertFalse(webTablesPage.isRecordPresent("John"), "Search results should not contain John");
    }

    @Test(priority = 10)
    public void testTC010_SearchNonExistent() {
        System.out.println("TC_WT_010: Melakukan pencarian data dengan kata kunci yang tidak ada");
        webTablesPage.searchRecord("NoSuchUser123");
        delay(2);

        // Verification
        Assert.assertTrue(webTablesPage.isTableEmpty(), "Table should be empty when search has no results");
        
        // Clear search to restore state
        webTablesPage.clearSearch();
        delay(2);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void delay(long detik) {
        try {
            Thread.sleep(detik * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
