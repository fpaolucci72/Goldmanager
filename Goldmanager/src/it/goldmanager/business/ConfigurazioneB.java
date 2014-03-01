package it.goldmanager.business;

import it.goldmanager.common.GoldmanagerDefine;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.GoldmanagerUtility;
import it.goldmanager.common.HibernateUtil;
import it.goldmanager.databean.Configurazione;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class ConfigurazioneB {

	@SuppressWarnings("unchecked")
	public List<Configurazione> getConfigurazione() {
		GoldmanagerLogger.debug(ConfigurazioneB.class, "getConfigurazione", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Transaction tx = null;
		Session session = null;
		ArrayList<Configurazione> configList = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Configurazione.class);
			criteria.add(Restrictions.eq(GoldmanagerDefine.CONFIGURAZIONE_ATTIVO, GoldmanagerDefine.ATTIVO));
			configList = (ArrayList<Configurazione>) criteria.list();
			session.flush();
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(ConfigurazioneB.class, "Data op: " + GoldmanagerUtility.today() + " runtime exception ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(ConfigurazioneB.class, "Data op: " + GoldmanagerUtility.today() + " trovati documenti " + configList, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return configList;
	}

	public void updateConfigurazione(Configurazione _configurazione) {
		GoldmanagerLogger.debug(ConfigurazioneB.class, "updateConfigurazione init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.merge(_configurazione);
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(ConfigurazioneB.class, "updateConfigurazione rollback ", null);			
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(ConfigurazioneB.class, "updateConfigurazione effettuato per id: " + _configurazione.getIdConfigurazione(), GoldmanagerSession.getCurrentInstance().getUtente()
		        .getUsername());
	}

	public Configurazione getConfigurazione(String _idconfigurazione) {
		GoldmanagerLogger.debug(ConfigurazioneB.class, "getConfigurazione", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Transaction tx = null;
		Session session = null;
		Configurazione config = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Configurazione.class);
			criteria.add(Restrictions.eq(GoldmanagerDefine.CONFIGURAZIONE_ID_CONFIGURAZIONE, _idconfigurazione));
			criteria.add(Restrictions.eq(GoldmanagerDefine.CONFIGURAZIONE_ATTIVO, GoldmanagerDefine.ATTIVO));
			config = (Configurazione) criteria.uniqueResult();
			session.flush();
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(ConfigurazioneB.class, "Data op: " + GoldmanagerUtility.today() + " runtime exception ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger
		        .info(ConfigurazioneB.class, "Data op: " + GoldmanagerUtility.today() + " trovata configurazione " + config, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return config;
	}
}
