package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class BankAccount 
 */
@JsonIgnoreProperties(ignoreUnknown=true) 
public class BankAccount {

	/**
	 * Name of client
	 */
	@JsonProperty("lastName") private String lastName;
	
	/**
	 * First name of the client
	 */
	@JsonProperty("firstName") private String firstName;
	
	/**
	 * Account id
	 */
	@JsonProperty("account") private String account;
	
	/**
	 * Amount of the account
	 */
	@JsonProperty("amount") private float amount;
	
	/**
	 * Risk of the account value
	 */
	@JsonProperty("risk") private String risk;

	/**
	 * Constructor of the class Bank account FOR JSON
	 * 
	 * @param lastName
	 * @param firstName
	 * @param account
	 * @param amount
	 * @param risk
	 */
	@JsonCreator
	public BankAccount(@JsonProperty("lastName")String lastName, @JsonProperty("firstName")String firstName, @JsonProperty("account")String account, @JsonProperty("amount")String amount, @JsonProperty("risk")String risk) 
	{
		this.lastName = lastName;
		this.firstName = firstName;
		this.account = account;
		this.amount = Float.valueOf(amount.trim());
		this.risk = risk;
	}
	
	/**
	 * Constructor basic of the class Bank account
	 * 
	 * @param lastName
	 * @param firstName
	 * @param account
	 * @param amount
	 * @param risk
	 */
	public BankAccount(String lastName, String firstName, String account, float amount, String risk) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.account = account;
		this.amount = amount;
		this.risk = risk;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getAccount() 
	{
		return account;
	}

	public void setAccount(String account) 
	{
		this.account = account;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getRisk() 
	{
		return risk;
	}

	public void setRisk(String risk) 
	{
		this.risk = risk;
	}	
}
