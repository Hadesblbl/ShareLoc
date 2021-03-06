package dao;

import entities.Service;

public class DAOService extends DAOFactory<Service> {

	public DAOService() {
		super(Service.class);
	}
	
	@Override
	public Service get(String ID) {
		return em.find(Service.class, ID);
	}
}
