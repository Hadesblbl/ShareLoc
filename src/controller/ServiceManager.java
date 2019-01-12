package controller;

import java.util.Map.Entry;

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
		if (coloc != null && user != null) {
			Service service = new Service(coloc, title, description, cost);
			daoService.persist(service);
			voteService(user.getMail(), service.getID(), true);
			return true;
		}
		return false;
	}

	public static boolean voteService(String userID, int serviceID, boolean response) {
		User user = daoUser.get(userID);
		Service service = daoService.get(serviceID);
		if (user != null && service != null && service.isValidated()) {
			service.putVote(user, response);
			daoService.set(service);
			return true;
		}
		return false;
	}

	public static boolean validateService(String userID, int serviceID) {
		User user = daoUser.get(userID);
		Service service = daoService.get(serviceID);
		if (user != null && service != null && service.getColoc().getGestionnaire() == user) {
			service.setValidated(true);
			daoService.set(service);
			return true;
		}
		return false;
	}

	public static String getServiceInfo(int id) {
		Service service = daoService.get(id);
		String response = "";
		if (service != null) {
			response += "ID: " + service.getID() + "\tTitle: " + service.getTitle() + "\tColocation: "
					+ service.getColoc().getName() + "\tDescription: " + service.getDescription() + "\tCost:"+service.getCost();
			if(service.isValidated())
				response += "Validated: True";
			else {
				response += "Validated: False\r\n";
				for(Entry<User, Boolean> es : service.getVotes().entrySet())
					response += "\r\n\t Mail: "+ es.getKey().getMail()+" Vote: "+ es.getValue();	
			}
		}
		return response;
	}
	
	public static String getAllServices() {
		String response="";
		for(Service service : daoService.findAll())
			response += "ID: " + service.getID() + "\tTitle: " + service.getTitle() + "\tColocation: "
					+ service.getColoc().getName()+"\r\n";
		return response;
	}
	

}
