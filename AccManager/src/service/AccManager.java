package service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import persistance.Persistance;

@Path("/bankAccount")
public class AccManager {
	
	private Persistance persistance = new Persistance();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getAccounts")
	public String getBankAccount() 
	{
		return "{\"message\" : \"TEST\"}";
	}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getAccount/{idAccount}")
	public Response getAccount(@PathParam("idAccount") String idAccount) 
	{
		return null;
	}
	
	@POST
	@Path("createAccount")
	public Response createAccount(MultivaluedMap<String, String> formParams) 
	{
		return null;
	}
	
	@PUT
	@Path("updateAccount")
	public Response updateAccountHttpMethod(MultivaluedMap<String, String> formParams)
	{
		return null;
	}
	
	@POST
	@Path("updateAccount")
	public Response updateAccount(MultivaluedMap<String, String> formParams)
	{
		return null;
	}
	

	@GET
	@Path("deleteAccount/{idAccount}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteAccount(@PathParam("idAccount") String idAccount)
	{
		return "{'idAccount':'"+idAccount+"'}";
	}
	
	@DELETE
	@Path("deleteAccount/{idAccount}")
	public Response deleteAccountMethodHttp(@PathParam("idAccount") String idAccount)
	{
		return null;
	}
	
	
}
