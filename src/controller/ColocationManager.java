package controller;

import java.util.ArrayList;

import dao.DAOColocation;
import entities.Colocation;
import entities.User;

public class ColocationManager {

	private static DAOColocation daoColoc = new DAOColocation();
	
	public ColocationManager() {
		
	}
	
	public static void createColocation(Colocation coloc, ArrayList<User>users) {
		daoColoc.create(coloc);
		
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
