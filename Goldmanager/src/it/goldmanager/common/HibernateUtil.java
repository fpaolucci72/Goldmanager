package it.goldmanager.common;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	static {
		try {
			Configuration configuration = new Configuration().configure();//.addClass(it.goldmanager.databean.Utente.class).addClass(it.goldmanager.databean.Ruolo.class);
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			
//			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().configure().buildServiceRegistry();
//			MetadataSources metadataSources = new MetadataSources(serviceRegistry);
//			SessionFactory factory = metadataSources.buildMetadata().buildSessionFactory();

			
			
		} catch (HibernateException he) {
			GoldmanagerLogger.error(HibernateUtil.class, "Error creating Session: " + he, null);
			throw new ExceptionInInitializerError(he);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void closeCurrentSession() {
		getSessionFactory().getCurrentSession().close();
	}
}
