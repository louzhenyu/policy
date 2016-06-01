package net.jinri.auto.policy.pojos;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class PolicyBasic implements Serializable{
	 @Field
	 private String pid;
	 @Field
	 private String start_airport;
	 @Field
	 private String arrive_airport;
	 @Field
	 private String aircom;
	 @Field
	 private String pname;
	 @Field
	 private Double agency_fee;
	 @Field
	 private Double agency_fee_bab;
	 @Field
	 private Double agency_fee_chd;	
	 @Field
	 private Double billing_fee;
	 @Field
	 private Double billing_fee_bab;
	 @Field
	 private Double billing_fee_chd;
	 @Field
	 private Integer early_days;
	 @Field
	 private String cabin;
	 @Field
	 private Double incentive_fee;
	 @Field
	 private Double incentive_fee_bab;
	 @Field
	 private Double incentive_fee_chd;
	 @Field
	 private String inapplicable_passenger;
	 @Field
	 private Date sale_begin;
	 @Field
	 private Date sale_end;
	 @Field
	 private Integer sat_work_time_begin;
	 @Field
	 private Integer sat_work_time_end;
	 @Field
	 private String state;
	 @Field
	 private Integer stay_max;
	 @Field
	 private Integer stay_min;
	 @Field
	 private Integer std_work_time_begin;
	 @Field
	 private Integer std_work_time_end;
	 @Field
	 private Integer sun_work_time_begin;
	 @Field
	 private Integer sun_work_time_end;
	 @Field
	 private Date trave_begin;
	 @Field
	 private Date trave_end;
	 @Field
	 private String create_person;
	 @Field
	 private String update_person;
	 @Field
	 private Date create_time;
	 @Field
	 private Date update_time;
	 @Field
	 private Double deducting_fee;
	 @Field
	 private Double deducting_fee_bab;
	 @Field
	 private Double deducting_fee_chd;
	 @Field
	 private String upids;
	 @Field
	 private String ptype;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getStart_airport() {
		return start_airport;
	}
	public void setStart_airport(String start_airport) {
		this.start_airport = start_airport;
	}
	public String getArrive_airport() {
		return arrive_airport;
	}
	public void setArrive_airport(String arrive_airport) {
		this.arrive_airport = arrive_airport;
	}
	public String getAircom() {
		return aircom;
	}
	public void setAircom(String aircom) {
		this.aircom = aircom;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Double getAgency_fee() {
		return agency_fee;
	}
	public void setAgency_fee(Double agency_fee) {
		this.agency_fee = agency_fee;
	}
	public Double getAgency_fee_bab() {
		return agency_fee_bab;
	}
	public void setAgency_fee_bab(Double agency_fee_bab) {
		this.agency_fee_bab = agency_fee_bab;
	}
	public Double getAgency_fee_chd() {
		return agency_fee_chd;
	}
	public void setAgency_fee_chd(Double agency_fee_chd) {
		this.agency_fee_chd = agency_fee_chd;
	}
	public Double getBilling_fee() {
		return billing_fee;
	}
	public void setBilling_fee(Double billing_fee) {
		this.billing_fee = billing_fee;
	}
	public Double getBilling_fee_bab() {
		return billing_fee_bab;
	}
	public void setBilling_fee_bab(Double billing_fee_bab) {
		this.billing_fee_bab = billing_fee_bab;
	}
	public Double getBilling_fee_chd() {
		return billing_fee_chd;
	}
	public void setBilling_fee_chd(Double billing_fee_chd) {
		this.billing_fee_chd = billing_fee_chd;
	}
	public Integer getEarly_days() {
		return early_days;
	}
	public void setEarly_days(Integer early_days) {
		this.early_days = early_days;
	}
	public String getCabin() {
		return cabin;
	}
	public void setCabin(String cabin) {
		this.cabin = cabin;
	}
	public Double getIncentive_fee() {
		return incentive_fee;
	}
	public void setIncentive_fee(Double incentive_fee) {
		this.incentive_fee = incentive_fee;
	}
	public Double getIncentive_fee_bab() {
		return incentive_fee_bab;
	}
	public void setIncentive_fee_bab(Double incentive_fee_bab) {
		this.incentive_fee_bab = incentive_fee_bab;
	}
	public Double getIncentive_fee_chd() {
		return incentive_fee_chd;
	}
	public void setIncentive_fee_chd(Double incentive_fee_chd) {
		this.incentive_fee_chd = incentive_fee_chd;
	}
	public String getInapplicable_passenger() {
		return inapplicable_passenger;
	}
	public void setInapplicable_passenger(String inapplicable_passenger) {
		this.inapplicable_passenger = inapplicable_passenger;
	}
	public Date getSale_begin() {
		return sale_begin;
	}
	public void setSale_begin(Date sale_begin) {
		this.sale_begin = sale_begin;
	}
	public Date getSale_end() {
		return sale_end;
	}
	public void setSale_end(Date sale_end) {
		this.sale_end = sale_end;
	}
	public Integer getSat_work_time_begin() {
		return sat_work_time_begin;
	}
	public void setSat_work_time_begin(Integer sat_work_time_begin) {
		this.sat_work_time_begin = sat_work_time_begin;
	}
	public Integer getSat_work_time_end() {
		return sat_work_time_end;
	}
	public void setSat_work_time_end(Integer sat_work_time_end) {
		this.sat_work_time_end = sat_work_time_end;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getStay_max() {
		return stay_max;
	}
	public void setStay_max(Integer stay_max) {
		this.stay_max = stay_max;
	}
	public Integer getStay_min() {
		return stay_min;
	}
	public void setStay_min(Integer stay_min) {
		this.stay_min = stay_min;
	}
	public Integer getStd_work_time_begin() {
		return std_work_time_begin;
	}
	public void setStd_work_time_begin(Integer std_work_time_begin) {
		this.std_work_time_begin = std_work_time_begin;
	}
	public Integer getStd_work_time_end() {
		return std_work_time_end;
	}
	public void setStd_work_time_end(Integer std_work_time_end) {
		this.std_work_time_end = std_work_time_end;
	}
	public Integer getSun_work_time_begin() {
		return sun_work_time_begin;
	}
	public void setSun_work_time_begin(Integer sun_work_time_begin) {
		this.sun_work_time_begin = sun_work_time_begin;
	}
	public Integer getSun_work_time_end() {
		return sun_work_time_end;
	}
	public void setSun_work_time_end(Integer sun_work_time_end) {
		this.sun_work_time_end = sun_work_time_end;
	}
	public Date getTrave_begin() {
		return trave_begin;
	}
	public void setTrave_begin(Date trave_begin) {
		this.trave_begin = trave_begin;
	}
	public Date getTrave_end() {
		return trave_end;
	}
	public void setTrave_end(Date trave_end) {
		this.trave_end = trave_end;
	}
	public String getCreate_person() {
		return create_person;
	}
	public void setCreate_person(String create_person) {
		this.create_person = create_person;
	}
	public String getUpdate_person() {
		return update_person;
	}
	public void setUpdate_person(String update_person) {
		this.update_person = update_person;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Double getDeducting_fee() {
		return deducting_fee;
	}
	public void setDeducting_fee(Double deducting_fee) {
		this.deducting_fee = deducting_fee;
	}
	public Double getDeducting_fee_bab() {
		return deducting_fee_bab;
	}
	public void setDeducting_fee_bab(Double deducting_fee_bab) {
		this.deducting_fee_bab = deducting_fee_bab;
	}
	public Double getDeducting_fee_chd() {
		return deducting_fee_chd;
	}
	public void setDeducting_fee_chd(Double deducting_fee_chd) {
		this.deducting_fee_chd = deducting_fee_chd;
	}
	public String getUpids() {
		return upids;
	}
	public void setUpids(String upids) {
		this.upids = upids;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}	 
}
