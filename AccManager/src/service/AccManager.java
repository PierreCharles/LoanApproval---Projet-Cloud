package service;

import java.io.IOException;
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

@Path("/bankAccount")
public class AccManager 
{
	
	/**
	 * Object Persistance for add / delete / get / update in database
	 */
	private Persistance persistance = new Persistance();
	
	/**
	 * Converter to JSON
	 */
	private ObjectMapper converterJson = new ObjectMapper();
	
	/**
	 * Method to get all the bank accounts from the web
	 * 
	 * @return Response with JSON datas
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getAccounts")
	public Response getBankAccount() 
	{
		try {
			List<BankAccount> listAccounts = persistance.getAccounts();
			String output = converterJson.writeValueAsString(listAccounts);		
			return Response.status(200).entity(output).build();
		} catch (PersistanceNotFoundException noFound) {
			String output = "'error':'" + noFound.getMessage() + "'";
			return Response.status(204).entity(output).build();
		} catch (Exception select) {
			String output = "'error':'" + select.getMessage() + "'";
			return Response.status(500).entity(output).build();
		}
	}	
	
	/**
	 * Method to get an account with his Id from the web
	 * 
	 * @param idAccount
	 * 
	 * @return Response Json 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getAccount/{idAccount}")
	public Response getAccount(@PathParam("idAccount") String idAccount) 
	{
		try {
			BankAccount account = (BankAccount)persistance.getAccountById(idAccount);
			String output = converterJson.writeValueAsString(account);
			return Response.status(200).entity(output).build();
		} catch (Exception e) {
			String output = "{'error':'"+e.getMessage()+"'}";	
			return Response.status(204).entity(output).build();
		} 
	}
	
	/**
	 * Method to add an account from the web
	 * 
	 * @param account
	 * 
	 * @return Response
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("createAccount")
	public Response createAccount(BankAccount account) 
	{
		try {
			persistance.persist(account);
			String output = "{'message':'The account was correctly created'}";
			return Response.status(200).entity(output).build();
		} catch (PersistanceAddException e) {
			String output = "{'error':'" + e.getMessage() + "'}";
			return Response.status(500).entity(output).build();
		}
	}
	
	/**
	 * Method to update an account with the PUT Http verbs 
	 * 
	 * @param account
	 * 
	 * @return Response
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("updateAccount")
	public Response updateAccountHttpMethod(BankAccount account)
	{
		try {
			persistance.persist(account);
			String output = "{'message':'The account was correctly created'}";
			return Response.status(200).entity(output).build();
		} catch (PersistanceAddException e) {
			String output = "{'error':'" + e.getMessage() + "'}";
			return Response.status(500).entity(output).build();
		}
	}
	
	/**
	 * Method to update an account with the normal Post verbs HTTP 
	 * 
	 * @param account
	 * 
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("updateAccount")
	public Response updateAccount(BankAccount account)
	{
		try {
			persistance.persist(account);
			String output = "{'message':'The account was correctly created'}";
			return Response.status(200).entity(output).build();
		} catch (PersistanceAddException e) {
			String output = "{'error':'" + e.getMessage() + "'}";
			return Response.status(500).entity(output).build();
		}
	}
	
	/**
	 * Method to delete an account with his Id from the web
	 * 
	 * @param idAccount
	 * 
	 * @return Response
	 */
	@GET
	@Path("deleteAccount/{idAccount}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAccount(@PathParam("idAccount") String idAccount)
	{
		try {
			persistance.deleteAccountById(idAccount);
			String output = "{'message':'The acccount :" + idAccount + " has been deleted correctly'}";
			return Response.status(200).entity(output).build();
		} catch (PersistanceDeleteException e) {
			String output = "{'error':'" + e.getMessage() + "'}";
			return Response.status(204).entity(output).build();
		}
	}
	
	/**
	 * Method to delete an account with his Id from an DELETE verb http web
	 * 
	 * @param idAccount
	 * 
	 * @return Response
	 */
	@DELETE
	@Path("deleteAccount/{idAccount}")
	public Response deleteAccountMethodHttp(@PathParam("idAccount") String idAccount)
	{
		try {
			persistance.deleteAccountById(idAccount);
			String output = "{'message':'The acccount :" + idAccount + " has been deleted correctly'}";
			return Response.status(200).entity(output).build();
		} catch (PersistanceDeleteException e) {
			String output = "{'error':'" + e.getMessage() + "'}";
			return Response.status(204).entity(output).build();
		}
	}	
}
