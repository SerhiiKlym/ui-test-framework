package com.epam.final_task.serhii_klymenko.test;

import com.epam.final_task.serhii_klymenko.page.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    private final static Logger log = LogManager.getLogger(LoginTest.class);

    @Test
    public void UC1LoginWithEmptyCredentials() {
        log.info("Running test on thread: " + Thread.currentThread().getId());
        LoginPage loginPage = new LoginPage();

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
        log.info("Running test on thread: " + Thread.currentThread().getId());
        LoginPage loginPage = new LoginPage();

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
        log.info("Running test on thread: " + Thread.currentThread().getId());
        LoginPage loginPage = new LoginPage();

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
