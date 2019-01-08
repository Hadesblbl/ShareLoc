package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

public abstract class DAOFactory<T> {
	
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShareLock");
	protected static EntityManager em ;
	private Class<T> classeEntite;
	
	public DAOFactory(Class<T> classeEntite){
		this.classeEntite = classeEntite;
		getEntityManager();
	}
	
	protected EntityManager getEntityManager() {
		if (em == null)
			em = emf.createEntityManager();
		return em;
	}
	
	public T get(String ID) {
		return getEntityManager().find(classeEntite, ID);
	}
	
	public T get(int ID) {
		return getEntityManager().find(classeEntite, ID);
	}
	
	public void remove(T object) {
		em.getTransaction().begin();
		em.remove(object);
		em.flush();
		em.getTransaction().commit();
	}
	
	public void set(T object) {
		em.getTransaction().begin();
		em.refresh(object);
		em.flush();
		em.getTransaction().commit();
	}
	
	public void persist(T object) {
		em.getTransaction().begin();
		em.persist(object);
		em.flush();
		em.getTransaction().commit();
	}
	
	public ArrayList<T> findAll() {
		@SuppressWarnings("unchecked")
		CriteriaQuery<T> cq = (CriteriaQuery<T>) getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(classeEntite));
		Vector<T> v = (Vector<T>) getEntityManager().createQuery(cq).getResultList();
		if (v!=null)
			return new ArrayList<T>(v);
		return null;
	}

	/**
	 * Methode renvoyant les n objets de ce type compris dans l'intervalle pass�
	 * en parametre (utile pour la pagination des resultats).
	 * 
	 * @param etendue
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findRange(int[] etendue) {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(classeEntite));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(etendue[1] - etendue[0]);
		q.setFirstResult(etendue[0]);
		return q.getResultList();
	}

	/**
	 * Methode renvoyant le nombre d'objet de ce type.
	 * 
	 * @return
	 */
	public int count() {
		CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(classeEntite);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}
	
}
