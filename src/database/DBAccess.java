package database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.User;

public class DBAccess {

	public static List<User> getUsers() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShareLoc");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT x FROM User x");
		return query.getResultList();
	}

	public static void addUser(User user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShareLoc");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
}
