package springboot.hbn.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TblInsuranceProviders generated by hbm2java
 */
@Entity
@Table(name = "tbl_insurance_providers")
public class TblInsuranceProviders implements java.io.Serializable {

	private Long insurance_provider_id;
	private String insurance_code;
	private String insurance_name;
	private Integer active_stt;
	private String created_date;
	private String edited_date;
	
	private Integer fix_fee;
	private Float fix_rate;
	private String insurance_api;
	private String auth_token;
	private String api_acc;
	private String api_pass;
	private String auth_token_expires;
	private Integer insurance_default;
	private Integer include_vat_fee;
	private Integer include_interest;
	
	private String bank_message_transfer;
	private String bank_message_eg;
	private String hotline;

	
	@Column(name = "bank_message_transfer")
	public String getBank_message_transfer() {
		return bank_message_transfer;
	}

	public void setBank_message_transfer(String bank_message_transfer) {
		this.bank_message_transfer = bank_message_transfer;
	}
	@Column(name = "bank_message_eg")
	public String getBank_message_eg() {
		return bank_message_eg;
	}

	public void setBank_message_eg(String bank_message_eg) {
		this.bank_message_eg = bank_message_eg;
	}

	@Column(name = "hotline")
	public String getHotline() {
		return hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	public Integer getInclude_vat_fee() {
		return include_vat_fee;
	}

	public void setInclude_vat_fee(Integer include_vat_fee) {
		this.include_vat_fee = include_vat_fee;
	}

	public Integer getInclude_interest() {
		return include_interest;
	}

	public void setInclude_interest(Integer include_interest) {
		this.include_interest = include_interest;
	}

	@Column(name = "insurance_api")
	public String getInsurance_api() {
		return insurance_api;
	}

	public void setInsurance_api(String insurance_api) {
		this.insurance_api = insurance_api;
	}

	@Column(name = "auth_token")
	public String getAuth_token() {
		return auth_token;
	}

	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}

	@Column(name = "api_acc")
	public String getApi_acc() {
		return api_acc;
	}

	public void setApi_acc(String api_acc) {
		this.api_acc = api_acc;
	}

	@Column(name = "api_pass")
	public String getApi_pass() {
		return api_pass;
	}

	public void setApi_pass(String api_pass) {
		this.api_pass = api_pass;
	}

	@Column(name = "auth_token_expires")
	public String getAuth_token_expires() {
		return auth_token_expires;
	}

	public void setAuth_token_expires(String auth_token_expires) {
		this.auth_token_expires = auth_token_expires;
	}

	@Column(name = "insurance_default")
	public Integer getDefault() {
		return insurance_default;
	}

	public void setDefault(Integer default1) {
		insurance_default = default1;
	}

	public Integer getFix_fee() {
		return fix_fee;
	}

	public void setFix_fee(Integer fix_fee) {
		this.fix_fee = fix_fee;
	}

	public Float getFix_rate() {
		return fix_rate;
	}

	public void setFix_rate(Float fix_rate) {
		this.fix_rate = fix_rate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "insurance_provider_id", unique = true, nullable = false)
	public Long getInsuranceProviderId() {
		return this.insurance_provider_id;
	}

	public void setInsuranceProviderId(Long insuranceProviderId) {
		this.insurance_provider_id = insuranceProviderId;
	}

	@Column(name = "insurance_code")
	public String getInsuranceCode() {
		return this.insurance_code;
	}

	public void setInsuranceCode(String insuranceCode) {
		this.insurance_code = insuranceCode;
	}

	@Column(name = "insurance_name")
	public String getInsuranceName() {
		return this.insurance_name;
	}

	public void setInsuranceName(String insuranceName) {
		this.insurance_name = insuranceName;
	}

	@Column(name = "active_stt")
	public Integer getActiveStt() {
		return this.active_stt;
	}

	public void setActiveStt(Integer activeStt) {
		this.active_stt = activeStt;
	}

}
