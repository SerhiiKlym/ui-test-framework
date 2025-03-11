package com.epam.final_task.serhii_klymenko.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeStrategy implements BrowserStrategy{
    private final static Logger log = LogManager.getLogger(ChromeStrategy.class);

    @Override
    public WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();

        if (System.getenv("GITHUB_ACTIONS") != null) {
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        log.info("Chrome driver was created on thread: " + Thread.currentThread().getId());
        return new ChromeDriver(options);
    }
}
