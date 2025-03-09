package com.epam.final_task.serhii_klymenko.driver;

import com.epam.final_task.serhii_klymenko.util.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        synchronized (DriverFactory.class) {
            if (driver.get() == null) {
                String browser = ConfigReader.get("browsers").toLowerCase();

                switch (browser) {
                    case "firefox" -> {
                        FirefoxOptions options = new FirefoxOptions();
                        if (System.getenv("GITHUB_ACTIONS") != null) {
                            options.addArguments("--headless");
                        }

                        driver.set(new FirefoxDriver(options));
                    }
                    case "chrome" -> {
                        ChromeOptions options = new ChromeOptions();

                        // Run headless mode only in GitHub Actions
                        if (System.getenv("GITHUB_ACTIONS") != null) {
                            options.addArguments("--headless");
                            options.addArguments("--disable-gpu");
                            options.addArguments("--no-sandbox");
                            options.addArguments("--disable-dev-shm-usage");
                        }

                        driver.set(new ChromeDriver(options));
                    }
                }

                driver.get().manage().window().maximize();
            }
        }
        return driver.get();
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}