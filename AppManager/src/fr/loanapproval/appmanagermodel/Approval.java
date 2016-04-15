package fr.loanapproval.appmanagermodel;

public class Approval {

	private String name;
	private String manual_Response;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManual_Response() {
		return this.manual_Response;
	}

	public void setManual_Response(String manual_Response) {
		this.manual_Response = manual_Response;
	}

	public Approval(String name, String manual_response) {
		this.name = name;
		this.manual_Response = manual_response;
	}

}
