package fr.loanapproval;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("loanapproval")
public class LoanApproval {

    public static final int SOLD = 10000;

    public static final int URL_APPMANAGER = "";

    public static final int URL_CHECKACCOUNT = "https://afternoon-everglades-21216.herokuapp.com/checkaccount";

    /**
     * Methode for check is an account is "low" or "hight" -> Call AccManager service
     * @param idAccount
     * @return Response Json {"responses" : risk} 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("creditrequest/{idAccount}/{soldAccount}")
    public Response creditRequest(@PathParam("idAccount") String idAccount, @PathParam("soldAccount") String soldAccount) 
    {
        try {
            int id = Integer.parseInt(idAccount);
            int sold = Integer.parseInt(soldAccount);
            if(sold<SOLD)
            {
                JSONObject json  =  getDataRequestFromService(URL_CHECKACCOUNT+"/checkrisk/"+id);
                
                if(json.get("response")=="high"){
                    JSONObject json =  getDataRequestFromService(URL_APPMANAGER+"/"+id));
                    String output = "{\"response\":\""+json.get("manualResponse")+"\"}";
                } else {
                    String output = "{\"response\":\"approved\" , \"account\" : \" \"}";
                }   
            } else {
                JSONObject json  getDataRequestFromService(URL_APPMANAGER+"/"+id));
                String output = "{\"response\":\""+json.get("manualResponse")+"\"}";
            }    
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
    private JSONObject getDataRequestFromService(String urlService){
            Client client = Client.create();
            WebResource webResource = client.resource(urlService);
            ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

            if (response.getStatus() != 200) 
               throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());

            String entity = response.getEntity(String.class);
            JSONObject json =  (JSONObject) new JSONParser().parse(entity);
            return json;
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
