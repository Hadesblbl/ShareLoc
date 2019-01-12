package controller;

import dao.DAOColocation;
import dao.DAOService;
import dao.DAOUser;
import entities.Colocation;
import entities.Service;
import entities.User;

public class ServiceManager {

	private static DAOService daoService = new DAOService();
	private static DAOColocation daoColoc = new DAOColocation();
	private static DAOUser daoUser = new DAOUser();
	
	private ServiceManager() {

	}

	public static boolean createService(String userID, String colocID, String title, String description, int cost) {
		Colocation coloc = daoColoc.get(colocID);
		User user = daoUser.get(userID);
		if(coloc != null && user != null) {
			Service service = new Service(coloc, title, description, cost);
			daoService.persist(service);
			voteService(user.getMail(),service.getID(), true);
			return true;
		}
		return false;
	}
	
	public static boolean voteService(String userID, int serviceID, boolean response) {
		User user = daoUser.get(userID);
		Service service = daoService.get(serviceID);
		if(user != null && service != null && service.isValidated()) {
			service.putVote(user, response);
			daoService.set(service);
			return true;
		}
		return false;
	}
	
	public static boolean validateService(String userID, int serviceID) {
		User user = daoUser.get(userID);
		Service service = daoService.get(serviceID);
		if(user != null && service != null && service.getColoc().getGestionnaire() == user) {
			service.setValidated(true);
			daoService.set(service);
			return true;
		}
		return false;
	}

}
