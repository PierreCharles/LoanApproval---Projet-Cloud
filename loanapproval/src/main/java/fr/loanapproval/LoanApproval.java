package fr.loanapproval;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.*;
import javax.ws.rs.client.Invocation.Builder;

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
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject objectPeople = (JSONObject) jsonParser.parse(inputJSON);
            String idAccount = getIdFromAccManager((String) objectPeople.get("lastName"), (String) objectPeople.get("firstName"));
            return creditrequest(idAccount, (String) objectPeople.get("sold"));
        } catch (Exception e) {
            String output = "{'error':'" + e.getMessage() + "'}";   
            return Response.status(204).entity(output).build();
        }

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
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject objectAccount = (JSONObject) jsonParser.parse(inputJSON);
            return creditrequest((String) objectAccount.get("idAccount"), (String) objectAccount.get("sold"));
        } catch (Exception e) {
            String output = "{'error':'" + e.getMessage() + "'}";   
            return Response.status(204).entity(output).build();
        }
    }

    /**
     * Methode for check the query credit
     * 
     * @param id account and sold
     * 
     * @return Response Json 
     */
    public Response creditrequest(String idAccount, String sold)
    {
        String output = "";
        int amount = Integer.parseInt(sold);   
        try 
        {

            JSONParser jsonParser = new JSONParser();
            if (amount<SOLD) {
                JSONObject objectAccount = (JSONObject) jsonParser.parse(getRequestUrl(URL_CHECKACCOUNT+"/checkrisk/"+idAccount));
                if (objectAccount.get("response") == "high") 
                {
                    JSONObject objectApproval = (JSONObject) jsonParser.parse(getRequestUrl(URL_APPMANAGER+"/getApproval/"+idAccount));
                    output = "{\"response\":\"" + objectApproval.get("response") +"\"}";
                } else {
                    output = "{\"response\":\"approved\"}";
                }   
            } else {
                    JSONObject objectApproval = (JSONObject) jsonParser.parse(getRequestUrl(URL_APPMANAGER+"/getApproval/"+idAccount));
                    output = "{\"response\":\"" + objectApproval.get("response") +"\"}";
            }    
            return Response.status(200).entity(output).build();
        } catch (Exception e) {
            output = "{'error':'" + e.getMessage() + "'}";   
            return Response.status(204).entity(output).build();
        }
    }


    /**
     * Methode for get Data from service with an URL
     * 
     * @param String url
     * 
     * @return String 
     */
    public String getRequestUrl(String urlTargetService)
    {
        try {
            return ClientBuilder.newClient().target(urlTargetService).request().get(String.class);
        } catch (Exception e) {
            return "{'error':'" + e.getMessage() + "'}";   
        }
    }

    /**
     * Methode for get id of account with a lastName and a firstName from a web service AccManager
     * 
     * @param  lastName and the firstName
     * 
     * @return a idAccount
     */
    public String getIdFromAccManager(String lastName, String firstName)
    {

       String urlTargetService = URL_ACCMANAGER+"/getAccountByProperty/";
       Client client = ClientBuilder.newClient();
       WebTarget webTarget = client.target(urlTargetService);
       String jsonString = "{\"firstName\":\""+firstName+"\",\"lastName\":\""+lastName+"\"}";
       Builder builder = webTarget.request();
       Response response = builder.post(Entity.json(jsonString));
       return response.readEntity(String.class);
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
