package com.juaracoding.drivers.strategies;

public class DriverStrategyImplementer {
    public static DriverStrategy chooseStrategy(String strategy) {
        if (strategy == null) {
            return new Chrome(); // default browser
        }
        
        switch (strategy.toLowerCase()) {
            case "chrome":
                return new Chrome();
            case "chrome-headless":
            case "chome-headless": // handle typo
                return new ChromeHeadless();
            case "firefox":
                return new Firefox();
            case "firefox-headless":
                return new FirefoxHeadless();
            default:
                return new Chrome(); // default browser fallback
        }
    }
}
