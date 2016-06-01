package net.jinri.auto.policy.core;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import net.jinri.auto.policy.pojos.Order;
import net.jinri.auto.policy.pojos.PolicyBasic;
import net.jinri.auto.policy.pojos.PolicyBasicResult;
 
public class SolrDao <T>
{
 
	HttpSolrClient server = null;
    
    public SolrDao (String solrURL)
    {
        server = (HttpSolrClient)SolrServerFactory.getInstance().createServer(solrURL);
        configureSolr (server);
    }
    
    public void delete(String id)
    {
    	try {
			server.deleteById(id);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void delete(List<String> ids)
    {
    	try {
			server.deleteById(ids);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void commit()
    {
    	try {
			server.commit();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void rollback()
    {
    	try {
			server.rollback();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }    
    
    public void putDoc (SolrInputDocument doc)
    {
        putDoc (createSingletonSet(doc));
    }
    
    public void putDoc (Collection<SolrInputDocument> docs)
    {
        try 
        {
            long startTime = System.currentTimeMillis();
            UpdateRequest req = new UpdateRequest();
            req.setAction( UpdateRequest.ACTION.COMMIT, false, false );
            req.add (docs);
            UpdateResponse rsp = req.process( server );
            System.out.print ("Added documents to solr. Time taken = " + rsp.getElapsedTime() + ". " + rsp.toString());
            long endTime = System.currentTimeMillis();
            System.out.println (" , time-taken=" + ((double)(endTime-startTime))/1000.00 + " seconds");
        }
        catch (SolrServerException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public List<PolicyBasic> readPolicyBasicById(String pid)
    {
    	SolrQuery query = new SolrQuery();    	
    	query.setQuery("pid:" + pid);
    	QueryResponse rsp = null;
        try 
        {
            rsp = server.query( query );
        }
        catch (SolrServerException e) 
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        List<PolicyBasic> beans = rsp.getBeans(PolicyBasic.class);
        return beans;
    }
    
    public PolicyBasicResult readPolicyBasicAll(String usrName, int pageIndex, int pageCount)
    {
    	PolicyBasicResult result = new PolicyBasicResult();
    	SolrQuery query = new SolrQuery();
    	if(usrName == null || usrName.isEmpty())
    	{
        	query.setQuery("ptype:BSC");   		
    	}
    	else
    	{
    		query.setQuery("ptype:BSC && create_person:" + usrName);
    	}
    	query.setStart(pageIndex);
    	query.setRows(pageCount);
    	QueryResponse rsp = null;
        try 
        {
            rsp = server.query( query );
        }
        catch (SolrServerException e) 
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        result.setCount(rsp.getResults().getNumFound());
        List<PolicyBasic> beans = rsp.getBeans(PolicyBasic.class);
        result.setPolicyBasics(beans);
        return result;
    }
    
    public QueryResponse readPolicyAll(String usrName, int pageIndex, int pageCount)
    {
    	QueryResponse rs = null;
    	SolrQuery query = new SolrQuery();
    	if(usrName != null && !usrName.isEmpty())
    	query.setFilterQueries("create_person:" + usrName);
    	query.setQuery("ptype:USR");
    	query.setStart(pageIndex);
    	query.setRows(pageCount);
    	try {
			rs = server.query(query);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return rs;    	
    }
    
    public QueryResponse readPolicyById(String pid)
    {
    	SolrQuery query = new SolrQuery();
    	query.setQuery("pid:" + pid);
        QueryResponse rsp = null;
        try {
            rsp = server.query( query );
        } catch (SolrServerException e) 
        {
            e.printStackTrace();
            return null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }        
        return rsp;
    }
    
    public SolrDocumentList readAllDocs ()
    {
        SolrQuery query = new SolrQuery();
        query.setQuery( "*:*" );
        //query.addSortField( "price", SolrQuery.ORDER.asc );
        QueryResponse rsp = null;
        try {
            rsp = server.query( query );
        } catch (SolrServerException e) 
        {
            e.printStackTrace();
            return null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        SolrDocumentList docs = rsp.getResults();
        return docs;
    }
    
    public QueryResponse readUsrPolicyByConditions(Order order)
    {
    	QueryResponse rs = null;
    	SolrQuery query = new SolrQuery();
    	query.setFilterQueries("bp_count:" + order.getLegs().size());
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < order.getLegs().size(); ++i)
    	{
    		if(sb.length() != 0)
    		{
    			sb.append(" && ");
    		}
    		sb.append("start_airport_" + i + ":" + order.getLegs().get(i).getStartAirport());
    		sb.append(" && ");
    		sb.append("arrive_airport_" + i + ":" + order.getLegs().get(i).getArriveAirport());
    	}
    	query.setQuery(sb.toString());
    	query.setStart(order.getPage_index());
    	query.setRows(order.getPage_count());
    	try {
			rs = server.query(query);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return rs;
    }
    
    private void configureSolr(HttpSolrClient server) 
    {
        server.setConnectionTimeout(5000); // 5 seconds to establish TCP
        // The following settings are provided here for completeness.
        // They will not normally be required, and should only be used 
        // after consulting javadocs to know whether they are truly required.
        server.setSoTimeout(1000);  // socket read timeout
        server.setDefaultMaxConnectionsPerHost(100);
        server.setMaxTotalConnections(100);
        server.setFollowRedirects(false);  // defaults to false
        // allowCompression defaults to false.
        // Server side must support gzip or deflate for this to have any effect.
        server.setAllowCompression(false);       
    }
    
    private <U> Collection<U> createSingletonSet(U dao) 
    {
        if (dao == null)
            return Collections.emptySet();
        return Collections.singleton(dao);
    }
 
}
