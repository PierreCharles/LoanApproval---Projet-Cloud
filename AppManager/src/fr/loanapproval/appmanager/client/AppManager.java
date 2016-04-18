package fr.loanapproval.appmanager.client;

import java.net.URI;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import fr.loanapproval.appmanagermodel.Approval;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
@Path("/approval")
public class AppManager {

	private Random rand;
	private DatastoreService datastore;
	
	public AppManager() {
		this.rand = new Random();
		this.datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	public void deleteApproval() {

	}
	
	@GET
	@Produces("text/html")
	public Response listApproval(){	
		StringBuffer str = new StringBuffer();
		str.append("<!doctype html><html><body>");
		Query q = new Query("Approval");
		PreparedQuery pq = this.datastore.prepare(q);
		for(Entity result : pq.asIterable()){
			String Name = (String) result.getProperty("Name");
			String Manual_Response = (String) result.getProperty("Manual_Response");
			str.append(String.format("<p>%s %s</p>", Name, Manual_Response)); 
		}
		return Response.ok(str.toString(), MediaType.TEXT_HTML).build();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addApproval(Approval approval){
		Entity entity = new Entity("Approval", rand.nextInt());
		entity.setProperty("Name", approval.getName());
		entity.setProperty("Manual_Response", approval.getManual_Response());
		try{
			this.datastore.put(entity);
			return Response.created(new URI("http://appmanager-1280.appspot.com/rest/approval/"+entity.getKey())).build();
		} catch (Exception e){
			return Response.serverError().build();
		}
	}
	
}