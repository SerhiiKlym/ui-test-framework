package com.epam.final_task.serhii_klymenko.page;

import com.epam.final_task.serhii_klymenko.driver.DriverFactory;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {
    protected WebDriver driver;

    protected abstract AbstractPage openPage();

    protected AbstractPage(){
        this.driver = DriverFactory.getDriver();
    }
}
