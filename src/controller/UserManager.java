package controller;

import java.util.List;

import dao.DAOColocation;
import dao.DAOUser;
import entities.Colocation;
import entities.User;

public class UserManager {

	private static DAOColocation daoColoc = new DAOColocation();
	private static DAOUser daoUser = new DAOUser();
	
	public UserManager() {

	}

	public static User getUser(String mail) {
		if(mail==null) {
			return null;
		}
		return daoUser.get(mail);
	}
	
	public static boolean changeProfile(User current, User newProfil) {
		if ((newProfil.getMail() != current.getMail() && daoUser.get(newProfil.getMail()) == null)
				&& (!current.equals(newProfil))) {
			daoUser.set(newProfil);
			return true;
		}
		return false;
	}
	
	public static boolean sendInvitation(String senderID, String invitedID, String colocID) {
		Colocation coloc = daoColoc.get(colocID);
		User sender = daoUser.get(senderID);
		User invited = daoUser.get(invitedID);
		if((coloc != null && sender != null && invited != null) && coloc.getGestionnaire() == sender){
			coloc.addColocataire(invited);
			invited.getColocs().add(coloc);
			daoColoc.set(coloc);
			daoUser.set(invited);
			return true;
		}
		return false;
	}
	
	public static List<User> getUsers() {
		List<User> lv = daoUser.findAll();
		return lv;
	}
	
	public static User login(String login, String password) {	
		if(login==null) {
			return null;
		}
		User u=daoUser.get(login);
		if(u!=null && u.getPassword().equals(password))
			return u;
		return null;
	}
	
	public static boolean createUser(String login, String password, String firstname, String lastname) throws NullPointerException{
		if(login==null) {
			throw new NullPointerException("Login must not be null");
		}
		User u = daoUser.get(login);
		if (u == null) {
			daoUser.persist(new User(login, firstname, lastname,password));
			return true;
		}
		return false;
	}
}
