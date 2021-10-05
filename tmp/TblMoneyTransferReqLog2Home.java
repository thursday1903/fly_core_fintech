// default package
// Generated May 28, 2020 11:10:15 AM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblMoneyTransferReqLog2.
 * @see .TblMoneyTransferReqLog2
 * @author Hibernate Tools
 */
@Stateless
public class TblMoneyTransferReqLog2Home {

	private static final Log log = LogFactory.getLog(TblMoneyTransferReqLog2Home.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblMoneyTransferReqLog2 transientInstance) {
		log.debug("persisting TblMoneyTransferReqLog2 instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblMoneyTransferReqLog2 persistentInstance) {
		log.debug("removing TblMoneyTransferReqLog2 instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblMoneyTransferReqLog2 merge(TblMoneyTransferReqLog2 detachedInstance) {
		log.debug("merging TblMoneyTransferReqLog2 instance");
		try {
			TblMoneyTransferReqLog2 result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblMoneyTransferReqLog2 findById(BigDecimal id) {
		log.debug("getting TblMoneyTransferReqLog2 instance with id: " + id);
		try {
			TblMoneyTransferReqLog2 instance = entityManager.find(TblMoneyTransferReqLog2.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
