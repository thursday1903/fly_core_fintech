// default package
// Generated May 28, 2020 11:10:15 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TblMoneyTranTempFeeAssign generated by hbm2java
 */
@Entity
@Table(name = "TBL_MONEY_TRAN_TEMP_FEE_ASSIGN")
public class TblMoneyTranTempFeeAssign implements java.io.Serializable {

	private long id;
	private String partnerCode;
	private Boolean typeFee;
	private String tempFeeCode;
	private boolean status;
	private Date timeUse;
	private String userRequest;
	private String userApprove;
	private Date timeApprove;
	private Long fixedFee;
	private Double rateFee;
	private Long minFee;
	private Long maxFee;
	private Boolean payer;
	private Boolean typePaid;
	private Date createdAt;
	private Date updatedAt;
	private Boolean typeTempFee;
	private String note;
	private String userUpdate;
	private Date timeUpdate;
	private long tempFeeId;

	public TblMoneyTranTempFeeAssign() {
	}

	public TblMoneyTranTempFeeAssign(long id, String partnerCode, String tempFeeCode, boolean status, long tempFeeId) {
		this.id = id;
		this.partnerCode = partnerCode;
		this.tempFeeCode = tempFeeCode;
		this.status = status;
		this.tempFeeId = tempFeeId;
	}

	public TblMoneyTranTempFeeAssign(long id, String partnerCode, Boolean typeFee, String tempFeeCode, boolean status,
			Date timeUse, String userRequest, String userApprove, Date timeApprove, Long fixedFee, Double rateFee,
			Long minFee, Long maxFee, Boolean payer, Boolean typePaid, Date createdAt, Date updatedAt,
			Boolean typeTempFee, String note, String userUpdate, Date timeUpdate, long tempFeeId) {
		this.id = id;
		this.partnerCode = partnerCode;
		this.typeFee = typeFee;
		this.tempFeeCode = tempFeeCode;
		this.status = status;
		this.timeUse = timeUse;
		this.userRequest = userRequest;
		this.userApprove = userApprove;
		this.timeApprove = timeApprove;
		this.fixedFee = fixedFee;
		this.rateFee = rateFee;
		this.minFee = minFee;
		this.maxFee = maxFee;
		this.payer = payer;
		this.typePaid = typePaid;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.typeTempFee = typeTempFee;
		this.note = note;
		this.userUpdate = userUpdate;
		this.timeUpdate = timeUpdate;
		this.tempFeeId = tempFeeId;
	}

	@Id

	@Column(name = "ID", unique = true, nullable = false, precision = 11, scale = 0)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "PARTNER_CODE", nullable = false, length = 30)
	public String getPartnerCode() {
		return this.partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	@Column(name = "TYPE_FEE", precision = 1, scale = 0)
	public Boolean getTypeFee() {
		return this.typeFee;
	}

	public void setTypeFee(Boolean typeFee) {
		this.typeFee = typeFee;
	}

	@Column(name = "TEMP_FEE_CODE", nullable = false, length = 50)
	public String getTempFeeCode() {
		return this.tempFeeCode;
	}

	public void setTempFeeCode(String tempFeeCode) {
		this.tempFeeCode = tempFeeCode;
	}

	@Column(name = "STATUS", nullable = false, precision = 1, scale = 0)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TIME_USE", length = 7)
	public Date getTimeUse() {
		return this.timeUse;
	}

	public void setTimeUse(Date timeUse) {
		this.timeUse = timeUse;
	}

	@Column(name = "USER_REQUEST", length = 30)
	public String getUserRequest() {
		return this.userRequest;
	}

	public void setUserRequest(String userRequest) {
		this.userRequest = userRequest;
	}

	@Column(name = "USER_APPROVE", length = 30)
	public String getUserApprove() {
		return this.userApprove;
	}

	public void setUserApprove(String userApprove) {
		this.userApprove = userApprove;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TIME_APPROVE", length = 7)
	public Date getTimeApprove() {
		return this.timeApprove;
	}

	public void setTimeApprove(Date timeApprove) {
		this.timeApprove = timeApprove;
	}

	@Column(name = "FIXED_FEE", precision = 11, scale = 0)
	public Long getFixedFee() {
		return this.fixedFee;
	}

	public void setFixedFee(Long fixedFee) {
		this.fixedFee = fixedFee;
	}

	@Column(name = "RATE_FEE", precision = 126, scale = 0)
	public Double getRateFee() {
		return this.rateFee;
	}

	public void setRateFee(Double rateFee) {
		this.rateFee = rateFee;
	}

	@Column(name = "MIN_FEE", precision = 11, scale = 0)
	public Long getMinFee() {
		return this.minFee;
	}

	public void setMinFee(Long minFee) {
		this.minFee = minFee;
	}

	@Column(name = "MAX_FEE", precision = 11, scale = 0)
	public Long getMaxFee() {
		return this.maxFee;
	}

	public void setMaxFee(Long maxFee) {
		this.maxFee = maxFee;
	}

	@Column(name = "PAYER", precision = 1, scale = 0)
	public Boolean getPayer() {
		return this.payer;
	}

	public void setPayer(Boolean payer) {
		this.payer = payer;
	}

	@Column(name = "TYPE_PAID", precision = 1, scale = 0)
	public Boolean getTypePaid() {
		return this.typePaid;
	}

	public void setTypePaid(Boolean typePaid) {
		this.typePaid = typePaid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_AT", length = 7)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED_AT", length = 7)
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Column(name = "TYPE_TEMP_FEE", precision = 1, scale = 0)
	public Boolean getTypeTempFee() {
		return this.typeTempFee;
	}

	public void setTypeTempFee(Boolean typeTempFee) {
		this.typeTempFee = typeTempFee;
	}

	@Column(name = "NOTE", length = 500)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "USER_UPDATE", length = 30)
	public String getUserUpdate() {
		return this.userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TIME_UPDATE", length = 7)
	public Date getTimeUpdate() {
		return this.timeUpdate;
	}

	public void setTimeUpdate(Date timeUpdate) {
		this.timeUpdate = timeUpdate;
	}

	@Column(name = "TEMP_FEE_ID", nullable = false, precision = 11, scale = 0)
	public long getTempFeeId() {
		return this.tempFeeId;
	}

	public void setTempFeeId(long tempFeeId) {
		this.tempFeeId = tempFeeId;
	}

}
