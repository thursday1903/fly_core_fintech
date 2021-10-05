// default package
// Generated May 28, 2020 11:10:15 AM by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblMoneyTransferPartner.
 * @see .TblMoneyTransferPartner
 * @author Hibernate Tools
 */
@Stateless
public class TblMoneyTransferPartnerHome {

	private static final Log log = LogFactory.getLog(TblMoneyTransferPartnerHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblMoneyTransferPartner transientInstance) {
		log.debug("persisting TblMoneyTransferPartner instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblMoneyTransferPartner persistentInstance) {
		log.debug("removing TblMoneyTransferPartner instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblMoneyTransferPartner merge(TblMoneyTransferPartner detachedInstance) {
		log.debug("merging TblMoneyTransferPartner instance");
		try {
			TblMoneyTransferPartner result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblMoneyTransferPartner findById(long id) {
		log.debug("getting TblMoneyTransferPartner instance with id: " + id);
		try {
			TblMoneyTransferPartner instance = entityManager.find(TblMoneyTransferPartner.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
