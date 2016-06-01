package net.jinri.auto.policy.pojos;

import java.io.Serializable;

public class MqMsg implements Serializable{
	private String policyType;
	private String policyOperation;
	private PolicyIn policy;
	private PolicyBasic policyBasic;
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String getPolicyOperation() {
		return policyOperation;
	}
	public void setPolicyOperation(String policyOperation) {
		this.policyOperation = policyOperation;
	}
	public PolicyIn getPolicy() {
		return policy;
	}
	public void setPolicy(PolicyIn policy) {
		this.policy = policy;
	}
	public PolicyBasic getPolicyBasic() {
		return policyBasic;
	}
	public void setPolicyBasic(PolicyBasic policyBasic) {
		this.policyBasic = policyBasic;
	}
}
