package com.epam.final_task.serhii_klymenko.page;

import com.epam.final_task.serhii_klymenko.driver.DriverFactory;
import com.epam.final_task.serhii_klymenko.util.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {

    private final static Logger log = LogManager.getLogger(AbstractPage.class);
    protected WebDriver driver;
    protected final WebDriverWait wait;
    protected String timeout = ConfigReader.get("timeout");

    protected AbstractPage() {
        log.info("Super constructor is called.");
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(timeout)));
    }
}
