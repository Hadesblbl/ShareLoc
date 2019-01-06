package controller;

import java.util.List;

import dao.DAOUser;
import entities.Colocation;
import entities.User;

public class UserManager {

	public static DAOUser daoUser = new DAOUser();

	public UserManager() {

	}

	public static User getUser(String mail) {
		return daoUser.get(mail);
	}
	
	public static boolean inscription(User user) {
		User u = daoUser.get(user.getMail());
		if (u != null)
			return false;
		daoUser.set(user);
		return true;
	}

	public static boolean login(User user, String password) {
		if(daoUser.get(user.getMail()) == user && user.getPassword().equals(password))
			return true;
		return false;
	}
	
	public static boolean changeProfile(User current, User newProfil) {
		if ((newProfil.getMail() != current.getMail() && daoUser.get(newProfil.getMail()) == null)
				&& (!current.equals(newProfil))) {
			daoUser.set(newProfil);
			return true;
		}
		return false;
	}

	public static boolean createInvitation(Colocation coloc, User From, User For) {
		if (coloc.getGestionnaire() == From && !coloc.getColocataires().contains(For)) {
			// TODO comment faire l'invitation?
			return true;
		}
		return false;
	}

	public static boolean acceptInvitation(Colocation coloc, User user) {
		return ColocationManager.addUserToColoc(coloc, user);
	}

	public static boolean rejectInvitation() {
		return false;// TODO
	}
	
	public static List<User> getUsers() {
		List<User> lv = daoUser.findAll();
		return lv;
	}
	
	public static User login(String login, String password) {		
		User u=daoUser.get(login);
		if(u!=null && u.getPassword().equals(password))
			return u;
		return null;
	}
	
	public static boolean createUser(String login, String password, String firstname, String lastname) {
		User u = daoUser.get(login);
		if (u == null) {
			daoUser.set(new User(login, password, firstname, lastname));
			return true;
		}
		return false;
	}
}
