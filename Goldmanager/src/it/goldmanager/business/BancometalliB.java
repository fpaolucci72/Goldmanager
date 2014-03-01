package it.goldmanager.business;

import it.goldmanager.common.GoldmanagerDefine;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.GoldmanagerUtility;
import it.goldmanager.common.HibernateUtil;
import it.goldmanager.databean.Bancometalli;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

public class BancometalliB {

	@SuppressWarnings("unchecked")
	public ArrayList<Bancometalli> getBancometalli() {
		GoldmanagerLogger.debug(BancometalliB.class, "getBancometalli", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Transaction tx = null;
		Session session = null;
		ArrayList<Bancometalli> bm = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Bancometalli.class);
			criteria.addOrder(Order.asc(GoldmanagerDefine.BANCOMETALLI_INTESTAZIONE));
			bm = (ArrayList<Bancometalli>) criteria.list();
			session.flush();
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(BancometalliB.class, "Data op: " + GoldmanagerUtility.today() + " runtime exception ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(BancometalliB.class, "Data op: " + GoldmanagerUtility.today() + " trovati bancometalli " + bm, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return bm;
	}

	public Bancometalli getBancometalli(String _idBancometalli) {
		GoldmanagerLogger.debug(BancometalliB.class, "Data op: " + GoldmanagerUtility.today() + " getBancometalli ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		Bancometalli banco = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			banco = (Bancometalli) session.get(Bancometalli.class, Integer.parseInt(_idBancometalli));
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(BancometalliB.class, "Data op: " + GoldmanagerUtility.today() + " getBancometalli rollback ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(BancometalliB.class, "Data op: " + GoldmanagerUtility.today() + " trovato bancometalli " + banco, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return banco;
	}

	public void saveOrUpBancometalli(Bancometalli _bancometalli) {
		GoldmanagerLogger.debug(BancometalliB.class, "saveOrUpBancometalli init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(_bancometalli);
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(BancometalliB.class, "saveOrUpBancometalli rollback ", null);
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(BancometalliB.class, "saveOrUpBancometalli effettuato per intestazione: " + _bancometalli.getIntestazione(), GoldmanagerSession.getCurrentInstance().getUtente()
		        .getUsername());
	}

	public void delBancometalli(int _idbancometalli) {
		GoldmanagerLogger.debug(BancometalliB.class, "Data op: " + GoldmanagerUtility.today() + " delBancometalli ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		String intestazionedel;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Bancometalli bm = (Bancometalli) session.get(Bancometalli.class, _idbancometalli);
			intestazionedel = bm.getIntestazione();
			session.delete(bm);
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(BancometalliB.class, "Data op: " + GoldmanagerUtility.today() + " delBancometalli rollback ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(BancometalliB.class, "Data op: " + GoldmanagerUtility.today() + " delBancometalli intestazione: " + intestazionedel, GoldmanagerSession.getCurrentInstance().getUtente()
		        .getUsername());
	}
}
