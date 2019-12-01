package com.example.taskplanner2.Login;

public class LoginWrapper {

    public String username;
    public String password;

    public LoginWrapper(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginWrapper{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
