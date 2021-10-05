package springboot.hbn.home;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import springboot.hbn.entities.TblBanks;
import springboot.hbn.entities.TblBranchBank;

// default package

/**
 * Home object for domain model class TblLoanRequest.
 * 
 * @see .TblLoanRequest
 * @author Hibernate Tools
 */

public class TblBranchBankHome extends BaseHibernateHome {


	public List<Object> getListBank(Integer branchId) throws Exception {
		// log.debug("finding TblACategories instance by example");

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria listTrans = session.createCriteria(TblBranchBank.class);
			Criterion name = Restrictions.eq("branchId", branchId);
			listTrans.add(name);
			// listTrans.setMaxResults(1);
			@SuppressWarnings("unchecked")
			List<Object> listAcc = listTrans.list();
			return listAcc;
		} catch (Exception re) {
			log.fatal("find by example failed", re);
			// composeAlertAndSend(re);
			throw re;
		} finally {
			releaseSession(session);
		}
	}
}
