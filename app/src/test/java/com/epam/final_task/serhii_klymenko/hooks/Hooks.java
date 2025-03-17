package com.epam.final_task.serhii_klymenko.hooks;

import com.epam.final_task.serhii_klymenko.driver.DriverFactory;
import com.epam.final_task.serhii_klymenko.util.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {

    private final static Logger log = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp() {
        log.info("The setUp method (hooks)... running test on thread: " + Thread.currentThread().getId());
        DriverFactory.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            log.error("Cucumber Test Failed: " + scenario.getName());
           ScreenshotUtil.saveScreenshot(scenario.getName());
        }
        DriverFactory.closeDriver();
        log.info("The driver was annihilated on thread (hooks): " + Thread.currentThread().getId());
    }
}
