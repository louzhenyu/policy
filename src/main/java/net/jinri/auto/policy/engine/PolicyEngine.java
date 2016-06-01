package net.jinri.auto.policy.engine;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import net.jinri.auto.policy.config.PropertiesFactoryHelper;
import net.jinri.auto.policy.core.SolrDao;
import net.jinri.auto.policy.pojos.PolicyResult;
import net.jinri.auto.policy.pojos.Order;
import net.jinri.auto.policy.pojos.PolicyIn;
import net.jinri.auto.policy.pojos.PolicyBasic;
import net.jinri.auto.policy.pojos.PolicyBasicResult;
import net.jinri.auto.policy.pojos.PolicyRet;

public class PolicyEngine {
    String solrURL = PropertiesFactoryHelper.getInstance().getConfig("solr_url");
    SolrDao<PolicyBasic> solrDao = new SolrDao<PolicyBasic> (solrURL);
    public Boolean addPolicyBasic(PolicyBasic policyBasic)
    {
    	try
    	{    		
    		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
    		if(policyBasic.getPid() == null || policyBasic.getPid().isEmpty())
    		{
    			policyBasic.setPid(UUID.randomUUID().toString());
    		}
    		else
    		{//保留原用户政策关系
	    		List<PolicyBasic> policyBasics = readPolicyBasicById(policyBasic.getPid());
	    		if(policyBasics == null || policyBasics.isEmpty())
	    		{
	    			policyBasic.setUpids(null);
	    		}
	    		else
	    		{
	    			policyBasic.setUpids(policyBasics.get(0).getUpids());
	    		}
    		}
    		SolrInputDocument docBasic = new SolrInputDocument();
	    	docBasic = createSolrInputDocument(docBasic, policyBasic, null);
	    	docs.add(docBasic);
	    	if(policyBasic.getUpids() != null && !policyBasic.getUpids().isEmpty())
	    	{//更新用户政策
	    		String[] upids = policyBasic.getUpids().split("/");
	    		for(int i = 0; i < upids.length; ++i)
	    		{
	    			SolrDocumentList policies = readPolicyByIdInner(upids[i]);
	    			if(policies == null || policies.isEmpty())
	    			{
	    				solrDao.rollback();
	    				return false;
	    			}
	    			SolrInputDocument doc = new SolrInputDocument();
	    			for(int j = 0; j < Integer.parseInt(policies.get(0).getFieldValue("bp_count").toString()); ++j)
	    			{
	    				if(policies.get(0).getFieldValue("pid_" + j).toString().equals(policyBasic.getPid()))
	    				{
	    					doc = createSolrInputDocument(doc, policyBasic, "_" + j);	    					
	    				}
	    			}
	    			docs.add(doc);	    			
	    		}
	    	}
    		solrDao.putDoc(docs);
    		solrDao.commit();
    		return true;	    	
    	}
    	catch(Exception ex)
    	{
    		solrDao.rollback();
    		return false;
    	}
    }
    public Boolean addPolicy(PolicyIn policy)
    {
    	try
    	{
    		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
    		if(policy.getPid() == null || policy.getPid().isEmpty())
    		{
    			policy.setPid(UUID.randomUUID().toString());
    		}
	    	SolrInputDocument doc = createSolrInputDocument(new SolrInputDocument(), policy, null);    
	    	String bpidOld;
	    	SolrDocumentList policies = readPolicyByIdInner(policy.getPid());
	    	if(policies == null || policies.isEmpty())
	    	{
	    		bpidOld = "";
	    	}
	    	else
	    	{
	    		bpidOld = policies.get(0).getFieldValue("bpids").toString();
	    	}
	    	String[] bpidOlds = bpidOld.split("/");
	    	for(int i = 0; i < bpidOlds.length; ++i)
	    	{
	    		if(policy.getBpids().indexOf(bpidOlds[i]) == -1)
	    		{
	    			List<PolicyBasic> policyBasics = readPolicyBasicById(bpidOlds[i]);
	    			String upidNew = "";
		    		if (policyBasics == null || policyBasics.isEmpty())
		    		{
		    			solrDao.rollback();
		    			return false;
		    		}
		    		else
		    		{	
		    			String[] upidOlds = policyBasics.get(0).getUpids().split("/");
		    			for(int j = 0; j < upidOlds.length; ++j)
		    			{
		    				if(!upidOlds[j].equals(policy.getPid()))
		    				{
		    					if(upidNew.isEmpty())
		    					{
		    						upidNew = upidNew.concat(upidOlds[j]);
		    					}
		    					else
		    					{
		    						upidNew = upidNew.concat("/").concat(upidOlds[j]);
		    					}
		    				}
		    				
		    			}		    			
		    		}
		    		policyBasics.get(0).setUpids(upidNew);
		    		SolrInputDocument oldDoc = new SolrInputDocument();
		    		oldDoc = createSolrInputDocument(oldDoc, policyBasics.get(0), null);
		    		docs.add(oldDoc);
	    		}
	    	}
	    	String[] bpids = policy.getBpids().split("/");
	    	for(int i = 0; i < bpids.length; ++i)
	    	{
	    		List<PolicyBasic> policyBasics = readPolicyBasicById(bpids[i]);
	    		if (policyBasics == null || policyBasics.isEmpty())
	    		{
	    			solrDao.rollback();
	    			return false;
	    		}
	    		else
	    		{
	    			String upids = policyBasics.get(0).getUpids();
	    			if (upids == null || upids.isEmpty())
	    			{
	    				upids = "";
	    				policyBasics.get(0).setUpids(upids.concat(policy.getPid()));
	    			}
	    			else if(upids.indexOf(policy.getPid()) == -1)
	    			{	    				
	    				policyBasics.get(0).setUpids(upids.concat("/").concat(policy.getPid()));
	    			}
	    			docs.add(createSolrInputDocument(new SolrInputDocument(), policyBasics.get(0), null));
	    			doc = createSolrInputDocument(doc, policyBasics.get(0), "_" + i);    			
	    		}
	    	}
	    	String[] ojs = policy.getCan_ojs().split("/");
	    	for(int i = 0; i < ojs.length; ++i)
	    	{
	    		doc.addField("can_oj_" + i, ojs[i]);
	    	}
	    	docs.add(doc);
	    	solrDao.putDoc(docs);
	    	solrDao.commit();
    	}
    	catch(Exception ex)
    	{
    		solrDao.rollback();
    		return false;
    	}
    	return true;
    }
    
