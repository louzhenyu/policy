package net.jinri.auto.policy.pojos;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class PolicyIn implements Serializable{
	 @Field
	 private String pid;
	 @Field
	 private String ptype;
	 @Field
	 private String pname;
	 @Field
	 private Integer bp_count;
	 @Field
	 private String state;
	 @Field
	 private Double agency_fee;
	 @Field
	 private Double agency_fee_chd;
	 @Field
	 private Double agency_fee_bab;
	 @Field
	 private Double billing_fee;
	 @Field
	 private Double billing_fee_chd;
	 @Field
	 private Double billing_fee_bab;
	 @Field
	 private Double incentive_fee;
	 @Field
	 private Double incentive_fee_bab;
	 @Field
	 private Double incentive_fee_chd;
	 @Field
	 private Double deducting_fee;
	 @Field
	 private Double deducting_fee_bab;
	 @Field
	 private Double deducting_fee_chd;
	 @Field
	 private String create_person;
	 @Field
	 private Date create_time;
	 @Field
	 private String update_person;
	 @Field
	 private Date update_time;
	 @Field
	 private String bpids;
	 @Field
	 private String can_ojs;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getBp_count() {
		return bp_count;
	}
	public void setBp_count(Integer bp_count) {
		this.bp_count = bp_count;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Double getAgency_fee() {
		return agency_fee;
	}
	public void setAgency_fee(Double agency_fee) {
		this.agency_fee = agency_fee;
	}
	public Double getAgency_fee_chd() {
		return agency_fee_chd;
	}
	public void setAgency_fee_chd(Double agency_fee_chd) {
		this.agency_fee_chd = agency_fee_chd;
	}
	public Double getAgency_fee_bab() {
		return agency_fee_bab;
	}
	public void setAgency_fee_bab(Double agency_fee_bab) {
		this.agency_fee_bab = agency_fee_bab;
	}
	public Double getBilling_fee() {
		return billing_fee;
	}
	public void setBilling_fee(Double billing_fee) {
		this.billing_fee = billing_fee;
	}
	public Double getBilling_fee_chd() {
		return billing_fee_chd;
	}
	public void setBilling_fee_chd(Double billing_fee_chd) {
		this.billing_fee_chd = billing_fee_chd;
	}
	public Double getBilling_fee_bab() {
		return billing_fee_bab;
	}
	public void setBilling_fee_bab(Double billing_fee_bab) {
		this.billing_fee_bab = billing_fee_bab;
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
	public String getCreate_person() {
		return create_person;
	}
	public void setCreate_person(String create_person) {
		this.create_person = create_person;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_person() {
		return update_person;
	}
	public void setUpdate_person(String update_person) {
		this.update_person = update_person;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getBpids() {
		return bpids;
	}
	public void setBpids(String bpids) {
		this.bpids = bpids;
	}
	public String getCan_ojs() {
		return can_ojs;
	}
	public void setCan_ojs(String can_ojs) {
		this.can_ojs = can_ojs;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	
}
