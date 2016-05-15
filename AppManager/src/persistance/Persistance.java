package persistance;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;


import exceptions.PersistanceAddException;
import exceptions.PersistanceDeleteException;
import exceptions.PersistanceSelectException;
import exceptions.PersistanceNotFoundException;
import model.Approval;

/**
 * Class Persistance which allow to go some actions in the Datastore
 */
public class Persistance {
	
	/**
	 * The datastore Object
	 */
	private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	/**
	 * Method to persist an approval in the Datastore
	 * 
	 * @param approval
	 * 
	 * @throws Exception
	 */
	public void persist(Approval approval) throws PersistanceAddException
	{
		try {
			Entity entityApproval = new Entity("approval", approval.getId());
			entityApproval.setProperty("lastName",approval.getLastName());
			entityApproval.setProperty("firstName",approval.getFirstName());
			entityApproval.setProperty("id",approval.getId());
			entityApproval.setProperty("response",approval.getResponse());
			
			Date dateAdd = new Date();
			entityApproval.setProperty("dateAdd", dateAdd);
			
			datastore.put(entityApproval);
		} catch (Exception e) {
			throw new PersistanceAddException("Error in the insertion or update of the account");
		}
	}
	
	/**
	 * Method to delete an approval with his Id
	 * 
	 * @param approvalId
	 * 
	 * @throws PersistanceDeleteException
	 */
	public void deleteApprovalById(String approvalId) throws PersistanceDeleteException 
	{
		Key keyApproval = KeyFactory.createKey("approval", approvalId);
		
		try {
			datastore.delete(keyApproval);
		} catch (Exception e){
			throw new PersistanceDeleteException("Error when you try delete the account :" + approvalId);
		}
		
	}
	
	/**
	 * Method to get an approval with his Id
	 * 
	 * @param approvalId
	 * 
	 * @return Approval
	 * 
	 * @throws PersistanceNotFoundException
	 */
	public Approval getApprovalById(String approvalId) throws PersistanceNotFoundException
	{
		Key keyApproval = KeyFactory.createKey("approval", approvalId);
		try {
			Entity entityAccount = datastore.get(keyApproval);
			return new Approval((String)entityAccount.getProperty("lastName"), 
								   (String)entityAccount.getProperty("firstName"), 
								   (String)entityAccount.getProperty("id"), 
								   (String)entityAccount.getProperty("response"));
		} catch (Exception e) {
			throw new PersistanceNotFoundException("The approval " + approvalId + " can't be find");
		}	
	}
	
	/**
	 * Method to get all the approvals
	 * 
	 * @return List<Approval>
	 * 
	 * @throws PersistanceSelectException 
	 */
	public List<Approval> getApprovals() throws PersistanceSelectException, PersistanceNotFoundException
	{
		List<Approval> approvalsList = new ArrayList<Approval>();
		
		Query query = new Query("approval").addSort("dateAdd", SortDirection.DESCENDING);
		
        try {
			List<Entity> results = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
			
	        for (Entity result : results) {
	        	approvalsList.add(new Approval((String)result.getProperty("lastName"), 
						   						(String)result.getProperty("firstName"), 
						   						(String)result.getProperty("id"), 
						   						(String)result.getProperty("response")));	
	        }
	        
	        if (approvalsList.size() == 0) {
	        	throw new PersistanceNotFoundException("There is nobody approvals actualy");
	        }
	        
	        return approvalsList;
			
        } catch (Exception e) {
        	throw new PersistanceSelectException("An error attempt when you try to get all the approvals, maybe there is no approvals actually");
        }
	}	
}
