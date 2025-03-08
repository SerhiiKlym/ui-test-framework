package com.epam.final_task.serhii_klymenko.driver;

import com.epam.final_task.serhii_klymenko.util.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {

        if (driver.get() == null) {
            String browser = ConfigReader.get("browser").toLowerCase();

            switch (browser) {
                case "firefox" -> driver.set(new FirefoxDriver());
                case "chrome" -> driver.set(new ChromeDriver());
            }

            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    private static void closeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}