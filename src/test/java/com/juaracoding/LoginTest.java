package com.juaracoding;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.LoginPage;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        DriverSingleton.getInstance(browser);
        driver = DriverSingleton.getDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        delay(3);
        DriverSingleton.closeObjectInstance();
    }

    @Test(priority = 1)
    public void testTC001_LoginValidCredentials() {
        System.out.println("TC_LGN_001: Login dengan kredensial valid");
        loginPage.login("Admin", "admin123");
        delay(3);
        Assert.assertTrue(loginPage.isDashboardDisplayed(), "Dashboard should be displayed after valid login");
    }

    @Test(priority = 2)
    public void testTC002_LoginInvalidUsername() {
        System.out.println("TC_LGN_002: Login dengan Username tidak valid");
        loginPage.login("InvalidUser", "admin123");
        delay(2);
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials", "Error message should match");
    }

    @Test(priority = 3)
    public void testTC003_LoginInvalidPassword() {
        System.out.println("TC_LGN_003: Login dengan Password tidak valid");
        loginPage.login("Admin", "wrongpassword");
        delay(2);
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials", "Error message should match");
    }

    @Test(priority = 4)
    public void testTC004_LoginEmptyUsernameAndPassword() {
        System.out.println("TC_LGN_004: Login dengan Username & Password kosong");
        loginPage.login("", "");
        delay(2);
        Assert.assertEquals(loginPage.getRequiredUsernameMessage(), "Required",
                "Username Required validation should be displayed");
        Assert.assertEquals(loginPage.getRequiredPasswordMessage(), "Required",
                "Password Required validation should be displayed");
    }

    @Test(priority = 5)
    public void testTC005_LoginEmptyUsernameValidPassword() {
        System.out.println("TC_LGN_005: Login dengan Username kosong dan Password valid");
        loginPage.login("", "admin123");
        delay(2);
        Assert.assertEquals(loginPage.getRequiredUsernameMessage(), "Required",
                "Username Required validation should be displayed");
        Assert.assertEquals(loginPage.getRequiredPasswordMessage(), "",
                "Password validation message should not be displayed");
    }

    @Test(priority = 6)
    public void testTC006_LoginValidUsernameEmptyPassword() {
        System.out.println("TC_LGN_006: Login dengan Username valid dan Password kosong");
        loginPage.login("Admin", "");
        delay(2);
        Assert.assertEquals(loginPage.getRequiredUsernameMessage(), "",
                "Username validation message should not be displayed");
        Assert.assertEquals(loginPage.getRequiredPasswordMessage(), "Required",
                "Password Required validation should be displayed");
    }

    @Test(priority = 7)
    public void testTC007_LoginIncorrectCasePassword() {
        System.out.println("TC_LGN_007: Login dengan case-sensitivity pada Password");
        loginPage.login("Admin", "ADMIN123");
        delay(2);
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials",
                "Error message should match for case-sensitive password mismatch");
    }

    private void delay(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
