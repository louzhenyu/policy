package net.jinri.auto.policy.pojos;

import java.util.List;

public class PolicyRet {
	private PolicyIn policy;
	private List<PolicyBasic> policyBasics;
	public PolicyIn getPolicy() {
		return policy;
	}
	public void setPolicy(PolicyIn policy) {
		this.policy = policy;
	}
	public List<PolicyBasic> getPolicyBasics() {
		return policyBasics;
	}
	public void setPolicyBasics(List<PolicyBasic> policyBasics) {
		this.policyBasics = policyBasics;
	}	
}
