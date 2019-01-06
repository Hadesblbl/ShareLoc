package controller;

import dao.DAOColocation;
import entities.Colocation;
import entities.User;

public class ColocationManager {

	private static DAOColocation daoColoc = new DAOColocation();
	
	private ColocationManager() {
		
	}
	
	public static boolean createColocation(Colocation coloc, User user) {
		if(daoColoc.get(coloc.getName()) != null || user == null)
			return false;
		coloc.setGestionnaire(user);
		daoColoc.set(coloc);
		return true;
		
	}
	
	public static boolean addUserToColoc(Colocation coloc, User user) {
		boolean status = coloc.addColocataire(user);
		daoColoc.set(coloc);
		return status;
	}
	
	public static boolean removeUserFromColoc(Colocation coloc, User user) {
		boolean status = coloc.removeColocataire(user);
		daoColoc.set(coloc);
		//TODO remove coloc from user too
		return status;
	}
	
	public static boolean changeColocAdmin(Colocation coloc,User currentUser, User user) {
		if(coloc.getGestionnaire() == currentUser && user != null) {
			coloc.setGestionnaire(user);
			return true;
		}
		return false;
	}
}
