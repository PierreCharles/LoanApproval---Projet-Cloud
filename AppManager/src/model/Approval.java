package model;

public class Approval {

	/**
	 * Approval name
	 */
	private String name;
	
	/**
	 * Approval value
	 */
	private String manualResponse;

	/**
	 * Constructor of the class Bank account 
	 * 
	 * @param name
	 * @param manualResponse

	 */
	public Approval(String name, String manualResponse) 
	{
		super();
		this.name = name;
		this.manualResponse = manualResponse;

	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}


	public String getManualResponse() 
	{
		return manualResponse;
	}

	public void setManualResponse(String manualResponse) 
	{
		this.manualResponse = manualResponse;
	}
	
}
