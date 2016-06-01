package net.jinri.auto.policy.pojos;

import java.util.List;

public class PolicyResult {
	private long count;
	private List<PolicyRet> policyRets;
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<PolicyRet> getPolicyRets() {
		return policyRets;
	}
	public void setPolicyRets(List<PolicyRet> policyRets) {
		this.policyRets = policyRets;
	}	
}
