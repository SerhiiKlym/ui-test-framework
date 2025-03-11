package com.epam.final_task.serhii_klymenko.driver;

import com.epam.final_task.serhii_klymenko.util.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final static Logger log = LogManager.getLogger(DriverFactory.class);

    public static WebDriver getDriver() {

        if (driver.get() == null) {
            synchronized (DriverFactory.class) {
                if (driver.get() == null) {
                    log.info("Creating new WebDriver instance...");
                    driver.set(createDriver());
                } else {
                    log.info("Reusing existing WebDriver instance.");
                }
            }
        }
        log.info("Maximizing browser window...");
        driver.get().manage().window().maximize();
        return driver.get();
    }

    private static BrowserStrategy getBrowserStrategy(String browser) {
        log.info("Selecting browser strategy for: {}", browser);
        return switch (browser.toLowerCase()) {
            case "firefox" -> {
                log.info("Using FirefoxStrategy");
                yield new FirefoxStrategy();
            }
            case "chrome" -> {
                log.info("Using ChromeStrategy");
                yield new ChromeStrategy();
            }
            default -> {
                log.error("Unsupported browser provided: {}", browser);
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        };
    }

    private static WebDriver createDriver() {
        String browser = System.getProperty("browser"); // Get from system properties
        log.info("System property browser: " + browser);

        if (browser == null || browser.isEmpty()) {
            browser = ConfigReader.get("browsers").toLowerCase(); // Get from config file
        }

        log.info("Final browser selection: " + browser);

        BrowserStrategy strategy = getBrowserStrategy(browser);
        WebDriver driver = strategy.createDriver();

        log.info("The " + browser + " driver was created on thread: " + Thread.currentThread().getId());
        return driver;
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            log.info("Closing WebDriver instance...");
            driver.get().quit();
            driver.remove();
        } else {
            log.warn("Attempted to close WebDriver, but it was already null.");
        }
    }
}