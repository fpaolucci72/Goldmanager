package it.goldmanager.business;

import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.GoldmanagerUtility;
import it.goldmanager.common.HibernateUtil;
import it.goldmanager.databean.Negozio;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class NegozioB {

	@SuppressWarnings("unchecked")
	public ArrayList<Negozio> getNegozio() {
		GoldmanagerLogger.debug(NegozioB.class, "getNegozio", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Transaction tx = null;
		Session session = null;
		ArrayList<Negozio> neg = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Negozio.class);
			neg = (ArrayList<Negozio>) criteria.list();
			session.flush();
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(NegozioB.class, "Data op: " + GoldmanagerUtility.today() + " runtime exception ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(NegozioB.class, "Data op: " + GoldmanagerUtility.today() + " trovato negozio " + neg, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return neg;
	}

	public Negozio getNegozio(String _idNegozio) {
		GoldmanagerLogger.debug(NegozioB.class, "getNegozio", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Transaction tx = null;
		Session session = null;
		Negozio neg = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			neg = (Negozio) session.get(Negozio.class, new Integer(_idNegozio));
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(NegozioB.class, "Data op: " + GoldmanagerUtility.today() + " runtime exception ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(NegozioB.class, "Data op: " + GoldmanagerUtility.today() + " trovato negozio " + neg, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return neg;
	}
}
