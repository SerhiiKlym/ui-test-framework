package com.epam.final_task.serhii_klymenko.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxStrategy implements BrowserStrategy {
    private final static Logger log = LogManager.getLogger(FirefoxStrategy.class);

    @Override
    public WebDriver createDriver() {
        FirefoxOptions options = new FirefoxOptions();

        if (System.getenv("GITHUB_ACTIONS") != null) {
            options.addArguments("--headless");
        }

        log.info("Firefox driver was created.");
        return new FirefoxDriver(options);
    }
}