    public Boolean deletePolicyBasic(String pid)
    {
    	try
    	{
	    	List<PolicyBasic> policyBasics = readPolicyBasicById(pid);
	    	if(policyBasics == null || policyBasics.isEmpty())
	    	{
	    		return false;
	    	}
	    	{
	    		if(policyBasics.get(0).getUpids() == null || policyBasics.get(0).getUpids().isEmpty())
	    		{
	    			solrDao.delete(pid);
	    			solrDao.commit();
	    		}
	    		else
	    		{
	    			return false;
	    		}
	    	}
	    	return true;
    	}
    	catch(Exception ex)
    	{
    		solrDao.rollback();
    		return false;
    	}
    }
   
    public Boolean deletePolicy(String pid)
    {
    	SolrDocumentList policies = readPolicyByIdInner(pid);
    	if(policies == null || policies.isEmpty())
    	{
    		return false;
    	}
    	else
    	{
    		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
    		String bpid = policies.get(0).getFieldValue("bpids").toString();
    		String[] bpids = bpid.split("/");
    		for(int i = 0; i < bpids.length; ++i)
    		{
    			List<PolicyBasic> policyBasics = readPolicyBasicById(bpids[i]);
    			if(policyBasics == null || policyBasics.isEmpty())
    			{
    				solrDao.rollback();
    				return false;
    			}
    			else
    			{
    				String upid = policyBasics.get(0).getUpids();
    				String upidNew = "";
    				String[] upids = upid.split("/");
    				for(int j = 0; j < upids.length; ++j)
    				{
    					if(!upids[j].equals(pid))
    					{
    						if(upidNew.equals(""))
    						{
    							upidNew = upidNew.concat(upids[j]);
    						}
    						else
    						{
    							upidNew = upidNew.concat("/").concat(upids[j]);
    						}
    					}
    				}
    				policyBasics.get(0).setUpids(upidNew);
    				SolrInputDocument doc = new SolrInputDocument();
    				doc = createSolrInputDocument(doc, policyBasics.get(0), null);
    				docs.add(doc);
    			}
    		}
    		solrDao.putDoc(docs);
    	}
    	solrDao.delete(pid);
    	solrDao.commit();
    	return true;
    }
    
