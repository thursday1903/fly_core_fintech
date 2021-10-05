// default package
// Generated May 28, 2020 11:10:15 AM by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblMoneyTranTempFeeAssign.
 * @see .TblMoneyTranTempFeeAssign
 * @author Hibernate Tools
 */
@Stateless
public class TblMoneyTranTempFeeAssignHome {

	private static final Log log = LogFactory.getLog(TblMoneyTranTempFeeAssignHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblMoneyTranTempFeeAssign transientInstance) {
		log.debug("persisting TblMoneyTranTempFeeAssign instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblMoneyTranTempFeeAssign persistentInstance) {
		log.debug("removing TblMoneyTranTempFeeAssign instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblMoneyTranTempFeeAssign merge(TblMoneyTranTempFeeAssign detachedInstance) {
		log.debug("merging TblMoneyTranTempFeeAssign instance");
		try {
			TblMoneyTranTempFeeAssign result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblMoneyTranTempFeeAssign findById(long id) {
		log.debug("getting TblMoneyTranTempFeeAssign instance with id: " + id);
		try {
			TblMoneyTranTempFeeAssign instance = entityManager.find(TblMoneyTranTempFeeAssign.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
