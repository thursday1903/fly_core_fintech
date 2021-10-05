package springboot.extra.entities;

public class ViewSponsorStatistic {

	Integer sponsor_id;
	String sponsor_name;
	Integer total_disbursement;
    Integer total_settlement;
    
	public Integer getSponsor_id() {
		return sponsor_id;
	}
	public void setSponsor_id(Integer sponsor_id) {
		this.sponsor_id = sponsor_id;
	}
	public String getSponsor_name() {
		return sponsor_name;
	}
	public void setSponsor_name(String sponsor_name) {
		this.sponsor_name = sponsor_name;
	}
	public Integer getTotal_disbursement() {
		return total_disbursement;
	}
	public void setTotal_disbursement(Integer total_disbursement) {
		this.total_disbursement = total_disbursement;
	}
	public Integer getTotal_settlement() {
		return total_settlement;
	}
	public void setTotal_settlement(Integer total_settlement) {
		this.total_settlement = total_settlement;
	}
}
