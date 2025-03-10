package com.epam.final_task.serhii_klymenko.service;

import com.epam.final_task.serhii_klymenko.model.User;

public class UserCreator {

    public static User userCreation() {
        return new User("log", "pass");
    }

}
