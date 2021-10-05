// default package
// Generated May 28, 2020 11:10:15 AM by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblMoneyTranTempFee.
 * @see .TblMoneyTranTempFee
 * @author Hibernate Tools
 */
@Stateless
public class TblMoneyTranTempFeeHome {

	private static final Log log = LogFactory.getLog(TblMoneyTranTempFeeHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblMoneyTranTempFee transientInstance) {
		log.debug("persisting TblMoneyTranTempFee instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblMoneyTranTempFee persistentInstance) {
		log.debug("removing TblMoneyTranTempFee instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblMoneyTranTempFee merge(TblMoneyTranTempFee detachedInstance) {
		log.debug("merging TblMoneyTranTempFee instance");
		try {
			TblMoneyTranTempFee result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblMoneyTranTempFee findById(long id) {
		log.debug("getting TblMoneyTranTempFee instance with id: " + id);
		try {
			TblMoneyTranTempFee instance = entityManager.find(TblMoneyTranTempFee.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
