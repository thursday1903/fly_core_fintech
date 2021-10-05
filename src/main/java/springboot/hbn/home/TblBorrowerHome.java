package springboot.hbn.home;
// default package
// Generated May 21, 2021, 12:14:23 AM by Hibernate Tools 4.3.5.Final

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import springboot.hbn.entities.TblBorrower;
import springboot.hbn.entities.TblLoanReqDetail;

/**
 * Home object for domain model class TblBorrower.
 * @see .TblBorrower
 * @author Hibernate Tools
 */

public class TblBorrowerHome extends BaseHibernateHome{

	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");
		TblLoanReqDetail tblLoanReqDetail = new TblLoanReqDetail();
		tblLoanReqDetail.setBorrowerFullname("Nguyễn văn Thế");
		tblLoanReqDetail.setLoanId(9999);
		tblLoanReqDetail.setBorrowerAddress("Số 1 ngõ 9 tân mai Hà nội");
		tblLoanReqDetail.setProductColor("Tím mộng mơ");
		tblLoanReqDetail.setBankBranch("Chi nhánh Hà Thành");
		tblLoanReqDetail.setProductDesc("Sản phẩm chạy ngon nghẻ lắm anh chi ơi");
		tblLoanReqDetail.setBorrowerId("0123456800");
//		Integer insertRs = new TblBorrowerHome().persist(tblLoanReqDetail);
//		System.out.println(insertRs);
		TblBorrower tblBorrower = new TblBorrower();
		tblBorrower.setIdNumber("132323232");
		TblBorrowerHome tblBorrowerHome = new TblBorrowerHome();
		try {
			tblBorrowerHome.findBorrowerByIdNumber(tblBorrower);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Object> findBorrowerByIdNumber(TblBorrower tblBorrower) throws Exception {
		log.debug("finding TblLoanReqDetail instance by example");

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria listTrans = session.createCriteria(TblBorrower.class);
			Criterion name = Restrictions.eq("idNumber", tblBorrower.getIdNumber());
			listTrans.add(name);
			// listTrans.setMaxResults(1);
			@SuppressWarnings("unchecked")
			List<Object> listAcc = listTrans.list();
			return listAcc;
		} catch (Exception re) {
			log.fatal("find by findBorrowerByIdNumber failed", re);
			// composeAlertAndSend(re);
			throw re;
		} finally {
			releaseSession(session);
		}
	}
	

}
