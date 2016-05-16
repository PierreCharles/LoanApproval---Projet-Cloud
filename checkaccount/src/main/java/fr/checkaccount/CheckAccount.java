package fr.checkaccount;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import javax.ws.rs.client.ClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

/**
 * Root resource (exposed at "checkaccount" path)
 */
@Path("checkaccount")
public class CheckAccount {

	/**
	 * Url of the acc manager service
	 */
	public static final String URL_ACCMANAGER = "https://1-dot-accmanager-1294.appspot.com/rest/bankAccount/getAccount";

	/**
	 * Methode for check is an account is "low" or "hight" -> Call AccManager service
	 * 
	 * @param idAccount
	 * 
	 * @return Response Json {"responses" : risk} 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("checkrisk/{idAccount}")
	public Response checkRisk(@PathParam("idAccount") String idAccount) 
	{
       	try {
            JSONParser jsonParser = new JSONParser();
            String urlTargetService = URL_ACCMANAGER+"/"+idAccount;
            String response = ClientBuilder.newClient().target(urlTargetService).request().get(String.class);
            System.out.println(response);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
            System.out.println(jsonObject.get("risk"));
			String output = "{\"response\":\""+jsonObject.get("risk")+"\"}";
			return Response.status(200).entity(output).build();
	    } catch (Exception e) {
	  		String output = "{'error':'"+e.getMessage()+"'}";	
			return Response.status(204).entity(output).build();
	    }
	}

	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     *
     * to the client as "json" media type.
     *
     * @return String that will be returned as a json response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIt() {
    	return "";
    }
}
