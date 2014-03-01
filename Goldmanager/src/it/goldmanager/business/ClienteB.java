package it.goldmanager.business;

import it.goldmanager.common.GoldmanagerDefine;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.GoldmanagerUtility;
import it.goldmanager.common.HibernateUtil;
import it.goldmanager.databean.Cliente;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

public class ClienteB {

	public int saveOrUpCliente(Cliente _cliente) {
		GoldmanagerLogger.debug(ClienteB.class, "saveOrUpCliente init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		int id;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(_cliente);
			session.flush();
			id = _cliente.getIdCliente();
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(ClienteB.class, "saveOrUpCliente rollback ", null);
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(ClienteB.class, "saveOrUpCliente effettuato per id: " + _cliente.getIdCliente(), GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return id;
	}

	public Cliente getCliente(String _idcliente) {
		GoldmanagerLogger.debug(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " getCliente per idDoc" + _idcliente, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		Cliente cliente = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			cliente = (Cliente) session.get(Cliente.class, new Integer(_idcliente));
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " getCliente rollback ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " trovato cliente " + cliente, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return cliente;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Cliente> getAllCliente() {
		GoldmanagerLogger.debug(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " getAllCliente ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		ArrayList<Cliente> clienti = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Cliente.class);
			criteria.addOrder(Order.asc(GoldmanagerDefine.CLIENTE_COGNOME));
			clienti = (ArrayList<Cliente>) criteria.list();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " getAllCliente rollback ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " trovati cliente " + clienti.size(), GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return clienti;
	}

	public void delCliente(int _idcliente) {
		GoldmanagerLogger.debug(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " delCliente ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Session session = null;
		Transaction tx = null;
		String cognomedel;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Cliente cliente = (Cliente) session.get(Cliente.class, _idcliente);
			cognomedel = cliente.getCognome();
			session.delete(cliente);
			tx.commit();
		} catch (RuntimeException e) {
			GoldmanagerLogger.error(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " delCliente rollback ", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeCurrentSession();
		}
		GoldmanagerLogger.info(ClienteB.class, "Data op: " + GoldmanagerUtility.today() + " delCliente cognome: " + cognomedel, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
	}
}
