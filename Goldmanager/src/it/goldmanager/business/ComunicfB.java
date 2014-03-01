package it.goldmanager.business;

import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.GoldmanagerUtility;
import it.goldmanager.common.HibernateUtil;
import it.goldmanager.databean.Comunicf;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ComunicfB {

	@SuppressWarnings("unchecked")
	public List<Comunicf> getComunicfB() {
		GoldmanagerLogger.debug(ComunicfB.class, "getComunicfB", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Transaction tx = null;
		Session session = null;
		ArrayList<Comunicf> configList = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Comunicf.class);
			configList = (ArrayList<Comunicf>) criteria.list();
			session.flush();
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(ComunicfB.class, "Data op: " + GoldmanagerUtility.today() + " runtime exception ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(ComunicfB.class, "Data op: " + GoldmanagerUtility.today() + " trovati comuni ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return configList;
	}
}
