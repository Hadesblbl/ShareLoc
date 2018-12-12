package database;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.User;

public class ColocEntityManager {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShareLoc");;
	private static EntityManager em = emf.createEntityManager();

	public static void createUser(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}

	public static User getUser(String mail) {
		em.getTransaction().begin();
		return em.find(User.class, mail);
	}

	public static void setUser(User user) {
		em.getTransaction().begin();
		em.refresh(user);
		em.getTransaction().commit();
	}

	public static void deleteUser(User user) {
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
	}

	public static ArrayList<User> getAllUsers() {
		return null;
	}
}
