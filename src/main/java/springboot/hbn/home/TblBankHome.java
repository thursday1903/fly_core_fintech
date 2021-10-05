package springboot.hbn.home;

import java.util.List;

import springboot.hbn.entities.TblLoanBill;

// default package
// Generated Jun 23, 2021, 11:32:10 PM by Hibernate Tools 4.3.5.Final

/**
 * Home object for domain model class TblLoanBill.
 * @see .TblLoanBill
 * @author Hibernate Tools
 */

public class TblBankHome extends BaseHibernateHome{

	public static void main(String[] args) {
		TblBankHome tblLoanBillHome = new TblBankHome();
		TblLoanBill tblLoanBill = new TblLoanBill();
		try {
			Object obj = tblLoanBillHome.findById(1785, tblLoanBill);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
