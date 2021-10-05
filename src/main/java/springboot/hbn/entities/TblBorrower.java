package springboot.hbn.entities;

// default package
// Generated May 29, 2021, 3:10:44 PM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.domain.Example;

/**
 * TblBorrower generated by hbm2java
 */
@Entity
@Table(name = "tbl_borrower")
public class TblBorrower extends MongoBaseObj {

	private Integer borrower_id;
	private String borrower_name;
	private String borrower_mobile;
	private String borrower_email;
	private String id_number;
	private String id_img_front;
	private String id_img_back;
	private String license_drive_number;
	private String borrower_code;
	private String borrower_address;
	private Date borrower_birth_day;
	private String id_issued_by;
	private Date id_issued_date;
	private String bank_account;
	private String bank_code;
	private String bank_name;
	private String bank_branch;
	private int borrower_type;
	private int branch_id;

	public TblBorrower() {
	}

	
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "borrower_id", unique = true, nullable = false)
	public Integer getBorrowerId() {
		return this.borrower_id;
	}

	public void setBorrowerId(Integer borrowerId) {
		this.borrower_id = borrowerId;
	}

	@Column(name = "borrower_name", nullable = false, length = 100)
	public String getBorrowerName() {
		return this.borrower_name;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrower_name = borrowerName;
	}

	@Column(name = "borrower_mobile", nullable = false, length = 500)
	public String getBorrowerMobile() {
		return this.borrower_mobile;
	}

	public void setBorrowerMobile(String borrowerMobile) {
		this.borrower_mobile = borrowerMobile;
	}

	@Column(name = "borrower_email", nullable = false, length = 100)
	public String getBorrowerEmail() {
		return this.borrower_email;
	}

	public void setBorrowerEmail(String borrowerEmail) {
		this.borrower_email = borrowerEmail;
	}

	@Column(name = "id_number", nullable = false, length = 20)
	public String getIdNumber() {
		return this.id_number;
	}

	public void setIdNumber(String idNumber) {
		this.id_number = idNumber;
	}

	@Column(name = "id_img_front", nullable = false, length = 200)
	public String getIdImgFront() {
		return this.id_img_front;
	}

	public void setIdImgFront(String idImgFront) {
		this.id_img_front = idImgFront;
	}

	@Column(name = "id_img_back", nullable = false, length = 200)
	public String getIdImgBack() {
		return this.id_img_back;
	}

	public void setIdImgBack(String idImgBack) {
		this.id_img_back = idImgBack;
	}

	@Column(name = "license_drive_number", nullable = false, length = 50)
	public String getLicenseDriveNumber() {
		return this.license_drive_number;
	}

	public void setLicenseDriveNumber(String licenseDriveNumber) {
		this.license_drive_number = licenseDriveNumber;
	}

	@Column(name = "borrower_code", nullable = false, length = 20)
	public String getBorrowerCode() {
		return this.borrower_code;
	}

	public void setBorrowerCode(String borrowerCode) {
		this.borrower_code = borrowerCode;
	}

	@Column(name = "borrower_address", nullable = false, length = 200)
	public String getBorrowerAddress() {
		return this.borrower_address;
	}

	public void setBorrowerAddress(String borrowerAddress) {
		this.borrower_address = borrowerAddress;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "borrower_birth_day", nullable = false, length = 10)
	public Date getBorrowerBirthDay() {
		return this.borrower_birth_day;
	}

	public void setBorrowerBirthDay(Date borrowerBirthDay) {
		this.borrower_birth_day = borrowerBirthDay;
	}

	@Column(name = "id_issued_by", nullable = false, length = 200)
	public String getIdIssuedBy() {
		return this.id_issued_by;
	}

	public void setIdIssuedBy(String idIssuedBy) {
		this.id_issued_by = idIssuedBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "id_issued_date", nullable = false, length = 10)
	public Date getIdIssuedDate() {
		return this.id_issued_date;
	}

	public void setIdIssuedDate(Date idIssuedDate) {
		this.id_issued_date = idIssuedDate;
	}

	@Column(name = "bank_account", nullable = false, length = 20)
	public String getBankAccount() {
		return this.bank_account;
	}

	public void setBankAccount(String bankAccount) {
		this.bank_account = bankAccount;
	}

	@Column(name = "bank_code", nullable = false, length = 20)
	public String getBankCode() {
		return this.bank_code;
	}

	public void setBankCode(String bankCode) {
		this.bank_code = bankCode;
	}

	@Column(name = "bank_name", nullable = false, length = 200)
	public String getBankName() {
		return this.bank_name;
	}

	public void setBankName(String bankName) {
		this.bank_name = bankName;
	}

	@Column(name = "bank_branch", nullable = false, length = 200)
	public String getBankBranch() {
		return this.bank_branch;
	}

	public void setBankBranch(String bankBranch) {
		this.bank_branch = bankBranch;
	}

	@Column(name = "borrower_type", nullable = false)
	public int getBorrowerType() {
		return this.borrower_type;
	}

	public void setBorrowerType(int borrowerType) {
		this.borrower_type = borrowerType;
	}

	@Column(name = "branch_id", nullable = false)
	public int getBranchId() {
		return this.branch_id;
	}

	public void setBranchId(int branchId) {
		this.branch_id = branchId;
	}

}