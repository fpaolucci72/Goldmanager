package it.goldmanager.business;

import it.goldmanager.common.GoldmanagerDefine;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.GoldmanagerUtility;
import it.goldmanager.common.HibernateUtil;
import it.goldmanager.databean.Attovendita;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class AttovenditaB {

	public int saveAttovendita(Attovendita _attovendita) {
		GoldmanagerLogger.info(AttovenditaB.class, "saveAttovendita init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		int id;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.save(_attovendita);
			session.flush();
			id = _attovendita.getIdAttovendita();
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(AttovenditaB.class, "saveAttovendita rollback ", null);
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.debug(AttovenditaB.class, "saveAttovendita effettuato per id: " + _attovendita.getIdAttovendita(), GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return id;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Attovendita> getAllAttovendita() {
		GoldmanagerLogger.debug(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " getAllAttovendita ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		ArrayList<Attovendita> atto = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Attovendita.class);
			criteria.addOrder(Order.desc(GoldmanagerDefine.ATTOVENDITA_NUMERO_ATTOVENDITA));
			atto = (ArrayList<Attovendita>) criteria.list();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " getAllAttovendita rollback ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " trovati attovendita " + atto.size(), GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return atto;
	}

	public void delAttovendita(int _idattovendita) {
		GoldmanagerLogger.debug(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " delAttovendita ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		int attonum;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Attovendita atto = (Attovendita) session.get(Attovendita.class, _idattovendita);
			attonum = atto.getIdAttovendita();
			session.delete(atto);
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " delAttovendita rollback ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " delAttovendita atto num: " + attonum, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
	}

	public Attovendita getAttovendita(String _idAttovendita) {
		GoldmanagerLogger.debug(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " getAttovendita ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		Attovendita atto = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			atto = (Attovendita) session.get(Attovendita.class, Integer.parseInt(_idAttovendita));
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " getAttovendita rollback ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " trovato attovendita " + atto, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return atto;
	}

	public void updateAttovendita(Attovendita _attovendita) {
		GoldmanagerLogger.info(AttovenditaB.class, "updateAttovendita init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.merge(_attovendita);
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(AttovenditaB.class, "updateAttovendita rollback ", null);
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.debug(AttovenditaB.class, "updateAttovendita effettuato per id: " + _attovendita.getIdAttovendita(), GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Attovendita> getAllAttovendita(String _tipoMetallo) {
		GoldmanagerLogger.debug(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " getAllAttovendita ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		ArrayList<Attovendita> atto = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Attovendita.class);
			criteria.add(Restrictions.eq(GoldmanagerDefine.ATTOVENDITA_TIPO_METALLO, _tipoMetallo));
			criteria.add(Restrictions.isNotNull(GoldmanagerDefine.ATTOVENDITA_NUMERO_ATTOVENDITA));
			criteria.add(Restrictions.isNull(GoldmanagerDefine.ATTOVENDITA_BOLLAVENDITA));
			criteria.addOrder(Order.desc(GoldmanagerDefine.ATTOVENDITA_NUMERO_ATTOVENDITA));
			atto = (ArrayList<Attovendita>) criteria.list();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " getAllAttovendita rollback ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " trovati attovendita " + atto.size(), GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return atto;
	}
}
