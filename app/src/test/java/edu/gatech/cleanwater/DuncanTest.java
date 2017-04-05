package edu.gatech.cleanwater;


import org.junit.Test;

import edu.gatech.cleanwater.Model.FirebaseHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the firebase submitPurityReport method
 */

public class DuncanTest {

    public void submitPurityReportTest() {

        assertTrue(FirebaseHelper.submitPurityReport(89, -181));
        assertFalse(FirebaseHelper.submitPurityReport(89, -179));
        assertFalse(FirebaseHelper.submitPurityReport(89, -89));

        assertTrue(FirebaseHelper.submitPurityReport(91, -181));
        assertTrue(FirebaseHelper.submitPurityReport(91, -179));
        assertTrue(FirebaseHelper.submitPurityReport(91, -89));

        assertTrue(FirebaseHelper.submitPurityReport(90, -180));
        assertFalse(FirebaseHelper.submitPurityReport(90, -179));
        assertFalse(FirebaseHelper.submitPurityReport(90, -89));






    }
}
