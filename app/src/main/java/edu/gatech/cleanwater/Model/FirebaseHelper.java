package edu.gatech.cleanwater.Model;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ryan on 4/2/2017.
 * Class that interacts with Firebase and checks inputs for validity
 */

public class FirebaseHelper {

    private static DatabaseReference myRef;
    private static FirebaseAuth mAuth;

    private static String status = "";

    public static boolean sumbitSourceReport(String date, String reporter, String waterType, String waterCondition, double latitude, double longitude) {

        myRef = FirebaseDatabase.getInstance().getReference();
        if (latitude > 90 || latitude < -90) {
            return false;
        }
        if (longitude > 180 || longitude < -180) {
            return false;
        }
        if(TextUtils.isEmpty(waterType)) {
            return false;
        }
        if(TextUtils.isEmpty(waterCondition)) {
            return false;
        }
        SourceReport report = new SourceReport(date, reporter, waterType, waterCondition, latitude, longitude);
        myRef.child("SourceReportList").push().setValue(report);
        return true;
    }

    public static boolean sumbitPurityReport(String date, String reporter, int virus, int contaminant, double latitude, double longitude) {
        myRef = FirebaseDatabase.getInstance().getReference();
        if (latitude > 90 || latitude < -90) {
            return false;
        }
        if (longitude > 180 || longitude < -180) {
            return false;
        }
        PurityReport report = new PurityReport(date, reporter, virus, contaminant, latitude, longitude);
        myRef.child("PurityReportListReportList").push().setValue(report);
        return true;
    }

    public static String loginUser(String username, String password) {
        status = "";
        if(TextUtils.isEmpty(username)) {
            return "bad username";
        }
        if(TextUtils.isEmpty(password)) {
            return "bad password";
        }
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    status = "good";
                } else {
                    status = "bad";
                }
            }
        });
        return status;
    }

    public static String registerUser(String username, String password) {
        status = "";
        if(TextUtils.isEmpty(username)) {
            return "bad username";
        }
        if(TextUtils.isEmpty(password)) {
            return "bad password";
        }
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    status = "good";
                } else {
                    status = "bad";
                }
            }
        });
        return status;
    }
}
