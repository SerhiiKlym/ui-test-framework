package com.epam.final_task.serhii_klymenko.util;

import com.epam.final_task.serhii_klymenko.driver.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {
    private static final Logger log = LogManager.getLogger(ScreenshotUtil.class);
    private static final String SCREENSHOT_DIR = "./build/screenshots/";

    public static void saveScreenshot(String testName) {


        try {
            File screenCapture = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.FILE);

            Files.createDirectories(Paths.get(SCREENSHOT_DIR)); // Ensure directory exists
            String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS"));
            File targetFile = new File(SCREENSHOT_DIR + testName.replaceAll(" ", "_") + "_" + timestamp + ".png");

            boolean isCreated = targetFile.getParentFile().mkdirs();
            if (!isCreated && !targetFile.getParentFile().exists()) {
                log.warn("Failed to create screenshot directory: " + targetFile.getParentFile());
            } else if (!isCreated) {
                log.info("Screenshot directory already exists: " + targetFile.getParentFile());
            }

            FileUtils.copyFile(screenCapture, targetFile);
            log.info("Screenshot saved at: " + targetFile.getAbsolutePath());

        } catch (WebDriverException e) {
            log.error("Failed to take screenshot: " + e.getMessage());
        }catch (IOException e){
            log.error("Failed to take screenshot: ", e);
        }
    }
}
