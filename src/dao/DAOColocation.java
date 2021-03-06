package dao;

import entities.Colocation;

public class DAOColocation extends DAOFactory<Colocation> {

	public DAOColocation() {
		super(Colocation.class);
	}
	
	@Override
	public Colocation get(String ID) {
		return em.find(Colocation.class, ID);
	}
}
