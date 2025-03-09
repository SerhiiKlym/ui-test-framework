package com.epam.final_task.serhii_klymenko.test;

import com.epam.final_task.serhii_klymenko.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
      this.driver = DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
      DriverFactory.closeDriver();
    }
}