    public PolicyResult match(Order order)
    {
    	PolicyResult matchResult = new PolicyResult();
    	QueryResponse rs = solrDao.readUsrPolicyByConditions(order);
    	if(rs == null)
    		return matchResult;
    	else
    	{
    		long totalCount = rs.getResults().getNumFound();
    		matchResult.setCount(totalCount);
    		if(rs.getResults() == null)
    			return matchResult;
    		List<PolicyRet> policyResults = ConvertToPolicyRet(rs);
    		matchResult.setPolicyRets(policyResults);
    	}
		return matchResult;    	
    }
    public List<PolicyBasic> readPolicyBasicById(String pid)
    {
    	return solrDao.readPolicyBasicById(pid);
    }
    public PolicyBasicResult readPolicyBasicAll(String usrName, int pageIndex, int pageCount)
    {
    	return solrDao.readPolicyBasicAll(usrName, pageIndex, pageCount);
    }
    public PolicyResult readPolicyAll(String usrName, int pageIndex, int pageCount)
    {
    	PolicyResult result = new PolicyResult();
    	QueryResponse rs = solrDao.readPolicyAll(usrName, pageIndex, pageCount);
    	if(rs == null)
    		return result;
    	else
    	{
    		long totalCount = rs.getResults().getNumFound();
    		result.setCount(totalCount);
    		if(rs.getResults() == null)
    			return result;
    		List<PolicyRet> policyResults = ConvertToPolicyRet(rs);
    		result.setPolicyRets(policyResults);
    	}
		return result;    	    	
    }
    public PolicyResult readPolicyById(String pid)
    {
    	PolicyResult result = new PolicyResult();
    	QueryResponse rs = solrDao.readPolicyById(pid);
    	if(rs == null)
    		return result;
    	else
    	{
    		long totalCount = rs.getResults().getNumFound();
    		result.setCount(totalCount);
    		if(rs.getResults() == null)
    			return result;
    		List<PolicyRet> policyResults = ConvertToPolicyRet(rs);
    		result.setPolicyRets(policyResults);
    	}
		return result;
    }
    
