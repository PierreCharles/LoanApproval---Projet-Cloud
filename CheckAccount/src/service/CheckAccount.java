package service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.appengine.repackaged.org.codehaus.jackson.JsonGenerationException;
import com.google.appengine.repackaged.org.codehaus.jackson.map.JsonMappingException;
import com.google.appengine.repackaged.org.codehaus.jackson.map.ObjectMapper;

import exceptions.PersistanceAddException;
import exceptions.PersistanceDeleteException;
import exceptions.PersistanceNotFoundException;
import model.BankAccount;
import persistance.Persistance;

@Path("/checkAccount")
public class CheckAccount 
{
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
	@Path("check/{price}")
	public Response check(@PathParam("price") String price) 
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
	
}
