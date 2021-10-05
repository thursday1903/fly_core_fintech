package springboot.hbn.entities;

// default package
// Generated Jul 11, 2021, 2:58:25 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TblInsuranceFeeMappApi generated by hbm2java
 */
@Entity
@Table(name = "tbl_insurance_fee_mapp_api")
public class TblInsuranceFeeMappApi implements java.io.Serializable {

	private Integer mapping_id;
	private int insurance_provider_id;
	private int insurance_fee_group_id;

	public TblInsuranceFeeMappApi() {
	}

	public TblInsuranceFeeMappApi(int insuranceProviderId, int insuranceFeeGroupId) {
		this.insurance_provider_id = insuranceProviderId;
		this.insurance_fee_group_id = insuranceFeeGroupId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "mapping_id", unique = true, nullable = false)
	public Integer getMappingId() {
		return this.mapping_id;
	}

	public void setMappingId(Integer mappingId) {
		this.mapping_id = mappingId;
	}

	@Column(name = "insurance_provider_id", nullable = false)
	public int getInsuranceProviderId() {
		return this.insurance_provider_id;
	}

	public void setInsuranceProviderId(int insuranceProviderId) {
		this.insurance_provider_id = insuranceProviderId;
	}

	@Column(name = "insurance_fee_group_id", nullable = false)
	public int getInsuranceFeeGroupId() {
		return this.insurance_fee_group_id;
	}

	public void setInsuranceFeeGroupId(int insuranceFeeGroupId) {
		this.insurance_fee_group_id = insuranceFeeGroupId;
	}

}
