package com.juaracoding.drivers;

import com.juaracoding.drivers.strategies.DriverStrategy;
import com.juaracoding.drivers.strategies.DriverStrategyImplementer;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

public class DriverSingleton {
    private static DriverSingleton instance = null;
    private static WebDriver driver;

    private DriverSingleton(String browser) {
        instantiate(browser);
    }

    public static DriverSingleton getInstance(String browser) {
        if (instance == null) {
            instance = new DriverSingleton(browser);
        }
        return instance;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public WebDriver instantiate(String strategy) {
        DriverStrategy driverStrategy = DriverStrategyImplementer.chooseStrategy(strategy);
        driver = driverStrategy.setStrategy();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    public static void closeObjectInstance() {
        instance = null;
        if (driver != null) {
            driver.quit();
        }
    }
}
