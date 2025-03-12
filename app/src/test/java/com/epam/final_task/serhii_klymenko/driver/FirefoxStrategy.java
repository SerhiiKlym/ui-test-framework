package com.epam.final_task.serhii_klymenko.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxStrategy implements BrowserStrategy {
    private final static Logger log = LogManager.getLogger(FirefoxStrategy.class);

    @Override
    public WebDriver createDriver() {
        log.info("Running in GitHub Actions: {}", System.getenv("GITHUB_ACTIONS") != null);
        FirefoxOptions options = new FirefoxOptions();

        if (System.getenv("GITHUB_ACTIONS") != null) {
            options.addArguments("--headless");
        }
        options.setCapability("webSocketUrl", true);
        options.setCapability("moz:firefoxOptions", "{\"remote_debugging\": true}");

        log.info("Firefox driver created using WebDriver BiDi (CDP Disabled).");
        try {
            log.info("Initializing Firefox WebDriver with options: {}", options);
            WebDriver driver = new FirefoxDriver(options);
            log.info("Firefox WebDriver successfully created.");
            return driver;
        } catch (SessionNotCreatedException e) {
            log.error("Session creation failed: {}", e.getMessage(), e);
            throw e;
        }
    }
}
