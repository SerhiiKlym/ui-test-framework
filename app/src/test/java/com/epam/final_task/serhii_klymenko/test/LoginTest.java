package com.epam.final_task.serhii_klymenko.test;

import com.epam.final_task.serhii_klymenko.page.LoginPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    @Test
    public void UC1LoginWithEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage = loginPage.openPage()
                .inputRandomName()
                .inputRandomPassword()
                .clearName()
                .clearPassword()
                .hitLoginButton();
        String error = "";
        if (loginPage.isErrorMessageDisplayed()) {
            error = loginPage.getErrorMessage();
        }
        assertEquals(error, "Epic sadface: Username is required");
    }

    @Test
    public void UC2LoginWithEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage = loginPage.openPage()
                .inputRandomName()
                .inputRandomPassword()
                .clearPassword()
                .hitLoginButton();
        String error = "";
        if (loginPage.isErrorMessageDisplayed()) {
            error = loginPage.getErrorMessage();
        }
        assertEquals(error, "Epic sadface: Password is required");
    }

    @Test
    public void UC3LoginWithLegitCredentials() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage = loginPage.openPage()
                .inputLegitName()
                .inputLegitPassword()
                .hitLoginButton();
        String welcomeMsg = "";
        if (loginPage.isWelcomeMessageDisplayed()) {
            welcomeMsg = loginPage.getWelcomeMessage();
        }
        assertEquals(welcomeMsg, "Swag Labs");
    }
}
