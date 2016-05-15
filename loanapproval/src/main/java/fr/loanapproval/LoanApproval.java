package fr.loanapproval;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
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
 * Root resource (exposed at "myresource" path)
 */
@Path("loanapproval")
public class LoanApproval {

	/**
	 * The max sold without asking risk
	 */
    public static final int SOLD = 10000;

    /**
     * AccManager url Service
     */
    public static final String URL_ACCMANAGER = "https://1-dot-accmanager-1294.appspot.com/rest/bankAccount";

    /**
     * AppManager url Service
     */
    public static final String URL_APPMANAGER = "http://1-dot-appmanager-1280.appspot.com/rest/approval";

    /**
     * CheckAccount url Service
     */
    public static final String URL_CHECKACCOUNT = "https://afternoon-everglades-21216.herokuapp.com/checkaccount";

    /**
     * Methode for check query credit with a firstName a lastName and a sold
     *
     * @param  lastName, firstName and a sold
     * 
     * @return Response Json 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("creditRequestByName")
    public Response creditRequestByName(String inputJSON) 
    {
        JSONObject objectPeople = (JSONObject) parser.parse(inputJSON);
        int idAccount = getIdFromAccManager((String) objectPeople.get("lastName"), (String) objectPeople.get("firstName"));
        
        return creditrequest(idAccount, (String) objectPeople.get("sold"));
    }

    /**
     * Methode for check query credit with an id and a sold
     * 
     * @param id account and sold
     * 
     * @return Response Json 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("creditRequestById")
    public Response creditRequestBtId(String inputJSON) 
    {
        JSONObject objectAccount = parser.parse(inputJSON);
        return creditrequest(json.get("idAccount"), json.get("sold"));
    }

    /**
     * Methode for check the query credit
     * 
     * @param id account and sold
     * 
     * @return Response Json 
     */
    public Response creditrequest(int idAcount, int sold)
    {
        String output = "";
        try 
        {
            if (sold<SOLD) {
                JSONObject objectAccount  =  getDataRequestFromService(URL_CHECKACCOUNT+"/checkrisk/"+idAccount);
                
                if (objectAccount.get("response") == "high") {
                    JSONObject objectAccount =  getDataRequestFromService(URL_APPMANAGER+"/"+idAccount);
                    String output = "{\"response\":\"" + objectAccount.get("manualResponse") + "\"}";
                } else {
                    String output = "{\"response\":\"approved\" , \"account\" : \" \"}";
                }   
            } else {
                JSONObject objectAccount = getDataRequestFromService(URL_APPMANAGER+"/"+idAccount);
                String output = "{\"response\":\"" + objectAccount.get("manualResponse") + "\"}";
            }    
            return Response.status(200).entity(output).build();
        } catch (Exception e) {
            String output = "{'error':'" + e.getMessage() + "'}";   
            return Response.status(204).entity(output).build();
        }
    }

    /**
     * Methode for get id of account with a lastName and a firstName from a web service AccManager
     * 
     * @param  lastName and the firstName
     * 
     * @return a idAccount
     */
    public int getIdFromAccManager(String lastName, String firstName)
    {
    	// A VERIFIER
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        WebResource webResourcePost = client.resource(URL_ACCMANAGER);
        response = webResourcePost.type("application/json").post(ClientResponse.class, (String) "{\"lastName\":\"" + lastName + "\",\"firstName\":\"" + firstName + "\"}");
        
        if (response.getStatus() != 200) {
        	throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        responseContent = response.getEntity(String.class);
        
        JSONObject account = (JSONObject) parser.parse(responseContent);
        
        return Integer.parseInt((String) account.get('account'));
        
    }

    /**
     * Methode for get Data from a web service by an Url
     * 
     * @param url to service
     * 
     * @return a JSON string
     */
    public JSONObject getDataRequestFromService(String urlService) throws ParseException
    {
            Client client = Client.create();
            WebResource webResource = client.resource(urlService);
            ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

            if (response.getStatus() != 200) {
               throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            
            String entity = response.getEntity(String.class);
            return (JSONObject) parser.parse(entity);
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
