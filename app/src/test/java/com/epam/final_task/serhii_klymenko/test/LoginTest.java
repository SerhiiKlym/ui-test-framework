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
}
