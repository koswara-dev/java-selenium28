package com.juaracoding;

import com.juaracoding.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import java.io.FileWriter;
import java.io.File;

public class DumpTest {
    @Test
    public void dump() {
        try {
            DriverSingleton.getInstance("chrome-headless");
            WebDriver driver = DriverSingleton.getDriver();
            driver.get("https://m.tiketkai.com/home");
            
            String title = driver.getTitle();
            String pageSource = driver.getPageSource();
            
            System.out.println("PAGE TITLE: " + title);
            System.out.println("PAGE SOURCE LENGTH: " + pageSource.length());
            
            // Save page source to a file in the workspace
            File file = new File("tiketkai_page_source.html");
            FileWriter writer = new FileWriter(file);
            writer.write(pageSource);
            writer.close();
            System.out.println("Saved page source to " + file.getAbsolutePath());
            
            DriverSingleton.closeObjectInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
