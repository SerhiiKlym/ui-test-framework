package com.epam.final_task.serhii_klymenko.page;

import com.epam.final_task.serhii_klymenko.model.User;
import com.epam.final_task.serhii_klymenko.util.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends AbstractPage {

    private final WebDriverWait wait;
    protected String baseUrl = ConfigReader.get("baseUrl");
    protected String timeout = ConfigReader.get("timeout");

    private final static String title = "Swag Labs";

    @FindBy(css = "#user-name")
    WebElement userNameInputField;
    @FindBy(css = "#password")
    WebElement userPasswordInputField;
    @FindBy(css = "#login-button")
    WebElement loginButton;
    private final By errorMessage = By.cssSelector(".error-message-container.error h3");
    private final By welcomeMessage = By.cssSelector("div.header_label div.app_logo");

    public LoginPage() {
        super();
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(timeout)));
    }

    @Override
    public LoginPage openPage() {
        driver.get(baseUrl);
        if (!driver.getCurrentUrl().equals(baseUrl) || !driver.getTitle().equals(title)) {
            System.out.println(driver.getCurrentUrl() + " " + baseUrl + " " + driver.getTitle() + " " + title);
            throw new RuntimeException("Failed to open the page: " + baseUrl);
        }
        return this;
    }

    public LoginPage inputUserName(User user) {
        wait.until(ExpectedConditions.visibilityOf(userNameInputField)).sendKeys(user.getUserName());
        return this;
    }

    public LoginPage clearName() {
//        wait.until(ExpectedConditions.elementToBeClickable(userNameInputField)).click(); //chrome autofills name after clear()
//        wait.until(ExpectedConditions.visibilityOf(userNameInputField)).clear();
        userNameInputField.sendKeys(Keys.CONTROL + "a");
        userNameInputField.sendKeys(Keys.BACK_SPACE);
        return this;
    }

    public LoginPage inputWrongPassword(User user) {
        wait.until(ExpectedConditions.visibilityOf(userPasswordInputField)).sendKeys(user.getWrongPassWord());
        return this;
    }

    public LoginPage inputLegitPassword(User user) {
        wait.until(ExpectedConditions.visibilityOf(userPasswordInputField)).sendKeys(user.getCorrectPassWord());
        return this;
    }

    public LoginPage clearPassword() {
//        wait.until(ExpectedConditions.elementToBeClickable(userPasswordInputField)).click();
//        wait.until(ExpectedConditions.visibilityOf(userPasswordInputField)).clear();
        wait.until(ExpectedConditions.visibilityOf(userPasswordInputField)).sendKeys(Keys.CONTROL + "a");
        wait.until(ExpectedConditions.visibilityOf(userPasswordInputField)).sendKeys(Keys.BACK_SPACE);
        return this;
    }

    public LoginPage hitLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(loginButton)).click();
        return this;
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isWelcomeMessageDisplayed() {
        return driver.findElement(welcomeMessage).isDisplayed();
    }

    public String getWelcomeMessage() {
        return driver.findElement(welcomeMessage).getText();
    }


}