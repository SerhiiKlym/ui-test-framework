package com.epam.final_task.serhii_klymenko.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener {
    private final Logger log = LogManager.getRootLogger();

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        log.error("TestNG Test Failed: " + result.getName());
        ScreenshotUtil.saveScreenshot(result.getName());
    }
}
