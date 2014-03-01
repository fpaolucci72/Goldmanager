package it.goldmanager.business;

import it.goldmanager.common.GoldmanagerDefine;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.GoldmanagerUtility;
import it.goldmanager.common.HibernateUtil;
import it.goldmanager.databean.Documento;
import it.goldmanager.view.ClienteV;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class DocumentoB {

	@SuppressWarnings("unchecked")
	public List<Documento> getDocumento() {
		GoldmanagerLogger.debug(ClienteV.class, "caricaDocumento", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Transaction tx = null;
		Session session = null;
		ArrayList<Documento> documentoList = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Documento.class);
			criteria.add(Restrictions.eq(GoldmanagerDefine.DOCUMENTO_ATTIVO, GoldmanagerDefine.ATTIVO));
			criteria.addOrder(Order.asc(GoldmanagerDefine.DOCUMENTO_ID_DOCUMENTO));
			documentoList = (ArrayList<Documento>) criteria.list();
			session.flush();
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(ClienteV.class, "Data op: " + GoldmanagerUtility.today() + " runtime exception ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(ClienteV.class, "Data op: " + GoldmanagerUtility.today() + " trovati documenti " + documentoList, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return documentoList;
	}

	public Documento getUtente(String _idDoc) {
		GoldmanagerLogger.debug(ClienteV.class, "Data op: " + GoldmanagerUtility.today() + " getDocumento per idDoc" + _idDoc, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		Documento doc = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			doc = (Documento) session.get(Documento.class, new Integer(_idDoc));
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(ClienteV.class, "Data op: " + GoldmanagerUtility.today() + " getDocumento rollback ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(ClienteV.class, "Data op: " + GoldmanagerUtility.today() + " trovato documento " + doc, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return doc;
	}
}
