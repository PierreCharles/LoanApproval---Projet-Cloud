package fr.loanapproval.appmanager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/approval")
public class AppManager {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBankAccount() {
		return "{\"message\" : \"Hello\"}";
	}

	
}
