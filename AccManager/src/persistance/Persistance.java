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
import model.BankAccount;

public class Persistance {
	
	/**
	 * The datastore Object
	 */
	private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	/**
	 * Method to persist a bank account in the Datastore
	 * 
	 * @param account
	 * 
	 * @throws Exception
	 */
	public void persist(BankAccount account) throws PersistanceAddException
	{
		try {
			Entity entityAccount = new Entity("account", account.getAccount());
			entityAccount.setProperty("lastName",account.getLastName());
			entityAccount.setProperty("firstName",account.getFirstName());
			entityAccount.setProperty("account",account.getAccount());
			entityAccount.setProperty("risk",account.getRisk());
			
			Date dateAdd = new Date();
			entityAccount.setProperty("dateAdd", dateAdd);
			
			datastore.put(entityAccount);
		} catch (Exception e) {
			throw new PersistanceAddException("Error in the insertion or update of the account");
		}
	}
	
	/**
	 * Method to delete an account with his Id
	 * 
	 * @param accountId
	 * 
	 * @throws PersistanceDeleteException
	 */
	public void deleteAccountById(String accountId) throws PersistanceDeleteException 
	{
		Key keyAccount = KeyFactory.createKey("account", accountId);
		
		try {
			datastore.delete(keyAccount);
		} catch (Exception e){
			throw new PersistanceDeleteException("Error when you try delete the account :" + accountId);
		}
		
	}
	
	/**
	 * Method to get an account with his Id
	 * 
	 * @param accountId
	 * 
	 * @return BankAccount
	 * 
	 * @throws PersistanceNotFoundException
	 */
	public BankAccount getAccountById(String accountId) throws PersistanceNotFoundException
	{
		Key keyAccount = KeyFactory.createKey("account", accountId);
		try {
			Entity entityAccount = datastore.get(keyAccount);
			
			return new BankAccount((String)entityAccount.getProperty("lastName"), 
								   (String)entityAccount.getProperty("firstName"), 
								   (String)entityAccount.getProperty("account"), 
								   (String)entityAccount.getProperty("risk"));
		} catch (Exception e) {
			throw new PersistanceNotFoundException("The account " + accountId + " can't be find");
		}	
	}
	
	/**
	 * Method to get all the accounts
	 * 
	 * @return List<BankAccount>
	 * 
	 * @throws PersistanceSelectException 
	 */
	public List<BankAccount> getAccounts() throws PersistanceSelectException, PersistanceNotFoundException
	{
		List<BankAccount> accountsList = new ArrayList<BankAccount>();
		
		Query query = new Query("account").addSort("dateAdd", SortDirection.DESCENDING);
		
        try {
			List<Entity> results = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
			
	        for (Entity result : results) {
	        	accountsList.add(new BankAccount((String)result.getProperty("lastName"), 
						   						(String)result.getProperty("firstName"), 
						   						(String)result.getProperty("account"), 
						   						(String)result.getProperty("risk")));	
	        }
	        
	        if (accountsList.size() == 0) {
	        	throw new PersistanceNotFoundException("There is nobody accounts actualy");
	        }
	        
	        return accountsList;
			
        } catch (Exception e) {
        	throw new PersistanceSelectException("An error attempt when you try to get all the accounts, maybe there is no accounts actually");
        }
	}	
}
