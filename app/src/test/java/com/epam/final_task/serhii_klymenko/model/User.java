package com.epam.final_task.serhii_klymenko.model;

public class User {
    private final String userName;
    private final String correctPassWord;

    public User(String userName) {
        this.userName = userName;
        this.correctPassWord = "secret_sauce1";
    }

    public String getUserName() {
        return userName;
    }

    public String getCorrectPassWord() {
        return correctPassWord;
    }


    @Override
    public String toString() {
        return "User{" +
                "login='" + userName + '\'' +
                '}';
    }
}
