package com.epam.final_task.serhii_klymenko.page;

import com.epam.final_task.serhii_klymenko.driver.DriverFactory;
import com.epam.final_task.serhii_klymenko.util.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {
    protected WebDriver driver;

    protected final WebDriverWait wait;
    protected String timeout = ConfigReader.get("timeout");

    protected AbstractPage(){
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(timeout)));
    }
}
