package net.jinri.auto.policy.sample;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.jinri.auto.policy.pojos.Leg;
import net.jinri.auto.policy.pojos.PolicyResult;
import net.jinri.auto.policy.pojos.Order;
import net.jinri.auto.policy.pojos.Passenger;
import net.jinri.auto.policy.pojos.PolicyIn;
import net.jinri.auto.policy.pojos.PolicyBasic;
import net.jinri.auto.policy.engine.PolicyEngine;
 
 
public class SolrjExample 
{	
    public static void main( String[] args )
    {
//    	executeAddPolicybasic0();
//    	executeAddPolicybasic1();
//    	executeAddPolicybasic2();
    	//executeDeletePolicyBasic();
//    	executeAddPolicy();
    	executeDeletePolicy();
//    	executeMatch();
    }  
    public static void executeAddPolicybasic0()
    {
        PolicyBasic policyBasic = new PolicyBasic();
        policyBasic.setAgency_fee(128.2);
        policyBasic.setAgency_fee_bab(100.3);
        policyBasic.setAgency_fee_chd(90.3);
        policyBasic.setAircom("MU/CA/CZ/HU/3U/MF");
        policyBasic.setArrive_airport("PVG/SHA");
        policyBasic.setBilling_fee(30.5);
        policyBasic.setBilling_fee_bab(20.3);
        policyBasic.setBilling_fee_chd(10.9);
        policyBasic.setPid(UUID.randomUUID().toString());
        policyBasic.setPname("policy_test_0");
        policyBasic.setCabin("A/B/C/D/E/F/G/H/I/J/K/L/M/N/O/P/Q/R/S/T/U/V/W/X/Y/Z");
        policyBasic.setCreate_person("zhenyu_lou");
        policyBasic.setCreate_time(new Date());
        policyBasic.setDeducting_fee(999.5);
        policyBasic.setDeducting_fee_bab(900.3);
        policyBasic.setDeducting_fee_chd(800.3);
        policyBasic.setEarly_days(10);
        policyBasic.setInapplicable_passenger("STUDENT/SAILOR/OLD/BABY");
        policyBasic.setIncentive_fee(9.9);
        policyBasic.setIncentive_fee_bab(8.8);
        policyBasic.setIncentive_fee_chd(7.7);
        policyBasic.setSale_begin(new Date());
        policyBasic.setSale_end(new Date());
        policyBasic.setSat_work_time_begin(1234567);
        policyBasic.setSat_work_time_end(2345678);
        policyBasic.setStart_airport("PEK/NAY");
        policyBasic.setState("ACTIVE");
        policyBasic.setStay_max(389945);
        policyBasic.setStay_min(9984);
        policyBasic.setStd_work_time_begin(8888);
        policyBasic.setStd_work_time_end(9999);
        policyBasic.setSun_work_time_begin(2222);
        policyBasic.setSun_work_time_end(3333);
        policyBasic.setTrave_begin(new Date());
        policyBasic.setTrave_end(new Date());
        policyBasic.setUpdate_person("zhenyu_lou");
        policyBasic.setUpdate_time(new Date());
        policyBasic.setPtype("BSC");
    	PolicyEngine policyEngine = new PolicyEngine();
        policyEngine.addPolicyBasic(policyBasic);
    }
    public static void executeAddPolicybasic1()
    {
        PolicyBasic policyBasic = new PolicyBasic();
        policyBasic.setAgency_fee(128.2);
        policyBasic.setAgency_fee_bab(100.3);
        policyBasic.setAgency_fee_chd(90.3);
        policyBasic.setAircom("MU/CA/CZ/HU/3U/MF");
        policyBasic.setArrive_airport("MAN/LPL");
        policyBasic.setBilling_fee(30.5);
        policyBasic.setBilling_fee_bab(20.3);
        policyBasic.setBilling_fee_chd(10.9);
        policyBasic.setPid(UUID.randomUUID().toString());
        policyBasic.setPname("policy_test_1");
        policyBasic.setCabin("A/B/C/D/E/F/G/H/I/J/K/L/M/N/O/P/Q/R/S/T/U/V/W/X/Y/Z");
        policyBasic.setCreate_person("zhenyu_lou");
        policyBasic.setCreate_time(new Date());
        policyBasic.setDeducting_fee(999.5);
        policyBasic.setDeducting_fee_bab(900.3);
        policyBasic.setDeducting_fee_chd(800.3);
        policyBasic.setEarly_days(10);
        policyBasic.setInapplicable_passenger("STUDENT/SAILOR/OLD/BABY");
        policyBasic.setIncentive_fee(9.9);
        policyBasic.setIncentive_fee_bab(8.8);
        policyBasic.setIncentive_fee_chd(7.7);
        policyBasic.setSale_begin(new Date());
        policyBasic.setSale_end(new Date());
        policyBasic.setSat_work_time_begin(1234567);
        policyBasic.setSat_work_time_end(2345678);
        policyBasic.setStart_airport("PVG/SHA");
        policyBasic.setState("ACTIVE");
        policyBasic.setStay_max(389945);
        policyBasic.setStay_min(9984);
        policyBasic.setStd_work_time_begin(8888);
        policyBasic.setStd_work_time_end(9999);
        policyBasic.setSun_work_time_begin(2222);
        policyBasic.setSun_work_time_end(3333);
        policyBasic.setTrave_begin(new Date());
        policyBasic.setTrave_end(new Date());
        policyBasic.setUpdate_person("zhenyu_lou");
        policyBasic.setUpdate_time(new Date());
        policyBasic.setPtype("BSC");
    	PolicyEngine policyEngine = new PolicyEngine();
        policyEngine.addPolicyBasic(policyBasic);
    }
    public static void executeAddPolicybasic2()
    {
        PolicyBasic policyBasic = new PolicyBasic();
        policyBasic.setAgency_fee(128.2);
        policyBasic.setAgency_fee_bab(100.3);
        policyBasic.setAgency_fee_chd(90.3);
        policyBasic.setAircom("MU/CA/CZ/HU/3U/MF");
        policyBasic.setArrive_airport("LAX/JFK");
        policyBasic.setBilling_fee(30.5);
        policyBasic.setBilling_fee_bab(20.3);
        policyBasic.setBilling_fee_chd(10.9);
        policyBasic.setPid(UUID.randomUUID().toString());
        policyBasic.setPname("policy_test_2");
        policyBasic.setCabin("A/B/C/D/E/F/G/H/I/J/K/L/M/N/O/P/Q/R/S/T/U/V/W/X/Y/Z");
        policyBasic.setCreate_person("zhenyu_lou");
        policyBasic.setCreate_time(new Date());
        policyBasic.setDeducting_fee(999.5);
        policyBasic.setDeducting_fee_bab(900.3);
        policyBasic.setDeducting_fee_chd(800.3);
        policyBasic.setEarly_days(10);
        policyBasic.setInapplicable_passenger("STUDENT/SAILOR/OLD/BABY");
        policyBasic.setIncentive_fee(9.9);
        policyBasic.setIncentive_fee_bab(8.8);
        policyBasic.setIncentive_fee_chd(7.7);
        policyBasic.setSale_begin(new Date());
        policyBasic.setSale_end(new Date());
        policyBasic.setSat_work_time_begin(1234567);
        policyBasic.setSat_work_time_end(2345678);
        policyBasic.setStart_airport("LPL/MAN");
        policyBasic.setState("ACTIVE");
        policyBasic.setStay_max(389945);
        policyBasic.setStay_min(9984);
        policyBasic.setStd_work_time_begin(8888);
        policyBasic.setStd_work_time_end(9999);
        policyBasic.setSun_work_time_begin(2222);
        policyBasic.setSun_work_time_end(3333);
        policyBasic.setTrave_begin(new Date());
        policyBasic.setTrave_end(new Date());
        policyBasic.setUpdate_person("zhenyu_lou");
        policyBasic.setUpdate_time(new Date());
        policyBasic.setPtype("BSC");
    	PolicyEngine policyEngine = new PolicyEngine();
        policyEngine.addPolicyBasic(policyBasic);
    }
    public static void executeAddPolicy()
    {
    	PolicyIn policy = new PolicyIn();
    	policy.setAgency_fee(10.1);
    	policy.setAgency_fee_bab(10.2);
    	policy.setAgency_fee_chd(10.3);
    	policy.setBilling_fee(11.1);
    	policy.setBilling_fee_bab(11.2);
    	policy.setBilling_fee_chd(11.3);
    	policy.setBp_count(3);
    	policy.setBpids("f48e691d-b2b4-4c3b-b56f-17fef81e68bc/c3c83010-152f-4db6-a547-899c86909d6a/6fe94e40-9b04-4d8f-bf6c-dce1549285bb");
    	policy.setCreate_person("zhenyu_lou");
    	policy.setCreate_time(new Date());
    	policy.setDeducting_fee(12.1);
    	policy.setDeducting_fee_bab(12.2);
    	policy.setDeducting_fee_chd(12.3);
    	policy.setIncentive_fee(13.1);
    	policy.setIncentive_fee_bab(13.2);
    	policy.setIncentive_fee_chd(13.3);
    	policy.setPname("first user defined policy");
    	policy.setState("ACTIVE");
    	policy.setCan_ojs("0/0/0");
    	policy.setUpdate_person("zhenyu_lou");
    	policy.setUpdate_time(new Date());
    	policy.setPtype("USR");
    	PolicyEngine policyEngine = new PolicyEngine();
        policyEngine.addPolicy(policy);
    }
    public static void executeDeletePolicyBasic()
    {
    	PolicyEngine policyEngine = new PolicyEngine();
    	policyEngine.deletePolicyBasic("");
    }
    public static void executeDeletePolicy()
    {
    	PolicyEngine policyEngine = new PolicyEngine();
    	policyEngine.deletePolicy("10d827a2-3455-4a4f-a554-373d0516d70c");    	
    }
    public static void executeMatch()
    {
    	PolicyEngine policyEngine = new PolicyEngine();
    	Order order = new Order();
    	order.setPage_index(0);
    	order.setPage_count(20);
    	List<Leg> legs = new ArrayList<Leg>();
    	Leg leg0 = createLeg("MU", "PVG", "2016-04-22T17:32:00Z", "C", "MU571", "PEK", "2016-04-22T09:00:00Z");
    	Leg leg1 = createLeg("CA", "MAN", "2016-04-23T17:32:00Z", "A", "CA187", "PVG", "2016-04-23T09:00:00Z");
    	Leg leg2 = createLeg("CZ", "LAX", "2016-04-24T17:32:00Z", "W", "CZ208", "MAN", "2016-04-24T09:00:00Z");
    	legs.add(leg0);
    	legs.add(leg1);
    	legs.add(leg2);
    	order.setLegs(legs);
    	List<Passenger> passengers = new ArrayList<Passenger>();
    	Passenger passenger0 = new Passenger();
    	passenger0.setGeneralTypes(Arrays.asList("STUDENT", "SAILOR"));
    	Passenger passenger1 = new Passenger();
    	passenger1.setGeneralTypes(Arrays.asList("OLD", "SAILOR"));
    	passengers.add(passenger0);
    	passengers.add(passenger1);
    	order.setPassengers(passengers);
    	PolicyResult matchResult = policyEngine.match(order);
    	System.out.println("Total matched count:" + matchResult.getCount());
    }
    public static Leg createLeg(String airCom, String arriveAirport, String arriveDateStr, String cabin, String fltNo, String startAirport, String startDateStr)
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); 
    	Date arriveDate = new Date();
    	Date startDate = new Date();
		try {
			startDate = sdf.parse(startDateStr);
			arriveDate = sdf.parse(arriveDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Leg leg = new Leg();
    	leg.setAirCom(airCom);
    	leg.setArriveAirport(arriveAirport);
    	leg.setArriveDate(arriveDate);
    	leg.setCabin(cabin);
    	leg.setFltNo(fltNo);
    	leg.setStartAirport(startAirport);
    	leg.setStartDate(startDate);
    	return leg;
    }
}

