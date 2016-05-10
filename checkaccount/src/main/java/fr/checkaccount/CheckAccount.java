package fr.checkaccount;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import org.codehaus.jackson.JsonGenerationException;  
import org.codehaus.jackson.map.JsonMappingException;  
import org.codehaus.jackson.map.ObjectMapper;  

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("checkaccount")
public class CheckAccount {

	
	public final static int DATA_PRICE = 10000;
	
	/**
	 * Converter to JSON
	 */
	private ObjectMapper converterJson = new ObjectMapper();
	
	/**
	 * Method for check is an account is "low" or "hight" 
	 * @param price
	 * @return Response Json 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("checkrisk/{price}")
	public Response checkRisk(@PathParam("price") String price) 
	{
		int value = Integer.parseInt(price);
		try 
		{
			String output = null;
			if(value>DATA_PRICE)
				output = converterJson.writeValueAsString("hight");
			else
				output = converterJson.writeValueAsString("low");
			return Response.status(200).entity(output).build();
		} catch (Exception e) {
			String output = "{'error':'"+e.getMessage()+"'}";	
			return Response.status(204).entity(output).build();
		} 
	}
	
	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIt() {
    	return "Default checkaccount path";
    }
}
