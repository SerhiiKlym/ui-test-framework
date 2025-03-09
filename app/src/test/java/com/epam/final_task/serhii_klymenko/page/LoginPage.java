package com.epam.final_task.serhii_klymenko.page;

import com.epam.final_task.serhii_klymenko.util.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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

    @FindBy(css = "#user-name")
    WebElement userNameInputField;
    @FindBy(css = "#password")
    WebElement userPasswordInputField;
    @FindBy(css = "#login-button")
    WebElement loginButton;

    private final By errorMessage = By.cssSelector(".error-message-container.error h3");

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(timeout)));
    }

    @Override
    public LoginPage openPage() {
        driver.get(baseUrl);
        return this;
    }

    // TODO: parametrize
    public LoginPage inputRandomName() {
        wait.until(ExpectedConditions.visibilityOf(userNameInputField)).sendKeys("random_name");
        return this;
    }

    public LoginPage clearName()  {
//        wait.until(ExpectedConditions.elementToBeClickable(userNameInputField)).click(); //chrome autofills name after clear()
//        wait.until(ExpectedConditions.visibilityOf(userNameInputField)).clear();
        userNameInputField.sendKeys(Keys.CONTROL + "a");
        userNameInputField.sendKeys(Keys.BACK_SPACE);
        return this;
    }

    // TODO: parametrize
    public LoginPage inputRandomPassword()  {
        wait.until(ExpectedConditions.visibilityOf(userPasswordInputField)).sendKeys("random_pass");
        return this;
    }

    public LoginPage clearPassword()  {
        wait.until(ExpectedConditions.elementToBeClickable(userPasswordInputField)).click();
        wait.until(ExpectedConditions.visibilityOf(userPasswordInputField)).clear();
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
}