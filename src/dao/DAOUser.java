package dao;

import entities.User;

public class DAOUser extends DAOFactory<User> {

	public DAOUser() {
		super(User.class);
	}
	
	@Override
	public User get(String email) {
		return em.find(User.class, email);
	}
}
