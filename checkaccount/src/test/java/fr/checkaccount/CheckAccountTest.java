package fr.checkaccount;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.json.JSONException;
import org.json.JSONObject;

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
        final JSONObject responseMsg = target().path("checkaccount").request().get(JSONObject.class);
        JSONObject json = new JSONObject();
        json.put("test", "hello heroku");
        assertEquals(json, responseMsg);
    }
}
