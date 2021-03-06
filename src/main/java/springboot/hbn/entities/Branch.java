package springboot.hbn.entities;

// default package
// Generated Aug 12, 2021, 11:35:37 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Branch generated by hbm2java
 */
@Entity
@Table(name = "branch")
public class Branch implements java.io.Serializable {

	private Integer row_id;
	private String branch_name;
	private String manager;
	private String phone;
	private String address;
	private int status;
	private String bank_account_name;
	private String bank_note;
	private String bank_message_transfer;
	private String bank_message_eg;
	private String hotline;

	public Branch() {
	}

	public Branch(String branchName, String manager, String phone, String address, int status, String bankAccountName,
			String bankNote, String bankMessageTransfer, String bankMessageEg, String hotline) {
		this.branch_name = branchName;
		this.manager = manager;
		this.phone = phone;
		this.address = address;
		this.status = status;
		this.bank_account_name = bankAccountName;
		this.bank_note = bankNote;
		this.bank_message_transfer = bankMessageTransfer;
		this.bank_message_eg = bankMessageEg;
		this.hotline = hotline;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "row_id", unique = true, nullable = false)
	public Integer getRowId() {
		return this.row_id;
	}

	public void setRowId(Integer rowId) {
		this.row_id = rowId;
	}

	@Column(name = "branch_name", nullable = false, length = 500)
	public String getBranchName() {
		return this.branch_name;
	}

	public void setBranchName(String branchName) {
		this.branch_name = branchName;
	}

	@Column(name = "manager", nullable = false, length = 200)
	public String getManager() {
		return this.manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	@Column(name = "phone", nullable = false, length = 15)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "address", nullable = false, length = 500)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "status", nullable = false)
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "bank_account_name", nullable = false, length = 50)
	public String getBankAccountName() {
		return this.bank_account_name;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bank_account_name = bankAccountName;
	}

	@Column(name = "bank_note", nullable = false, length = 5000)
	public String getBankNote() {
		return this.bank_note;
	}

	public void setBankNote(String bankNote) {
		this.bank_note = bankNote;
	}

	@Column(name = "bank_message_transfer", nullable = false, length = 500)
	public String getBankMessageTransfer() {
		return this.bank_message_transfer;
	}

	public void setBankMessageTransfer(String bankMessageTransfer) {
		this.bank_message_transfer = bankMessageTransfer;
	}

	@Column(name = "bank_message_eg", nullable = false, length = 500)
	public String getBankMessageEg() {
		return this.bank_message_eg;
	}

	public void setBankMessageEg(String bankMessageEg) {
		this.bank_message_eg = bankMessageEg;
	}

	@Column(name = "hotline", nullable = false, length = 15)
	public String getHotline() {
		return this.hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

}
