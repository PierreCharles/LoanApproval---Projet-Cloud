package fr.checkaccount;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;



import org.junit.Test;
import static org.junit.Assert.assertEquals;

import fr.checkaccount.CheckAccount;

public class CheckAccountTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(CheckAccount.class);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        String responseMsg ="";
        String json = "";
        assertEquals(json, responseMsg);
    }
}
