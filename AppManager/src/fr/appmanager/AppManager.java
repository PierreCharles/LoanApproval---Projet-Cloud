package fr.appmanager;

import java.awt.PageAttributes.MediaType;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

import fr.appmanager.Approval;

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

		return null;
	}

}