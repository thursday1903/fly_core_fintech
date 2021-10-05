// default package
// Generated May 28, 2020 11:10:15 AM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblBankConnectors.
 * @see .TblBankConnectors
 * @author Hibernate Tools
 */
@Stateless
public class TblBankConnectorsHome {

	private static final Log log = LogFactory.getLog(TblBankConnectorsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblBankConnectors transientInstance) {
		log.debug("persisting TblBankConnectors instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblBankConnectors persistentInstance) {
		log.debug("removing TblBankConnectors instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblBankConnectors merge(TblBankConnectors detachedInstance) {
		log.debug("merging TblBankConnectors instance");
		try {
			TblBankConnectors result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblBankConnectors findById(BigDecimal id) {
		log.debug("getting TblBankConnectors instance with id: " + id);
		try {
			TblBankConnectors instance = entityManager.find(TblBankConnectors.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
