package fr.loanapproval.appmanagermodel;

import java.util.ArrayList;
import java.util.List;

public class Approvals {

	private List<Approval> approvals = new ArrayList<Approval>();
	
    public List<Approval> getApprovals() {
        return this.approvals;
    }

    public Approval getApproval(int index) {
        return this.approvals.get(index);
    }
    
    public void setApproval(Approval approval){
    	this.approvals.set(this.approvals.size(), approval);
    }
	
}
