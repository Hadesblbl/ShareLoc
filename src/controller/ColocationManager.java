package controller;

import dao.DAOColocation;
import dao.DAOUser;
import entities.Colocation;
import entities.User;

public class ColocationManager {

	private static DAOColocation daoColoc = new DAOColocation();
	private static DAOUser daoUser = new DAOUser();

	private ColocationManager() {

	}

	public static boolean createColocation(String colocID, String userID) {
		Colocation coloc = daoColoc.get(colocID);
		User user = daoUser.get(userID);
		if (coloc != null || user == null)
			return false;
		coloc = new Colocation(colocID, user);
		coloc.setGestionnaire(user);
		daoColoc.persist(coloc);
		user.getColocs().add(coloc);
		daoUser.set(user);
		return true;

	}

	public static boolean addUserToColoc(String colocID, String userID, String adminID) {
		Colocation coloc = daoColoc.get(colocID);
		User user = daoUser.get(userID);
		User admin = daoUser.get(adminID);
		boolean status = false;
		if (admin == coloc.getGestionnaire() && !coloc.getColocataires().contains(user)) {
			status = coloc.addColocataire(user);
			user.getColocs().add(coloc);
			daoColoc.set(coloc);
			daoUser.set(user);
		}
		return status;
	}

	public static boolean removeUserFromColoc(String colocID, String userID, String currentUserID) {
		Colocation coloc = daoColoc.get(colocID);
		User user = daoUser.get(userID);
		User currentUser = daoUser.get(currentUserID);
		boolean status = false;
		if (currentUser == coloc.getGestionnaire()) {
			status = coloc.removeColocataire(user);
			user.getColocs().remove(coloc);
			daoColoc.set(coloc);
			daoUser.set(user);
		}
		return status;
	}

	public static boolean changeColocAdmin(String colocID, String currentUserID, String userID) {
		Colocation coloc = daoColoc.get(colocID);
		User user = daoUser.get(userID);
		User currentUser = daoUser.get(currentUserID);
		if (coloc != null && currentUser != null && coloc.getGestionnaire() == currentUser && user != null
				&& coloc.getColocataires().contains(user)) {
			coloc.setGestionnaire(user);
			daoColoc.set(coloc);
			return true;
		}
		return false;
	}
}
