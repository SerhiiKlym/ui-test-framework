package com.epam.final_task.serhii_klymenko.stepdefinitions;

import com.epam.final_task.serhii_klymenko.model.User;
import com.epam.final_task.serhii_klymenko.page.AbstractPage;
import com.epam.final_task.serhii_klymenko.page.InventoryPage;
import com.epam.final_task.serhii_klymenko.page.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import static com.epam.final_task.serhii_klymenko.test.TestUtilMethods.verifyInventoryPage;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginSteps {

    private final static Logger log = LogManager.getLogger(LoginSteps.class);
    LoginPage loginPage = new LoginPage();
    AbstractPage abstractPage;
    User user;

    @Given("User is on the login page")
    public void user_is_on_login_page() {
        log.info("Navigating to the login page (cucu)...");
        loginPage.openApp();
    }

    @When("User enters username {string}")
    public void user_enters_username(String username) {
        log.info("Entering username: {}", username);
        user = new User(username);
        loginPage.inputUserName(user);
    }

    @And("User enters password for user {string}")
    public void user_enters_password(String username) {
        log.info("Entering password for user: {}", username);
        user = new User(username);
        loginPage.inputLegitPassword(user);
    }

    @And("User clears password")
    public void user_clears_password() {
        log.info("Clearing password field...");
        loginPage.clearPassword();
    }

    @And("User clears username")
    public void user_clears_username() {
        log.info("Clearing username field...");
        loginPage.clearName();
    }

    @And("User clicks on login button")
    public void user_clicks_login() {
        log.info("Clicking the login button...");
        abstractPage = loginPage.hitLoginButton();
    }

    @Then("User should be shown an error message 'no username'")
    public void user_is_shown_error_nousername() {
        if (abstractPage instanceof InventoryPage) {
            log.info("Verifying error message for missing username...");
            verifyInventoryPage(abstractPage);
        } else if (abstractPage instanceof LoginPage) {
            log.info("resultPage instanceof LoginPage");
            assertTrue(((LoginPage) abstractPage).isErrorMessageDisplayed());
            assertEquals(((LoginPage) abstractPage).getErrorMessage(), "Epic sadface: Username is required");
        }
    }

    @Then("User should be shown an error message 'no password'")
    public void user_is_shown_error_nopassword() {
        if (abstractPage instanceof InventoryPage) {
            log.info("Verifying error message for missing password...");
            verifyInventoryPage(abstractPage);
        } else if (abstractPage instanceof LoginPage) {
            log.info("resultPage instanceof LoginPage");
            assertTrue(((LoginPage) abstractPage).isErrorMessageDisplayed());
            assertEquals(((LoginPage) abstractPage).getErrorMessage(), "Epic sadface: Password is required");
        }
    }

    @Then("User should be redirected to inventory page")
    public void user_is_redirected_to_inventory() {
        if (abstractPage instanceof InventoryPage) {
            verifyInventoryPage(abstractPage);
        } else if (abstractPage instanceof LoginPage) {
            log.info("resultPage instanceof LoginPage");

            // Expected error messages
            String epicSadfaceWrongPassword = "Epic sadface: Username and password do not match any user in this service";
            String epicSadfaceLockedOut = "Epic sadface: Sorry, this user has been locked out.";

            assertTrue(((LoginPage) abstractPage).isErrorMessageDisplayed(), "Error message should be displayed");

            // The actual error message from the page
            String actualMessage = ((LoginPage) abstractPage).getErrorMessage();

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




}
