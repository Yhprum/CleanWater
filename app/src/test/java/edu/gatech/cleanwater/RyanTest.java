package edu.gatech.cleanwater;

import org.junit.Test;

import edu.gatech.cleanwater.Model.FirebaseHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the FirebaseHelper.submitSourceReport method.
 */

public class RyanTest {
    @Test
    public void testSubmitSourceReport() {

        String waterType = "non empty string";
        String waterCondition = "non empty string";
        double latitude = 90;
        double longitude = 180;

        assertTrue(FirebaseHelper.submitSourceReport(waterType, waterCondition, latitude, longitude));

        waterType = "";
        assertFalse(FirebaseHelper.submitSourceReport(waterType, waterCondition, latitude, longitude));

        waterType = "non empty String";
        waterCondition = "";
        assertFalse(FirebaseHelper.submitSourceReport(waterType, waterCondition, latitude, longitude));

        waterCondition = "non empty String";
        latitude = -91;
        assertFalse(FirebaseHelper.submitSourceReport(waterType, waterCondition, latitude, longitude));

        latitude = 91;
        assertFalse(FirebaseHelper.submitSourceReport(waterType, waterCondition, latitude, longitude));

        latitude = 0;
        longitude = -181;
        assertFalse(FirebaseHelper.submitSourceReport(waterType, waterCondition, latitude, longitude));

        longitude = 181;
        assertFalse(FirebaseHelper.submitSourceReport(waterType, waterCondition, latitude, longitude));
    }
}
