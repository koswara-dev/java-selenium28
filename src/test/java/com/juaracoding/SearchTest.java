package com.juaracoding;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.SearchPage;

public class SearchTest {

    private WebDriver driver;
    private SearchPage searchPage;

    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        DriverSingleton.getInstance(browser);
        driver = DriverSingleton.getDriver();
        driver.get("https://m.tiketkai.com/home");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        searchPage = new SearchPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        delay(3);
        DriverSingleton.closeObjectInstance();
    }

    @Test(priority = 1)
    public void testTC_SCH_001_SearchValidInputs() {
        System.out.println("TC_SCH_001: Search with valid inputs (1 Adult)");
        searchPage.selectOrigin("Gambir");
        searchPage.selectDestination("Bandung");
        searchPage.setDepartureDate("2026-06-01"); // Future date representation
        searchPage.setPassengers(1, 0);
        searchPage.clickSearch();
        delay(3);
        Assert.assertTrue(searchPage.isSearchResultsPageDisplayed(), "Search results page should be displayed");
    }

    @Test(priority = 2)
    public void testTC_SCH_002_SearchSameStations() {
        System.out.println("TC_SCH_002: Search with same Origin and Destination station");
        searchPage.selectOrigin("Gambir");
        searchPage.selectDestination("Gambir");
        searchPage.clickSearch();
        delay(2);
        Assert.assertTrue(searchPage.getErrorMessage().contains("tidak boleh sama") || !searchPage.isSearchResultsPageDisplayed(),
                "Error validation should trigger or search should be blocked");
    }

    @Test(priority = 3)
    public void testTC_SCH_003_SearchPastDate() {
        System.out.println("TC_SCH_003: Search with past Departure Date");
        searchPage.selectOrigin("Gambir");
        searchPage.selectDestination("Bandung");
        searchPage.setDepartureDate("2020-01-01"); // Past date
        searchPage.clickSearch();
        delay(2);
        Assert.assertTrue(searchPage.getErrorMessage().contains("tanggal") || !searchPage.isSearchResultsPageDisplayed(),
                "Error validation should trigger or search should be blocked for past dates");
    }

    @Test(priority = 4)
    public void testTC_SCH_004_SearchEmptyOrigin() {
        System.out.println("TC_SCH_004: Search with empty Origin Station");
        searchPage.selectDestination("Bandung");
        searchPage.clickSearch();
        delay(2);
        Assert.assertTrue(searchPage.getErrorMessage().contains("asal") || !searchPage.isSearchResultsPageDisplayed(),
                "Error validation should trigger or search should be blocked when origin is empty");
    }

    @Test(priority = 5)
    public void testTC_SCH_005_SearchEmptyDestination() {
        System.out.println("TC_SCH_005: Search with empty Destination Station");
        searchPage.selectOrigin("Gambir");
        searchPage.clickSearch();
        delay(2);
        Assert.assertTrue(searchPage.getErrorMessage().contains("tujuan") || !searchPage.isSearchResultsPageDisplayed(),
                "Error validation should trigger or search should be blocked when destination is empty");
    }

    @Test(priority = 6)
    public void testTC_SCH_006_SearchMaxPassengers() {
        System.out.println("TC_SCH_006: Search with maximum passengers (4 Adults)");
        searchPage.selectOrigin("Gambir");
        searchPage.selectDestination("Bandung");
        searchPage.setDepartureDate("2026-06-01");
        searchPage.setPassengers(4, 0);
        searchPage.clickSearch();
        delay(3);
        Assert.assertTrue(searchPage.isSearchResultsPageDisplayed(), "Search results page should be displayed for 4 adults");
    }

    @Test(priority = 7)
    public void testTC_SCH_007_SearchInfantExceedingAdult() {
        System.out.println("TC_SCH_007: Search with Infant count exceeding Adult count");
        searchPage.selectOrigin("Gambir");
        searchPage.selectDestination("Bandung");
        searchPage.setPassengers(1, 2); // 1 Adult, 2 Infants
        delay(2);
        Assert.assertTrue(searchPage.getErrorMessage().contains("infant") || !searchPage.isSearchResultsPageDisplayed(),
                "Error validation should trigger or selection should be blocked when infants exceed adults");
    }

    private void delay(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
