package edu.gatech.cleanwater.Model;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Ryan on 4/2/2017.
 * Class that interacts with Firebase and checks inputs for validity
 */

public class FirebaseHelper {

    private static FirebaseAuth mAuth;

    /**
<<<<<<< HEAD
     * Submits a new water source report to the database
     * @param date the date of submission
     * @param reporter the name of the reporter
=======
     * submits a new water source report to the database
>>>>>>> origin/master
     * @param waterType the type of water
     * @param waterCondition the condition of the water
     * @param latitude the latitude of the water's location
     * @param longitude the longitude of the water's location
     * @return true if the report was submitted, false otherwise
     */
<<<<<<< HEAD
    public static boolean submitSourceReport(String date, String reporter, String waterType, String waterCondition, double latitude, double longitude) {
=======
    public static boolean submitSourceReport(String waterType, String waterCondition, double latitude, double longitude) {
>>>>>>> origin/master

        if (latitude > 90 || latitude < -90) {
            return false;
        }
        if (longitude > 180 || longitude < -180) {
            return false;
        }
        if("".equals(waterType)) {
            return false;
        }
        if("".equals(waterCondition)) {
            return false;
        }
        return true;
    }

    /**
     * submits a new water purity report to the database
     * @param latitude the latitude of the water's location
     * @param longitude the longitude of the water's location
     * @return true if the report was submitted, false otherwise
     */
<<<<<<< HEAD
    public static boolean submitPurityReport(String date, String reporter, int virus, int contaminant, double latitude, double longitude) {
        myRef = FirebaseDatabase.getInstance().getReference();
=======
    public static boolean submitPurityReport(double latitude, double longitude) {
>>>>>>> origin/master
        if (latitude > 90 || latitude < -90) {
            return false;
        }
        if (longitude > 180 || longitude < -180) {
            return false;
        }
        return true;
    }

    /**
     * Logs in an existing user
     * @param username the user's email
     * @param password the user's password
     * @return a String representation of if the login succeeded or failed
     */
    public static String loginUser(String username, String password) {
        if("".equals(username)) {
            return "bad username";
        }
        if("".equals(password)) {
            return "bad password";
        }
        return "good";
    }

    /**
     * Registers new user
     * @param username the user's email
     * @param password the user's password
     * @return a String representation of if the register succeeded or failed
     */
    public static String registerUser(String username, String password) {
        if("".equals(username)) {
            return "bad username";
        }
        if("".equals(password)) {
            return "bad password";
        }
        return "good";
    }
}