    private SolrDocumentList readPolicyByIdInner(String pid)
    {
    	return solrDao.readPolicyById(pid).getResults();
    }    
    private SolrInputDocument createSolrInputDocument(SolrInputDocument doc, Object f, String suffix)
    {
    	Field[] fields = f.getClass().getDeclaredFields(); 
    	for(int i = 0 , len = fields.length; i < len; i++) { 
    	String varName;
    	if(suffix == null || suffix.isEmpty())
    	{
    		varName = fields[i].getName();
    	}
    	else
    	{
    		if (fields[i].getName().equals("upids") || fields[i].getName().equals("ptype"))
    		{
    			continue;
    		}
    		varName = fields[i].getName() + suffix;
    	}
    	try {
    	boolean accessFlag = fields[i].isAccessible(); 
    	fields[i].setAccessible(true); 
    	Object o = fields[i].get(f); 
    	doc.setField(varName, o);
    	fields[i].setAccessible(accessFlag); 
    	} catch (IllegalArgumentException ex) { 
    	ex.printStackTrace(); 
    	} catch (IllegalAccessException ex) { 
    	ex.printStackTrace(); 
    	}}
    	return doc;
    }
    private List<PolicyRet> ConvertToPolicyRet(QueryResponse rs)
    {
    	List<PolicyRet> policyResults = new ArrayList<PolicyRet>();
		for(int i = 0; i < rs.getResults().size(); ++i)
		{
			SolrDocument sd = rs.getResults().get(i);
			PolicyRet policyResult = new PolicyRet();
			PolicyIn policy = new PolicyIn();
			policy.setPid(sd.getFieldValue("pid").toString());
			policy.setPname(sd.getFieldValue("pname").toString());
			policy.setPtype(sd.getFieldValue("ptype").toString());
			policy.setBp_count((Integer)(sd.getFieldValue("bp_count")));
			policy.setState(sd.getFieldValue("state").toString());
			policy.setAgency_fee((Double)sd.getFieldValue("agency_fee"));
			policy.setAgency_fee_chd((Double)sd.getFieldValue("agency_fee_chd"));
			policy.setAgency_fee_bab((Double)sd.getFieldValue("agency_fee_bab"));
			policy.setBilling_fee((Double)sd.getFieldValue("billing_fee"));
			policy.setBilling_fee_chd((Double)sd.getFieldValue("billing_fee_chd"));
			policy.setBilling_fee_bab((Double)sd.getFieldValue("billing_fee_bab"));
			policy.setIncentive_fee((Double)sd.getFieldValue("incentive_fee"));
			policy.setIncentive_fee_chd((Double)sd.getFieldValue("incentive_fee_chd"));
			policy.setIncentive_fee_bab((Double)sd.getFieldValue("incentive_fee_bab"));
			policy.setDeducting_fee((Double)sd.getFieldValue("deducting_fee"));
			policy.setDeducting_fee_chd((Double)sd.getFieldValue("deducting_fee_chd"));
			policy.setDeducting_fee_bab((Double)sd.getFieldValue("deducting_fee_bab"));
			policy.setCreate_person(sd.getFieldValue("create_person").toString());
			policy.setCreate_time((Date)sd.getFieldValue("create_time"));
			policy.setUpdate_person(sd.getFieldValue("update_person").toString());
			policy.setUpdate_time((Date)sd.getFieldValue("update_time"));
			policy.setBpids(sd.getFieldValue("bpids").toString());
			policy.setCan_ojs(sd.getFieldValue("can_ojs").toString());
			policyResult.setPolicy(policy);
			List<PolicyBasic> policyBasics = new ArrayList<PolicyBasic>();
			for(int j = 0; j < policy.getBp_count(); ++j)
			{
				PolicyBasic policyBasic = new PolicyBasic();
				policyBasic.setPid(sd.getFieldValue("pid_" + j).toString());
				policyBasic.setStart_airport(sd.getFieldValue("start_airport_" + j).toString());
				policyBasic.setArrive_airport(sd.getFieldValue("arrive_airport_" + j).toString());
				policyBasic.setAircom(sd.getFieldValue("aircom_" + j).toString());
				policyBasic.setPname(sd.getFieldValue("pname_" + j).toString());
				policyBasic.setAgency_fee((Double)sd.getFieldValue("agency_fee_" + j));
				policyBasic.setAgency_fee_bab((Double)sd.getFieldValue("agency_fee_bab_" + j));
				policyBasic.setAgency_fee_chd((Double)sd.getFieldValue("agency_fee_chd_" + j));
				policyBasic.setBilling_fee((Double)sd.getFieldValue("billing_fee_" + j));
				policyBasic.setBilling_fee_bab((Double)sd.getFieldValue("billing_fee_bab_" + j));
				policyBasic.setBilling_fee_chd((Double)sd.getFieldValue("billing_fee_chd_" + j));
				policyBasic.setEarly_days((Integer)sd.getFieldValue("early_days_" + j));
				policyBasic.setCabin(sd.getFieldValue("cabin_" + j).toString());
				policyBasic.setIncentive_fee((Double)sd.getFieldValue("incentive_fee_" + j));
				policyBasic.setIncentive_fee_bab((Double)sd.getFieldValue("incentive_fee_bab_" + j));
				policyBasic.setIncentive_fee_chd((Double)sd.getFieldValue("incentive_fee_chd_" + j));
				policyBasic.setInapplicable_passenger(sd.getFieldValue("inapplicable_passenger_" + j).toString());
				policyBasic.setSale_begin((Date)sd.getFieldValue("sale_begin_" + j));
				policyBasic.setSale_end((Date)sd.getFieldValue("sale_end_" + j));
				policyBasic.setSat_work_time_begin((Integer)sd.getFieldValue("sat_work_time_begin_" + j));
				policyBasic.setSat_work_time_end((Integer)sd.getFieldValue("sat_work_time_end_" + j));
				policyBasic.setState(sd.getFieldValue("state_" + j).toString());
				policyBasic.setStay_max((Integer)sd.getFieldValue("stay_max_" + j));
				policyBasic.setStay_min((Integer)sd.getFieldValue("stay_min_" + j));
				policyBasic.setStd_work_time_begin((Integer)sd.getFieldValue("std_work_time_begin_" + j));
				policyBasic.setStd_work_time_end((Integer)sd.getFieldValue("std_work_time_end_" + j));
				policyBasic.setSun_work_time_begin((Integer)sd.getFieldValue("sun_work_time_begin_" + j));
				policyBasic.setSun_work_time_end((Integer)sd.getFieldValue("sun_work_time_end_" + j));
				policyBasic.setTrave_begin((Date)sd.getFieldValue("trave_begin_" + j));
				policyBasic.setTrave_end((Date)sd.getFieldValue("trave_end_" + j));
				policyBasic.setCreate_person(sd.getFieldValue("create_person_" + j).toString());
				policyBasic.setUpdate_person(sd.getFieldValue("update_person_" + j).toString());
				policyBasic.setCreate_time((Date)sd.getFieldValue("create_time_" + j));
				policyBasic.setUpdate_time((Date)sd.getFieldValue("update_time_" + j));
				policyBasic.setDeducting_fee((Double)sd.getFieldValue("deducting_fee_" + j));
				policyBasic.setDeducting_fee_bab((Double)sd.getFieldValue("deducting_fee_bab_" + j));
				policyBasic.setDeducting_fee_chd((Double)sd.getFieldValue("deducting_fee_chd_" + j));    				
				policyBasics.add(policyBasic);
			}
			policyResult.setPolicyBasics(policyBasics);
			policyResults.add(policyResult);			
		}
		return policyResults;
    }
}
