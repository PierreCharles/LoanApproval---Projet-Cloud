package fr.loanapproval.appmanager;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/approval")
public class AppManager {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Approval getTrackInJSON() {
		Approval approval = new Approval();
		approval.setName("Pierre");
		approval.setManual_Response("true");
		return approval;
	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Approval approval) {
		String result = "Approval saved : " + approval;
		return Response.status(201).entity(result).build();	
	}
	
}