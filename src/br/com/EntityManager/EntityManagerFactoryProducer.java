package br.com.EntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryProducer {
	
	private static final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("persistence");
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return emFactory;
	}
	
	public static EntityManager createEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}
	
	public static void closeDBConnection() {
		if (emFactory.isOpen())
			emFactory.close();
	}
}
