package com.epam.final_task.serhii_klymenko.service;

import com.epam.final_task.serhii_klymenko.model.User;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class UserDataProvider {

    private final static Logger log = LogManager.getLogger(UserDataProvider.class);

    private static final String TEST_DATA_FILE_PATH = "src/test/resources/test-data-users.json";

    @DataProvider(name = "userProvider", parallel = true)
    public static Object[][] userProvider() {
        log.info("Loading user test data from {}", TEST_DATA_FILE_PATH);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        Object[][] userData;

        try {
            jsonNode = objectMapper.readTree(new File(TEST_DATA_FILE_PATH));
            JsonNode usernamesNode = jsonNode.get("usernames");

            if (usernamesNode == null || !usernamesNode.isArray()) {
                log.error("Invalid JSON format: 'usernames' field is missing or not an array.");
                return new Object[0][0];
            }
            userData = new Object[usernamesNode.size()][1];//[iterations][1 user per iteration]

            Iterator<JsonNode> iterator = usernamesNode.iterator();
            int index = 0;
            while (iterator.hasNext()) {
                String username = iterator.next().asText();
                log.info("Loaded user: {}", username);
                userData[index++][0] = new User(username);
            }
        } catch (StreamReadException e) {
            log.error("Error reading JSON file. Possible format issue: {}", e.getMessage(), e);
            return new Object[0][0];
        } catch (IOException e) {
            log.error("Failed to load test data from file: {}", TEST_DATA_FILE_PATH, e);
            return new Object[0][0];
        }

        log.info("Successfully loaded {} users from test data.", userData.length);
        return userData;
    }
}
