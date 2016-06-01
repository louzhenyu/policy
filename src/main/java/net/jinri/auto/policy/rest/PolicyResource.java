package net.jinri.auto.policy.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import net.jinri.auto.policy.config.RabbitTemplateSingleton;
import net.jinri.auto.policy.engine.PolicyEngine;
import net.jinri.auto.policy.pojos.MqMsg;
import net.jinri.auto.policy.pojos.Order;
import net.jinri.auto.policy.pojos.PolicyIn;
import net.jinri.auto.policy.pojos.PolicyResult;
import net.jinri.auto.policy.pojos.PolicyBasic;
import net.jinri.auto.policy.pojos.PolicyBasicResult;

@Path("")
public class PolicyResource {
	private static final Logger logger = LoggerFactory.getLogger(PolicyResource.class);
	
	@Path("PolicyBasic")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addPolicyBasic(PolicyBasic policyBasic)
	{
		logger.info("[Interface]:Start adding basic policy-pname:{}, create_person:{}", policyBasic.getPname(), policyBasic.getCreate_person());
		try
		{
	    	RabbitTemplate template = RabbitTemplateSingleton.getInstance().getTemplate();
	    	MqMsg mqMsg = new MqMsg();
	    	mqMsg.setPolicyType("BSC");
	    	mqMsg.setPolicyOperation("ADD");
	    	mqMsg.setPolicyBasic(policyBasic);
	    	template.convertAndSend(mqMsg);
		}
		catch(Exception ex)
		{
			logger.error("[Interface]:Fail to add basic policy-pname:{}, create_person:{}", policyBasic.getPname(), policyBasic.getCreate_person());
			return;
		}
		logger.info("[Interface]:End adding basic policy-pname:{}, create_person:{}", policyBasic.getPname(), policyBasic.getCreate_person());
	}
	
	@Path("PolicyBasic/{pid}")
	@DELETE
	public void deletePolicyBasic(@PathParam("pid") String pid)
	{
		logger.info("[Interface]:Start deleting basic policy-pid:{}", pid);
		try
		{
	    	RabbitTemplate template = RabbitTemplateSingleton.getInstance().getTemplate();
	    	MqMsg mqMsg = new MqMsg();
	    	mqMsg.setPolicyType("BSC");
	    	mqMsg.setPolicyOperation("DEL");
	    	PolicyBasic policyBasic = new PolicyBasic();
	    	policyBasic.setPid(pid);
	    	mqMsg.setPolicyBasic(policyBasic);
	    	template.convertAndSend(mqMsg);
		}
		catch(Exception ex)
		{
			logger.error("[Interface]:Fail to delete basic policy-pid:{}", pid);
			return;
		}
    	logger.info("[Interface]:End deleting basic policy-pid:{}", pid);
	}
	
