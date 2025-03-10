package com.epam.final_task.serhii_klymenko.util;

import com.epam.final_task.serhii_klymenko.driver.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class TestListener implements ITestListener {
    private final Logger log = LogManager.getRootLogger();

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        saveScreenshot();
    }

    private void saveScreenshot() {
        try {
            File screenCapture = ((TakesScreenshot) DriverFactory
                    .getDriver())
                    .getScreenshotAs(OutputType.FILE);

            String screensDir = System.getProperty("screenshot.dir", "./build/screenshots/");

            File targetFile = new File(screensDir, getCurrentTimeAsString() + ".png");

            boolean isCreated = targetFile.getParentFile().mkdirs();
            if (!isCreated) {
                log.warn("Failed to create screenshot directory: " + targetFile.getParentFile());
            }

            FileUtils.copyFile(screenCapture, targetFile);
            log.info("Screenshot saved at: " + targetFile.getAbsolutePath());

        } catch (WebDriverException e) {
            log.error("Failed to take screenshot: " + e.getMessage());
        }catch (IOException e){
            log.error("Failed to take screenshot: ", e);
        }

    }


    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}
