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
import model.Approval;
import persistance.Persistance;

@Path("/approval")
public class AppManager 
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
	 * Method to get all the approval from the web
	 * 
	 * @return Response with JSON datas
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getApprovals")
	public Response getApproval() 
	{
		try {
			List<Approval> listApprovals = persistance.getApprovals();
			String output = converterJson.writeValueAsString(listApprovals);		
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
	 * Method to get an approval with his Id from the web
	 * 
	 * @param idApproval
	 * 
	 * @return Response Json 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getApproval/{idApproval}")
	public Response getApproval(@PathParam("idApproval") String idApproval) 
	{
		try {
			Approval approval = (Approval)persistance.getApprovalById(idApproval);
			String output = converterJson.writeValueAsString(approval);
			return Response.status(200).entity(output).build();
		} catch (Exception e) {
			String output = "{'error':'"+e.getMessage()+"'}";	
			return Response.status(204).entity(output).build();
		} 
	}
	
	/**
	 * Method to add an approval from the web
	 * 
	 * @param approval
	 * 
	 * @return Response
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("createApproval")
	public Response createApproval(Approval approval) 
	{
		try {
			persistance.persist(approval);
			String output = "{'message':'The approval was correctly created'}";
			return Response.status(200).entity(output).build();
		} catch (PersistanceAddException e) {
			String output = "{'error':'" + e.getMessage() + "'}";
			return Response.status(500).entity(output).build();
		}
	}
	
	/**
	 * Method to update an approval with the PUT Http verbs 
	 * 
	 * @param approval
	 * 
	 * @return Response
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("updateApproval")
	public Response updateApprovalHttpMethod(Approval approval)
	{
		try {
			persistance.persist(approval);
			String output = "{'message':'The approval was correctly created'}";
			return Response.status(200).entity(output).build();
		} catch (PersistanceAddException e) {
			String output = "{'error':'" + e.getMessage() + "'}";
			return Response.status(500).entity(output).build();
		}
	}
	
	/**
	 * Method to update an approval with the normal Post verbs HTTP 
	 * 
	 * @param aproval
	 * 
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("updateApproval")
	public Response updateApproval(Approval approval)
	{
		try {
			persistance.persist(approval);
			String output = "{'message':'The approval was correctly created'}";
			return Response.status(200).entity(output).build();
		} catch (PersistanceAddException e) {
			String output = "{'error':'" + e.getMessage() + "'}";
			return Response.status(500).entity(output).build();
		}
	}
	
	/**
	 * Method to delete an approval with his Id from the web
	 * 
	 * @param idApproval
	 * 
	 * @return Response
	 */
	@GET
	@Path("deleteApproval/{idApproval}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteApproval(@PathParam("idApproval") String idApproval)
	{
		try {
			persistance.deleteApprovalById(idApproval);
			String output = "{'message':'The approval :" + idApproval + " has been deleted correctly'}";
			return Response.status(200).entity(output).build();
		} catch (PersistanceDeleteException e) {
			String output = "{'error':'" + e.getMessage() + "'}";
			return Response.status(204).entity(output).build();
		}
	}
	
	/**
	 * Method to delete an approval with his Id from an DELETE verb http web
	 * 
	 * @param idApproval
	 * 
	 * @return Response
	 */
	@DELETE
	@Path("deleteApproval/{idApproval}")
	public Response deleteApprovalMethodHttp(@PathParam("idApproval") String idApproval)
	{
		try {
			persistance.deleteApprovalById(idApproval);
			String output = "{'message':'The acccount :" + idApproval + " has been deleted correctly'}";
			return Response.status(200).entity(output).build();
		} catch (PersistanceDeleteException e) {
			String output = "{'error':'" + e.getMessage() + "'}";
			return Response.status(204).entity(output).build();
		}
	}	
}
