package net.jinri.auto.policy.engine;

import net.jinri.auto.policy.pojos.MqMsg;

public class MqReceiver {
	PolicyEngine policyEngine = new PolicyEngine();
	public void Receive(MqMsg mqMsg)
	{		
		if(mqMsg.getPolicyType().equals("BSC"))
		{
			if(mqMsg.getPolicyOperation().equals("ADD"))
			{
				policyEngine.addPolicyBasic(mqMsg.getPolicyBasic());
			}
			else if(mqMsg.getPolicyOperation().equals("DEL"))
			{
				policyEngine.deletePolicyBasic(mqMsg.getPolicyBasic().getPid());
			}
		}
		else if(mqMsg.getPolicyType().equals("USR"))
		{
			if(mqMsg.getPolicyOperation().equals("ADD"))
			{
				policyEngine.addPolicy(mqMsg.getPolicy());
			}
			else if(mqMsg.getPolicyOperation().equals("DEL"))
			{
				policyEngine.deletePolicy(mqMsg.getPolicy().getPid());
			}		
		}
	}
}
