package it.goldmanager.business;

import it.goldmanager.common.CryptManager;
import it.goldmanager.common.GoldmanagerDefine;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.GoldmanagerUtility;
import it.goldmanager.common.HibernateUtil;
import it.goldmanager.databean.Utente;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class Login {

	public Utente getUtente(String user, String password) {
		GoldmanagerLogger.debug(Login.class, "caricaUtente", null);
		Transaction tx = null;
		Session session = null;
		Utente u = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Utente.class);
			criteria.add(Restrictions.eq(GoldmanagerDefine.UTENTE_USERNAME, user));
			CryptManager crypt = new CryptManager("DES");
			String encryptedLogin = crypt.encryptHexString(password);
			criteria.add(Restrictions.eq(GoldmanagerDefine.UTENTE_PASSWORD, encryptedLogin));
			u = (Utente) criteria.uniqueResult();
			session.flush();
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(Login.class, "Data op: " + GoldmanagerUtility.today() + " runtime exception ", null);
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(Login.class, "Data op: " + GoldmanagerUtility.today() + " trovato utente " + u, null);
		return u;
	}

	public void updateUtente(Utente _utente) {
		GoldmanagerLogger.info(Login.class, "updateUtente init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.merge(_utente);
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(Login.class, "updateUtente rollback ", null);
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.debug(Login.class, "updateUtente effettuato per id: " + _utente.getIdUtente(), GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
	}
}