	@Path("PolicyBasic/{pid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PolicyBasic> readPolicyBasicById(@PathParam("pid") String pid)
	{
		logger.info("[Interface]:Start reading basic policy by pid-pid:{}", pid);
		List<PolicyBasic> result = new ArrayList<PolicyBasic>();
		try
		{
			PolicyEngine policyEngine = new PolicyEngine();		
			result = policyEngine.readPolicyBasicById(pid);
		}
		catch(Exception ex)
		{
			logger.error("[Interface]:Fail to read basic policy by pid-pid:{}", pid);
			return result;
		}
		logger.info("[Interface]:End reading basic policy by pid-pid:{}", pid);
		return result;
	}
	
	@Path("PolicyBasic")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PolicyBasicResult readPolicyBasicAll( @DefaultValue("") @QueryParam("usrName") String usrName, @DefaultValue("0") @QueryParam("pageIndex") int pageIndex, @DefaultValue("20") @QueryParam("pageCount") int pageCount)
	{
		logger.info("[Interface]:Start reading basic policy all-create_person:{}, page_index:{}, page_count:{}", usrName, pageIndex, pageCount);
		PolicyBasicResult result = new PolicyBasicResult();
		try
		{			
			PolicyEngine policyEngine = new PolicyEngine();
			result = policyEngine.readPolicyBasicAll(usrName, pageIndex, pageCount);
		}
		catch(Exception ex)
		{
			logger.error("[Interface]:Fail to read basic policy all-create_person:{}, page_index:{}, page_count:{}", usrName, pageIndex, pageCount);
			return result;
		}
		logger.info("[Interface]:End reading basic policy all-create_person:{}, page_index:{}, page_count:{}", usrName, pageIndex, pageCount);
		return result;
	}
	
	@Path("Policy")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addPolicy(PolicyIn policy)
	{
		logger.info("[Interface]:Start adding policy-pname:{}, create_person:{}", policy.getPname(), policy.getCreate_person());
		try
		{
	    	RabbitTemplate template = RabbitTemplateSingleton.getInstance().getTemplate();
	    	MqMsg mqMsg = new MqMsg();
	    	mqMsg.setPolicyType("USR");
	    	mqMsg.setPolicyOperation("ADD");
	    	mqMsg.setPolicy(policy);
	    	template.convertAndSend(mqMsg);
		}
		catch(Exception ex)
		{
			logger.error("[Interface]:Fail to add policy-pname:{}, create_person:{}", policy.getPname(), policy.getCreate_person());
			return;
		}
		logger.info("[Interface]:End adding policy-pname:{}, create_person:{}", policy.getPname(), policy.getCreate_person());    	
	}
	@Path("Policy/{pid}")
	@DELETE
	public void deletePolicy(@PathParam("pid") String pid)
	{
		logger.info("[Interface]:Start deleting policy-pid:{}", pid);
		try
		{
	    	RabbitTemplate template = RabbitTemplateSingleton.getInstance().getTemplate();
	    	MqMsg mqMsg = new MqMsg();
	    	mqMsg.setPolicyType("USR");
	    	mqMsg.setPolicyOperation("DEL");
	    	PolicyIn policy = new PolicyIn();
	    	policy.setPid(pid);
	    	mqMsg.setPolicy(policy);
	    	template.convertAndSend(mqMsg);
		}
		catch(Exception ex)
		{
			logger.info("[Interface]:Fail to delete policy-pid:{}", pid);
		}
    	logger.info("[Interface]:End deleting policy-pid:{}", pid);
	}
	
	@Path("Policy/{pid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PolicyResult readPolicyById(@PathParam("pid") String pid)
	{
		logger.info("[Interface]:Start reading policy by pid-pid:{}", pid);
		PolicyResult result = new PolicyResult();
		try
		{
			PolicyEngine policyEngine = new PolicyEngine();
			result = policyEngine.readPolicyById(pid);
		}
		catch(Exception ex)
		{
			logger.error("[Interface]:Fail to read policy by pid-pid:{}", pid);	
			return result;
		}
		logger.info("[Interface]:End reading policy by pid-pid:{}", pid);		
		return result;
	}
	
	@Path("Policy")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PolicyResult readPolicyAll(@DefaultValue("") @QueryParam("usrName") String usrName, @DefaultValue("0") @QueryParam("pageIndex") int pageIndex, @DefaultValue("20") @QueryParam("pageCount") int pageCount)
	{
		logger.info("[Interface]:Start reading policy all-create_person:{}, page_index:{}, page_count:{}", usrName, pageIndex, pageCount);
		PolicyResult result = new PolicyResult();
		try
		{
			PolicyEngine policyEngine = new PolicyEngine();
			result = policyEngine.readPolicyAll(usrName, pageIndex, pageCount);
		}
		catch(Exception ex)
		{
			logger.error("[Interface]:Fail to read policy all-create_person:{}, page_index:{}, page_count:{}", usrName, pageIndex, pageCount);
			return result;
		}
		logger.info("[Interface]:End reading policy all-create_person:{}, page_index:{}, page_count:{}", usrName, pageIndex, pageCount);
		return result;
	}
	
	@Path("Match")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PolicyResult match(Order order)
	{	
		logger.info("[Interface]:Start matching-orderId:{}", order.getId());		
		PolicyResult result = new PolicyResult();
		try
		{
			PolicyEngine policyEngine = new PolicyEngine();
			result = policyEngine.match(order);
		}
		catch(Exception ex)
		{
			logger.error("[Interface]:Fail to match-orderId:{}", order.getId());
			return result;
		}
		logger.info("[Interface]:End matching-orderId:{}", order.getId());
		return result;
	}
}
