// default package
// Generated May 28, 2020 11:10:15 AM by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblMoneyTransferTransaction.
 * @see .TblMoneyTransferTransaction
 * @author Hibernate Tools
 */
@Stateless
public class TblMoneyTransferTransactionHome {

	private static final Log log = LogFactory.getLog(TblMoneyTransferTransactionHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblMoneyTransferTransaction transientInstance) {
		log.debug("persisting TblMoneyTransferTransaction instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblMoneyTransferTransaction persistentInstance) {
		log.debug("removing TblMoneyTransferTransaction instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblMoneyTransferTransaction merge(TblMoneyTransferTransaction detachedInstance) {
		log.debug("merging TblMoneyTransferTransaction instance");
		try {
			TblMoneyTransferTransaction result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblMoneyTransferTransaction findById(String id) {
		log.debug("getting TblMoneyTransferTransaction instance with id: " + id);
		try {
			TblMoneyTransferTransaction instance = entityManager.find(TblMoneyTransferTransaction.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
