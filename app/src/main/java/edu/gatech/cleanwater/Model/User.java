package edu.gatech.cleanwater.Model;

/**
 * Created by Ryan on 2/20/2017.
 */

public class User {

    private String username;
    private String password;
    private UserType type;

    public User(String username, String password, UserType type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }
}
