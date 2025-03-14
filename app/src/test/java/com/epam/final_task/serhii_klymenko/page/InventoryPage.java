package com.epam.final_task.serhii_klymenko.page;

import com.epam.final_task.serhii_klymenko.util.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends AbstractPage {

    private final static Logger log = LogManager.getLogger(InventoryPage.class);
    protected String inventoryUrl = ConfigReader.get("inventoryUrl");
    private final static String title = "Swag Labs";

    @FindBy(css = "div.header_label div.app_logo")
    WebElement inventoryDashboard;

    public InventoryPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public boolean isInventoryPageOpened() {
        log.info("Checking if we have opened the Inventory page for real...");
        return (wait.until(ExpectedConditions.visibilityOf(inventoryDashboard)).isDisplayed() &&
                (driver.getCurrentUrl().equals(inventoryUrl)));
    }

    public String getWelcomeMessage() {
        log.info("Getting the text of a header on Inventory page...");
        return wait.until(ExpectedConditions.visibilityOf(inventoryDashboard)).getText();
    }

    public String getTitle() {
        return title;
    }
}