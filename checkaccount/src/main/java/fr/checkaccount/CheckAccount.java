package fr.checkaccount;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

import org.codehaus.jackson.JsonGenerationException;  
import org.codehaus.jackson.map.JsonMappingException;  
import org.codehaus.jackson.map.ObjectMapper;  

/**
 * Root resource (exposed at "checkaccount" path)
 */
@Path("checkaccount")
public class CheckAccount {

	public static final String urlAccManager = "http://1-dot-accmanager-1294.appspot.com/getAccount/";

	/**
	 * Converter to JSON
	 */
	private ObjectMapper converterJson = new ObjectMapper();
	
	/**
	 * Method for check is an account is "low" or "hight" -> Call AccManager service
	 * @param risk
	 * @return Response Json 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("checkrisk/{idAccount}")
	public Response checkRisk(@PathParam("idAccount") String price) 
	{
		try 
		{
			int id = Integer.parseInt(idAccount);
			JSONObject json = readJsonFromUrl(urlAccManager+idAccount);
			JSONObject output = new JSONObject();
			json.put(response, json.get("risk"));
			return Response.status(200).entity(output).build();s
		} catch (Exception e) {
			String output = "{'error':'"+e.getMessage()+"'}";	
			return Response.status(204).entity(output).build();
		} 
	}
	
	
	  private static String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
	  }

	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		    InputStream is = new URL(url).openStream();
		    try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      String jsonText = readAll(rd);
		      JSONObject json = new JSONObject(jsonText);
		      return json;
		    } finally {
		      is.close();
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
    	return "";
    }
}
