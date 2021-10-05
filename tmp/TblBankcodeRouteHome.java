// default package
// Generated May 28, 2020 11:10:15 AM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblBankcodeRoute.
 * @see .TblBankcodeRoute
 * @author Hibernate Tools
 */
@Stateless
public class TblBankcodeRouteHome {

	private static final Log log = LogFactory.getLog(TblBankcodeRouteHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblBankcodeRoute transientInstance) {
		log.debug("persisting TblBankcodeRoute instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblBankcodeRoute persistentInstance) {
		log.debug("removing TblBankcodeRoute instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblBankcodeRoute merge(TblBankcodeRoute detachedInstance) {
		log.debug("merging TblBankcodeRoute instance");
		try {
			TblBankcodeRoute result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblBankcodeRoute findById(BigDecimal id) {
		log.debug("getting TblBankcodeRoute instance with id: " + id);
		try {
			TblBankcodeRoute instance = entityManager.find(TblBankcodeRoute.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
