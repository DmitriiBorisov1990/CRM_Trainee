package com.andersen.borisov.servise;

import com.andersen.borisov.model.User;

public class UserCreator {

    public static final String TESTDATA_USER_NAME = "testdata.user.name";
    public static final String TESTDATA_USER_PASSWORD = "testdata.user.password";

    public static User withCredentialsFromProperty() {
        User userDefault = new User(TestDataReader.getTestData(TESTDATA_USER_NAME),TestDataReader.getTestData(TESTDATA_USER_PASSWORD));
        if(userDefault.getUserName().isEmpty()){
            userDefault = new User("huntflow-test-16@andersenlab.com","159753CFThn");
        }
        return userDefault;
    }

    public static User withEmptyUsername() {
        return new User("", TestDataReader.getTestData(TESTDATA_USER_PASSWORD));
    }

    public static User withEmptyPassword() {
        return new User(TestDataReader.getTestData(TESTDATA_USER_NAME), "");
    }

    public static User withEmptyUsernameAndPassword() {
        return new User("", "");
    }
}
