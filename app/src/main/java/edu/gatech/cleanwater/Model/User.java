package edu.gatech.cleanwater.Model;

/**
 * Created by Ryan on 2/20/2017.
 * contains all the information for a specific User
 */

public class User {

    private String name;
    private String address;
    private String userType;

    /**
     * Initializes a new User with the default information
     */
    public User() {
        name = "Bob Waters";
        address = "Everywhere";
        userType = UserType.USER.name();
    }

    /**
     * Initializes a new User with the inputted information
     * @param name The User's name
     * @param address The User's address
     * @param userType The User's account type
     */
    public User(String name, String address, String userType) {
        this.name = name;
        this.address = address;
        this.userType = userType;
    }
}
