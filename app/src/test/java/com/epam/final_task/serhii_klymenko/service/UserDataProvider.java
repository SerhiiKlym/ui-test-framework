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
import java.util.Arrays;
import java.util.Iterator;

public class UserDataProvider {

    private final static Logger log = LogManager.getLogger(UserDataProvider.class);

    private static final String TEST_DATA_FILE_PATH = "src/test/resources/test-data-users.json";

    @DataProvider(name = "userProvider")
    public static Object[][] userProvider() {
        log.info("userProvider() method has been invoked... Loading userData....");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        Object[][] userData = null;

        try {
            jsonNode = objectMapper.readTree(new File(TEST_DATA_FILE_PATH));
            JsonNode usernamesNode = jsonNode.get("usernames");
            userData = new Object[usernamesNode.size()][1];//[iterations][1 user per iteration]

            Iterator<JsonNode> iterator = usernamesNode.iterator();
            int index = 0;
            while (iterator.hasNext()) {
                String username = iterator.next().asText();
                userData[index++][0] = new User(username);
            }
        } catch (StreamReadException e) {
            log.error("Error reading JSON file. Possible format issue: " + e.getMessage(), e);
        } catch (IOException e) {
            log.error("Error reading JSON file. Cannot find the file: " + e.getMessage(), e);
        }

        log.info("Object userData: " + Arrays.deepToString(userData));
        return userData;
    }
}
