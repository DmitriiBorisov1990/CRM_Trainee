package model;

import java.util.Objects;

public class User {

    private String userEmail;
    private String password;

    public User(String userName, String password) {
        this.userEmail = userName;
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userEmail, user.userEmail) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEmail, password);
    }
}
