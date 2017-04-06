
package edu.gatech.cleanwater;

import android.content.Context;

import com.google.firebase.FirebaseApp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.gatech.cleanwater.Model.FirebaseHelper;

import static org.junit.Assert.*;



/**
 * Created by Jeffrey on 4/5/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class RegisterUserTest {

    private FirebaseHelper fbh;

    private String use = "john smith";
    private String pass = "password";

    @Mock
    Context mockcontext;

    @Before
    public void setup() {
        FirebaseApp.initializeApp(mockcontext);
        fbh = new FirebaseHelper();
    }

    @Test
    public void testRegisterUser() {
//        String result = FirebaseHelper.registerUser(use, pass);
//        assertEquals("good", result);

        String empti = "";

        String badresult = fbh.registerUser(empti, pass);
        assertEquals("bad username", badresult);



    }

}
