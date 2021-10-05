package springboot.hbn.entities;

import static javax.persistence.GenerationType.IDENTITY;

// default package
// Generated May 29, 2021, 3:10:44 PM by Hibernate Tools 4.3.5.Final

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TblProduct generated by hbm2java
 */
@Entity
@Table(name = "tbl_product")
public class TblProduct extends MongoBaseObj {

	private Integer product_id;
	private String product_name;
	private String product_code;
	private int product_type;
	private Integer product_brand_id;
	private Long brandnew_price;
	private Long loan_price;
//	private Date created_date;
	private String registered_time;
	private int product_status;

	public TblProduct() {
	}

	

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "product_id", unique = true, nullable = false)
	public Integer getProductId() {
		return this.product_id;
	}

	public void setProductId(Integer productId) {
		this.product_id = productId;
	}

	@Column(name = "product_name", nullable = false, length = 50)
	public String getProductName() {
		return this.product_name;
	}

	public void setProductName(String productName) {
		this.product_name = productName;
	}

	@Column(name = "product_code", nullable = false, length = 50)
	public String getProductCode() {
		return this.product_code;
	}

	public void setProductCode(String productCode) {
		this.product_code = productCode;
	}

	@Column(name = "product_type", nullable = false)
	public int getProductType() {
		return this.product_type;
	}

	public void setProductType(int productType) {
		this.product_type = productType;
	}

	@Column(name = "product_brand_id")
	public Integer getProductBrandId() {
		return this.product_brand_id;
	}

	public void setProductBrandId(Integer productBrandId) {
		this.product_brand_id = productBrandId;
	}

	@Column(name = "brandnew_price", precision = 10, scale = 0)
	public Long getBrandnewPrice() {
		return this.brandnew_price;
	}

	public void setBrandnewPrice(Long brandnewPrice) {
		this.brandnew_price = brandnewPrice;
	}

	@Column(name = "loan_price", precision = 10, scale = 0)
	public Long getLoanPrice() {
		return this.loan_price;
	}

	public void setLoanPrice(Long loanPrice) {
		this.loan_price = loanPrice;
	}

//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "created_date", nullable = false, length = 19)
//	public Date getCreatedDate() {
//		return this.created_date;
//	}
//
//	public void setCreatedDate(Date createdDate) {
//		this.created_date = createdDate;
//	}

	@Column(name = "registered_time")
	public String getRegisteredTime() {
		return this.registered_time;
	}

	public void setRegisteredTime(String registeredTime) {
		this.registered_time = registeredTime;
	}

	@Column(name = "product_status", nullable = false)
	public int getProductStatus() {
		return this.product_status;
	}

	public void setProductStatus(int productStatus) {
		this.product_status = productStatus;
	}

}
