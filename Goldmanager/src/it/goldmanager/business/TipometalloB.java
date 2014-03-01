package it.goldmanager.business;

import it.goldmanager.common.GoldmanagerDefine;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.GoldmanagerUtility;
import it.goldmanager.common.HibernateUtil;
import it.goldmanager.databean.Tipometallo;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class TipometalloB {

	@SuppressWarnings("unchecked")
	public List<Tipometallo> getTipometallo(String _idGruppo) {
		GoldmanagerLogger.debug(TipometalloB.class, "getTipometallo", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Transaction tx = null;
		Session session = null;
		ArrayList<Tipometallo> tipometalloList = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Tipometallo.class);
			criteria.add(Restrictions.eq(GoldmanagerDefine.TIPOMETALLO_ATTIVO, GoldmanagerDefine.ATTIVO));
			criteria.add(Restrictions.eq(GoldmanagerDefine.TIPOMETALLO_GRUPPO, _idGruppo));
			criteria.addOrder(Order.desc(GoldmanagerDefine.TIPOMETALLO_NOME));
			tipometalloList = (ArrayList<Tipometallo>) criteria.list();
			session.flush();
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(TipometalloB.class, "Data op: " + GoldmanagerUtility.today() + " runtime exception ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(TipometalloB.class, "Data op: " + GoldmanagerUtility.today() + " trovati tipometallo " + tipometalloList, GoldmanagerSession.getCurrentInstance().getUtente()
		        .getUsername());
		return tipometalloList;
	}

	public Tipometallo getTipometalloId(String _idTipometallo) {
		GoldmanagerLogger.debug(TipometalloB.class, "getTipometallo", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Transaction tx = null;
		Session session = null;
		Tipometallo tipometallo = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Tipometallo.class);
			criteria.add(Restrictions.eq(GoldmanagerDefine.TIPOMETALLO_ATTIVO, GoldmanagerDefine.ATTIVO));
			criteria.add(Restrictions.eq(GoldmanagerDefine.TIPOMETALLO_ID_TIPOMETALLO, Integer.parseInt(_idTipometallo)));
			tipometallo = (Tipometallo) criteria.uniqueResult();
			session.flush();
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(TipometalloB.class, "Data op: " + GoldmanagerUtility.today() + " runtime exception ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(TipometalloB.class, "Data op: " + GoldmanagerUtility.today() + " trovato tipometallo " + tipometallo, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return tipometallo;
	}
}
