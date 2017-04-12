package edu.gatech.cleanwater;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cleanwater.Model.FirebaseHelper;

import static org.junit.Assert.assertEquals;


/**
 * Created by Jeffrey Lin on 4/5/2017.
 */

public class JeffreyTest {

    private String user;
    private String pass;
    private String empty;

    @Before
    public void setup() {
        user = "john smith";
        pass = "password";
        empty = "";
    }

    @Test
    public void testRegisterUser() {
        assertEquals("good", FirebaseHelper.registerUser(user, pass));

        assertEquals("bad username", FirebaseHelper.registerUser(empty, pass));

        assertEquals("bad password", FirebaseHelper.registerUser(user, empty));

        assertEquals("bad username", FirebaseHelper.registerUser(empty, empty));
    }
}
