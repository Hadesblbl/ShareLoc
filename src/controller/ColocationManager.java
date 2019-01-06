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
		daoColoc.set(coloc);
		return true;

	}

	public static boolean addUserToColoc(String colocID, String userID) {
		Colocation coloc = daoColoc.get(colocID);
		User user = daoUser.get(userID);
		boolean status = coloc.addColocataire(user);
		daoColoc.set(coloc);
		return status;
	}

	public static boolean removeUserFromColoc(String colocID, String userID) {
		Colocation coloc = daoColoc.get(colocID);
		User user = daoUser.get(userID);
		boolean status = coloc.removeColocataire(user);
		daoColoc.set(coloc);
		// TODO remove coloc from user too
		return status;
	}

	public static boolean changeColocAdmin(String colocID, String currentUserID, String userID) {
		Colocation coloc = daoColoc.get(colocID);
		User user = daoUser.get(userID);
		User currentUser = daoUser.get(userID);
		if (coloc.getGestionnaire() == currentUser && user != null) {
			coloc.setGestionnaire(user);
			return true;
		}
		return false;
	}
}
