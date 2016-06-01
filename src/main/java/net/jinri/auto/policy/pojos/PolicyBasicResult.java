package net.jinri.auto.policy.pojos;

import java.util.List;

public class PolicyBasicResult {
	private long count;
	private List<PolicyBasic> policyBasics;
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<PolicyBasic> getPolicyBasics() {
		return policyBasics;
	}
	public void setPolicyBasics(List<PolicyBasic> policyBasics) {
		this.policyBasics = policyBasics;
	}	
}
