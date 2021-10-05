// default package
// Generated May 28, 2020 11:10:15 AM by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblMoneyTransferCheckUser.
 * @see .TblMoneyTransferCheckUser
 * @author Hibernate Tools
 */
@Stateless
public class TblMoneyTransferCheckUserHome {

	private static final Log log = LogFactory.getLog(TblMoneyTransferCheckUserHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblMoneyTransferCheckUser transientInstance) {
		log.debug("persisting TblMoneyTransferCheckUser instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblMoneyTransferCheckUser persistentInstance) {
		log.debug("removing TblMoneyTransferCheckUser instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblMoneyTransferCheckUser merge(TblMoneyTransferCheckUser detachedInstance) {
		log.debug("merging TblMoneyTransferCheckUser instance");
		try {
			TblMoneyTransferCheckUser result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblMoneyTransferCheckUser findById(TblMoneyTransferCheckUserId id) {
		log.debug("getting TblMoneyTransferCheckUser instance with id: " + id);
		try {
			TblMoneyTransferCheckUser instance = entityManager.find(TblMoneyTransferCheckUser.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
