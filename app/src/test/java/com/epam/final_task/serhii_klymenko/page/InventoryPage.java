package com.epam.final_task.serhii_klymenko.page;

import com.epam.final_task.serhii_klymenko.util.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends AbstractPage {

    private final static Logger log = LogManager.getLogger(InventoryPage.class);

    protected String inventoryUrl = ConfigReader.get("inventoryUrl");


    private final static String title = "Swag Labs";

    @FindBy(css = "div.header_label div.app_logo")
    WebElement inventoryDashboard;


}