package springboot.hbn.entities;
// Generated Dec 10, 2021 9:06:17 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TblVaAccLoan generated by hbm2java
 */
@Entity
@Table(name = "tbl_va_acc_loan")
public class TblVaAccLoan implements java.io.Serializable {

	private Integer vaId;
	private String loanCode;
	private String accountNo;
	private String accountName;
	private String bankCode;
	private String bankName;
	private String mapId;
	private String qrCode;

	public TblVaAccLoan() {
	}

	public TblVaAccLoan(String loanCode, String accountNo, String accountName, String bankCode, String bankName,
			String mapId, String qrCode) {
		this.loanCode = loanCode;
		this.accountNo = accountNo;
		this.accountName = accountName;
		this.bankCode = bankCode;
		this.bankName = bankName;
		this.mapId = mapId;
		this.qrCode = qrCode;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "va_id", unique = true, nullable = false)
	public Integer getVaId() {
		return this.vaId;
	}

	public void setVaId(Integer vaId) {
		this.vaId = vaId;
	}

	@Column(name = "loan_code")
	public String getLoanCode() {
		return this.loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	@Column(name = "account_no", length = 50)
	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@Column(name = "account_name", length = 50)
	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Column(name = "bank_code", length = 50)
	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	@Column(name = "bank_name")
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "map_id", length = 50)
	public String getMapId() {
		return this.mapId;
	}

	public void setMapId(String mapId) {
		this.mapId = mapId;
	}

	@Column(name = "qr_code", length = 65535)
	public String getQrCode() {
		return this.qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

}
