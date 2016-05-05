package fr.accManager.model;

public class BankAccount {

	/**
	 * Name of client
	 */
	private String lastName;
	
	/**
	 * First name of the client
	 */
	private String firstName;
	
	/**
	 * Account id
	 */
	private String account;
	
	/**
	 * Risk of the account value
	 */
	private String risk;

	/**
	 * Constructor of the class Bank account 
	 * 
	 * @param lastName
	 * @param firstName
	 * @param account
	 * @param risk
	 */
	public BankAccount(String lastName, String firstName, String account, String risk) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.account = account;
		this.risk = risk;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}
	
}
