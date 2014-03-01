package it.goldmanager.business;

import it.goldmanager.common.GoldmanagerDefine;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.GoldmanagerUtility;
import it.goldmanager.common.HibernateUtil;
import it.goldmanager.databean.Attovendita;
import it.goldmanager.databean.Bollavendita;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class BollavenditaB {

	public int saveBollavendita(Bollavendita _bollavendita) {
		GoldmanagerLogger.info(BollavenditaB.class, "saveBollavendita init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		int id;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.save(_bollavendita);
			session.flush();
			id = _bollavendita.getIdBollavendita();
			for (Attovendita a : _bollavendita.getAttovenditas()) {
				a.setBollavendita(_bollavendita);
				session.merge(a);
			}
			id = _bollavendita.getIdBollavendita();
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(BollavenditaB.class, "saveBollavendita rollback ", null);
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.debug(BollavenditaB.class, "saveBollavendita effettuato per id: " + _bollavendita.getIdBollavendita(), GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return id;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Bollavendita> getAllBollavendita() {
		GoldmanagerLogger.debug(BollavenditaB.class, "Data op: " + GoldmanagerUtility.today() + " getAllBollavendita ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		ArrayList<Bollavendita> bolla = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Bollavendita.class);
			criteria.addOrder(Order.desc(GoldmanagerDefine.BOLLAVENDITA_NUMERO_FDA));
			bolla = (ArrayList<Bollavendita>) criteria.list();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(BollavenditaB.class, "Data op: " + GoldmanagerUtility.today() + " getAllBollavendita rollback ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(BollavenditaB.class, "Data op: " + GoldmanagerUtility.today() + " trovati bollavendita " + bolla.size(), GoldmanagerSession.getCurrentInstance().getUtente()
		        .getUsername());
		return bolla;
	}

	public Bollavendita getBollavendita(String _idBollavendita) {
		GoldmanagerLogger.debug(BollavenditaB.class, "Data op: " + GoldmanagerUtility.today() + " getBollavendita ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		Bollavendita bolla = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			bolla = (Bollavendita) session.get(Bollavendita.class, Integer.parseInt(_idBollavendita));
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(BollavenditaB.class, "Data op: " + GoldmanagerUtility.today() + " getBollavendita rollback ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(BollavenditaB.class, "Data op: " + GoldmanagerUtility.today() + " trovato bollavendita " + bolla, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return bolla;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Bollavendita> getAllBollavenditaDdt() {
		GoldmanagerLogger.debug(BollavenditaB.class, "Data op: " + GoldmanagerUtility.today() + " getAllBollavenditaDdt ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		ArrayList<Bollavendita> bolla = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Bollavendita.class);
			criteria.add(Restrictions.isNotNull(GoldmanagerDefine.BOLLAVENDITA_NUMERO_DDT));
			criteria.addOrder(Order.desc(GoldmanagerDefine.BOLLAVENDITA_NUMERO_DDT));
			bolla = (ArrayList<Bollavendita>) criteria.list();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(BollavenditaB.class, "Data op: " + GoldmanagerUtility.today() + " getAllBollavenditaDdt rollback ", GoldmanagerSession.getCurrentInstance().getUtente()
			        .getUsername());
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(BollavenditaB.class, "Data op: " + GoldmanagerUtility.today() + " trovati bollavendita " + bolla.size(), GoldmanagerSession.getCurrentInstance().getUtente()
		        .getUsername());
		return bolla;
	}
}
