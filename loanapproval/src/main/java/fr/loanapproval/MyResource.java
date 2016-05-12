package fr.loanapproval;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 */


@Path("myresource")
public class MyResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("checkrisk/{idAccount}")
    public Response checkRisk(@PathParam("idAccount") String idAccount) 
    {
        try {
            String output = "";
            return Response.status(200).entity(output).build();

        } catch (Exception e) {
            String output = "{\"error\": \" "+e.getMessage()+" \"}";   
            return Response.status(204).entity(output).build();
        }

    }


    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIt() {
        return "";
    }
}
