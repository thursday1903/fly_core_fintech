package springboot.hbn.home;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import springboot.hbn.entities.TblLoanReqDetail;

/**
 * Home object for domain model class TblLoanReqDetail.
 * @see .TblLoanReqDetail
 * @author Hibernate Tools
 */

public class TblLoanReqDetailHome extends BaseHibernateHome{

	public TblLoanReqDetail findDetailByLoanId(TblLoanReqDetail tblLoanReqDetail) throws Exception {
		log.debug("finding TblLoanReqDetail instance by example");

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria listTrans = session.createCriteria(TblLoanReqDetail.class);
			Criterion name = Restrictions.eq("loanId", tblLoanReqDetail.getLoanId());
			listTrans.add(name);
			// listTrans.setMaxResults(1);
			@SuppressWarnings("unchecked")
			List<Object> listAcc = listTrans.list();
			if(listAcc.size()>0)
			return (TblLoanReqDetail)listAcc.get(0);
			return null;
		} catch (Exception re) {
			log.fatal("find by example failed", re);
			// composeAlertAndSend(re);
			throw re;
		} finally {
			releaseSession(session);
		}
	}
}
