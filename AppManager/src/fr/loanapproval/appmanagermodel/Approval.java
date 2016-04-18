package fr.loanapproval.appmanagermodel;

public class Approval {

	private String name;
	private String manual_Response;

	/**
	 * Getter of the name
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Setter of the name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter of manual_Response
	 * @return
	 */
	public String getManual_Response() {
		return this.manual_Response;
	}

	/**
	 * Setter of manual_Response
	 * @param manual_Response
	 */
	public void setManual_Response(String manual_Response) {
		this.manual_Response = manual_Response;
	}

	/**
	 * Constructor of one approval
	 * @param name
	 * @param manual_response
	 */
	public Approval(String name, String manual_response) {
		this.name = name;
		this.manual_Response = manual_response;
	}

}
