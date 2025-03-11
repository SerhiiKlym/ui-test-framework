package com.epam.final_task.serhii_klymenko.test;

import com.epam.final_task.serhii_klymenko.driver.DriverFactory;
import com.epam.final_task.serhii_klymenko.util.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class BaseTest {
    private final static Logger log = LogManager.getLogger(BaseTest.class);


    @BeforeMethod
    public void setUp() {
        log.info("The setUp method... running test on thread: " + Thread.currentThread().getId());
        DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.closeDriver();
        log.info("The driver was annihilated on thread: " + Thread.currentThread().getId());
    }
}
