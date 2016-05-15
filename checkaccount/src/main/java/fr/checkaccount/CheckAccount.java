package fr.checkaccount;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

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

	public static final String URL_ACCMANAGER = "https://1-dot-accmanager-1294.appspot.com/rest/bankAccount/getAccount";

	/**
	 * Methode for check is an account is "low" or "hight" -> Call AccManager service
	 * @param idAccount
	 * @return Response Json {"responses" : risk} 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("checkrisk/{idAccount}")
	public Response checkRisk(@PathParam("idAccount") String idAccount) 
	{
       	try {
       		int id = Integer.parseInt(idAccount);

			//JSONObject json =  getDataRequestFromService(URL_ACCMANAGER+"/"+id);
			//String output = "{\"response\":\""+json.get("risk")+"\"}";

            String output = "{\"response\":\"test\"}";
			return Response.status(200).entity(output).build();

	    } catch (Exception e) {
	  		String output = "{'error':'"+e.getMessage()+"'}";	
			return Response.status(204).entity(output).build();
	    }
	}

	/**
     * Methode for get Data from a web service by an Url
     * @param url to service
     * @return a JSON string
     */
    public JSONObject getDataRequestFromService(String urlService) throws ParseException, RuntimeException
    {
            Client client = Client.create();
            WebResource webResource = client.resource(urlService);
            ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

            if (response.getStatus() != 200) 
               throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());

            String entity = response.getEntity(String.class);
            return (JSONObject) new JSONParser().parse(entity);
    }
	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "json" media type.
     * @return String that will be returned as a json response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIt() {
    	return "";
    }
}
