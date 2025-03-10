package com.epam.final_task.serhii_klymenko.driver;

import com.epam.final_task.serhii_klymenko.util.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final static Logger log = LogManager.getLogger(DriverFactory.class);


    public static WebDriver getDriver() {

        if (driver.get() == null) {
            synchronized (DriverFactory.class) {
                if (driver.get() == null) {
                    createDriver();
                }
            }
        }

        driver.get().manage().window().maximize();
        return driver.get();
    }

    private static void createDriver() {
        String browser = System.getProperty("browser"); // Get from system properties
        log.info("System property browser: " + browser);

        if (browser == null || browser.isEmpty()) {
            browser = ConfigReader.get("browsers").toLowerCase(); // Get from config file
        }

        log.info("Final browser selection: " + browser); // Ensure correct value is used
        switch (browser) {
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                if (System.getenv("GITHUB_ACTIONS") != null) {
                    options.addArguments("--headless");
                }

                driver.set(new FirefoxDriver(options));
                log.info("The " + browser + " driver was created.");
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
                log.info("The " + browser + " driver was created.");
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}