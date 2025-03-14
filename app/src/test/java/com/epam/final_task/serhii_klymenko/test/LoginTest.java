package com.epam.final_task.serhii_klymenko.test;

import com.epam.final_task.serhii_klymenko.model.User;
import com.epam.final_task.serhii_klymenko.page.AbstractPage;
import com.epam.final_task.serhii_klymenko.page.InventoryPage;
import com.epam.final_task.serhii_klymenko.page.LoginPage;
import com.epam.final_task.serhii_klymenko.service.UserDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    private final static Logger log = LogManager.getLogger(LoginTest.class);

    @Test(dataProvider = "userProvider", dataProviderClass = UserDataProvider.class)
    public void UC1LoginWithEmptyCredentials(User user) {
        log.info("Starting UC1 test for user: {}, running test on thread: {}", user.getUserName(), Thread.currentThread().getId());

        LoginPage loginPage = new LoginPage();

        AbstractPage resultPage = loginPage.openApp()
                .inputLegitPassword(user)
                .inputUserName(user)
                .clearPassword()
                .clearName()
                .hitLoginButton();

        if (resultPage instanceof InventoryPage) {
            verifyInventoryPage(resultPage);
        } else if (resultPage instanceof LoginPage) {
            log.info("resultPage instanceof LoginPage");
            assertTrue(((LoginPage) resultPage).isErrorMessageDisplayed());
            assertEquals(((LoginPage) resultPage).getErrorMessage(), "Epic sadface: Username is required");
        }
    }

    @Test(dataProvider = "userProvider", dataProviderClass = UserDataProvider.class)
    public void UC2LoginWithEmptyPassword(User user) {
        log.info("Starting UC2 test for user: {}, running test on thread: {}", user.getUserName(), Thread.currentThread().getId());

        LoginPage loginPage = new LoginPage();

        AbstractPage resultPage = loginPage.openApp()
                .inputLegitPassword(user)
                .inputUserName(user)
                .clearPassword()
                .hitLoginButton();

        if (resultPage instanceof InventoryPage) {
            verifyInventoryPage(resultPage);
        } else if (resultPage instanceof LoginPage) {
            log.info("resultPage instanceof LoginPage");
            assertTrue(((LoginPage) resultPage).isErrorMessageDisplayed());
            assertEquals(((LoginPage) resultPage).getErrorMessage(), "Epic sadface: Password is required");
        }
    }

    @Test(dataProvider = "userProvider", dataProviderClass = UserDataProvider.class)
    public void UC3LoginWithLegitCredentials(User user) {
        log.info("Starting UC3 test for user: {}, running test on thread: {}", user.getUserName(), Thread.currentThread().getId());

        LoginPage loginPage = new LoginPage();

        AbstractPage resultPage = loginPage.openApp()
                .inputLegitPassword(user)
                .inputUserName(user)
                .hitLoginButton();

        if (resultPage instanceof InventoryPage) {
            verifyInventoryPage(resultPage);
        } else if (resultPage instanceof LoginPage) {
            log.info("resultPage instanceof LoginPage");

            // Expected error messages
            String epicSadfaceWrongPassword = "Epic sadface: Username and password do not match any user in this service";
            String epicSadfaceLockedOut = "Epic sadface: Sorry, this user has been locked out.";

            assertTrue(((LoginPage) resultPage).isErrorMessageDisplayed(), "Error message should be displayed");

            // The actual error message from the page
            String actualMessage = ((LoginPage) resultPage).getErrorMessage();

            // Check if the message is one of the expected ones
            if (actualMessage.equals(epicSadfaceWrongPassword)) {
                log.info("User provided wrong credentials: {}", actualMessage);
                Assert.fail("Test failed: Incorrect username or password provided when login was expected to succeed.");
            } else if (actualMessage.equals(epicSadfaceLockedOut)) {
                log.info("User is locked out: {}", actualMessage);
                assertEquals(actualMessage, epicSadfaceLockedOut, "Error message does not match expected for locked-out user!");
            } else {
                Assert.fail("Unexpected error message displayed: " + actualMessage);
            }
        }
    }

    private void verifyInventoryPage(AbstractPage resultPage) {
        assertTrue(resultPage instanceof InventoryPage, "Expected InventoryPage but got: " + resultPage.getClass().getSimpleName());
        log.info("Result page is InventoryPage");

        InventoryPage inventoryPage = (InventoryPage) resultPage;
        assertTrue(inventoryPage.isInventoryPageOpened(), "Inventory page did not open successfully");
        assertEquals(inventoryPage.getWelcomeMessage(), inventoryPage.getTitle(), "Welcome message and title do not match");
    }

}
