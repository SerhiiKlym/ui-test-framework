package com.epam.final_task.serhii_klymenko.test;

import com.epam.final_task.serhii_klymenko.model.User;
import com.epam.final_task.serhii_klymenko.page.LoginPage;
import com.epam.final_task.serhii_klymenko.service.UserDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    private final static Logger log = LogManager.getLogger(LoginTest.class);

    @Test(dataProvider = "userProvider", dataProviderClass = UserDataProvider.class)
    public void UC1LoginWithEmptyCredentials(User user) {
        log.info("Running test on thread: " + Thread.currentThread().getId());
        LoginPage loginPage = new LoginPage();

        loginPage = loginPage.openPage()
                .inputUserName(user)
                .inputWrongPassword(user)
                .clearName()
                .clearPassword()
                .hitLoginButton();
        String error = "";
        if (loginPage.isErrorMessageDisplayed()) {
            error = loginPage.getErrorMessage();
        }
        assertEquals(error, "Epic sadface: Username is required");
    }

    @Test(dataProvider = "userProvider", dataProviderClass = UserDataProvider.class)
    public void UC2LoginWithEmptyPassword(User user) {
        log.info("Running test on thread: " + Thread.currentThread().getId());
        LoginPage loginPage = new LoginPage();

        loginPage = loginPage.openPage()
                .inputUserName(user)
                .inputWrongPassword(user)
                .clearPassword()
                .hitLoginButton();
        String error = "";
        if (loginPage.isErrorMessageDisplayed()) {
            error = loginPage.getErrorMessage();
        }
        assertEquals(error, "Epic sadface: Password is required");
    }

    @Test(dataProvider = "userProvider", dataProviderClass = UserDataProvider.class)
    public void UC3LoginWithLegitCredentials(User user) {
        log.info("Running test on thread: " + Thread.currentThread().getId());
        LoginPage loginPage = new LoginPage();

        loginPage = loginPage.openPage()
                .inputUserName(user)
                .inputLegitPassword(user)
                .hitLoginButton();
        String welcomeMsg = "";
        if (loginPage.isWelcomeMessageDisplayed()) {
            welcomeMsg = loginPage.getWelcomeMessage();
        }
        assertEquals(welcomeMsg, "Swag Labs");
    }
}
