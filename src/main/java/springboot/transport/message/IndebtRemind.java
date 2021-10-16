package springboot.transport.message;

import com.sun.jersey.core.header.InBoundHeaders;

import springboot.utils.GsonUltilities;

/**
 * @author T490
 * Lớp generate đối tượng nhắc nợ
 */
public class IndebtRemind {
	String loan_code;
	Integer loan_id;
	Integer bill_id;
	
	public String getLoan_code() {
		return loan_code;
	}
	public void setLoan_code(String loan_code) {
		this.loan_code = loan_code;
	}
	public Integer getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(Integer loan_id) {
		this.loan_id = loan_id;
	}
	public Integer getBill_id() {
		return bill_id;
	}
	public void setBill_id(Integer bill_id) {
		this.bill_id = bill_id;
	}
	
	String emailContent;
	String receiveEmail;
	String receivePhone;
	String smsContent;
	String emailSubject;

	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getSmsContent() {
		return smsContent;
	}
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	public String getReceivePhone() {
		return receivePhone;
	}
	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}
	
	public String getEmailContent() {
		return emailContent;
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	public String getReceiveEmail() {
		return receiveEmail;
	}
	public void setReceiveEmail(String receiveEmail) {
		this.receiveEmail = receiveEmail;
	}
	@Override
	public String toString() {
		return "IndebtRemind [loan_code=" + loan_code + ", loan_id=" + loan_id + ", bill_id=" + bill_id
				+ ", emailContent=" + emailContent + ", receiveEmail=" + receiveEmail + ", receivePhone=" + receivePhone
				+ ", smsContent=" + smsContent + ", emailSubject=" + emailSubject + "]";
	}
	
	public static void main(String[] args) {
		IndebtRemind indebtRemind = new IndebtRemind();
		indebtRemind.setBill_id(4273);
		indebtRemind.setLoan_id(348);
		indebtRemind.setLoan_code("BHVN.54.10411");
		System.out.println(GsonUltilities.toJson(indebtRemind));
		
		String json = "{\"loan_code\":\"BHVN.54.10411\",\"loan_id\":348,\"bill_id\":4273}";
	}
}
