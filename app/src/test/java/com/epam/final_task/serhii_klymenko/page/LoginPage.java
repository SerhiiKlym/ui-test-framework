package com.epam.final_task.serhii_klymenko.page;

import com.epam.final_task.serhii_klymenko.model.User;
import com.epam.final_task.serhii_klymenko.util.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

    private final static Logger log = LogManager.getLogger(LoginPage.class);
    protected String baseUrl = ConfigReader.get("baseUrl");
    private final static String title = "Swag Labs";

    @FindBy(css = "#user-name")
    WebElement userNameInputField;
    @FindBy(css = "#password")
    WebElement userPasswordInputField;
    @FindBy(css = "#login-button")
    WebElement loginButton;
    @FindBy(css = ".error-message-container.error h3")
    WebElement errorMessage;

    public LoginPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public LoginPage openApp() {
        driver.get(baseUrl);
        if (!driver.getCurrentUrl().equals(baseUrl) || !driver.getTitle().equals(title)) {
            throw new RuntimeException(String.format("Failed to open App page. Expected URL: %s, Actual URL: %s, Expected Title: %s, Actual Title: %s",
                    baseUrl, driver.getCurrentUrl(), title, driver.getTitle()));
        }
        return this;
    }

    public LoginPage inputUserName(User user) {
        log.info("Entering username: {}", user.getUserName());
        wait.until(ExpectedConditions.visibilityOf(userNameInputField)).sendKeys(user.getUserName());
        return this;
    }

    public LoginPage clearName() {
        log.info("Clearing username...");
        userNameInputField.sendKeys(Keys.CONTROL + "a");
        userNameInputField.sendKeys(Keys.BACK_SPACE);
        return this;
    }

    public LoginPage inputWrongPassword(User user) {
        log.info("Entering wrong password {} for  username: {}", user.getWrongPassWord(), user.getUserName());
        wait.until(ExpectedConditions.visibilityOf(userPasswordInputField)).sendKeys(user.getWrongPassWord());
        return this;
    }

    public LoginPage inputLegitPassword(User user) {
        log.info("Entering correct password {} for  username: {}", user.getCorrectPassWord(), user.getUserName());
        wait.until(ExpectedConditions.visibilityOf(userPasswordInputField)).sendKeys(user.getCorrectPassWord());
        return this;
    }

    public LoginPage clearPassword() {
        log.info("Clearing password...");
        wait.until(ExpectedConditions.visibilityOf(userPasswordInputField)).sendKeys(Keys.CONTROL + "a");
        wait.until(ExpectedConditions.visibilityOf(userPasswordInputField)).sendKeys(Keys.BACK_SPACE);
        return this;
    }

    public AbstractPage hitLoginButton() {
        log.info("Hitting login button...");
        wait.until(ExpectedConditions.visibilityOf(loginButton)).click();

        if (isErrorMessageDisplayed()){
            log.info("Staying on login page.");
            return this;
        }
        log.info("Going to Inventory page.");
        return new InventoryPage();
    }

    public boolean isErrorMessageDisplayed() {
        log.info("Looking for error message...");
        if (!driver.getCurrentUrl().equals(this.baseUrl)) {
            log.info("Not on LoginPage, skipping error message check.");
            return false;
        }
        log.info("Looking for error message on LoginPage...");
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
    }

    public String getErrorMessage() {
        log.info("Getting error message from LoginPage.");
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }
}