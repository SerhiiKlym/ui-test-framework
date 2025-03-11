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
        options.setCapability("webSocketUrl", true);
        options.setCapability("moz:firefoxOptions", "{\"remote_debugging\": true}");

        log.info("Firefox driver created using WebDriver BiDi (CDP Disabled).");
        return new FirefoxDriver(options);
    }
}
