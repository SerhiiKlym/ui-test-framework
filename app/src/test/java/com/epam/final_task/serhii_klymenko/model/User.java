package com.epam.final_task.serhii_klymenko.model;

public class User {
    private final String userName;
    private final String correctPassWord;
    private final String wrongPassWord;

    public User(String userName) {
        this.userName = userName;
        this.correctPassWord = "secret_sauce";
        this.wrongPassWord = "why did you fail me, brain?";
    }

    public String getUserName() {
        return userName;
    }

    public String getCorrectPassWord() {
        return correctPassWord;
    }
    public String getWrongPassWord() {
        return wrongPassWord;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + userName + '\'' +
                '}';
    }
}
