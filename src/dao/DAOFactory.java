package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAOFactory<T> {
	
	protected EntityManagerFactory emf;
	protected EntityManager em ;
	
	public DAOFactory(){
		emf = Persistence.createEntityManagerFactory("ShareLoc");;
		em = emf.createEntityManager();
	}
	
	public T get(String ID) {
		return null;
	}
	
	public void remove(T object) {
		em.getTransaction().begin();
		em.remove(object);
		em.flush();
		em.getTransaction().commit();
	}
	
	public void create(T object) {
		em.getTransaction().begin();
		em.persist(object);
		em.flush();
		em.getTransaction().commit();
	}
	
	public void set(T object) {
		em.getTransaction().begin();
		em.refresh(object);
		em.flush();
		em.getTransaction().commit();
	}
	
}
