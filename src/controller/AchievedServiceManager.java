package controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map.Entry;

import dao.DAOAchievedService;
import dao.DAOService;
import dao.DAOUser;
import entities.AchievedService;
import entities.Colocation;
import entities.Service;
import entities.User;

public class AchievedServiceManager {

	private static DAOService daoService = new DAOService();
	private static DAOUser daoUser = new DAOUser();
	private static DAOAchievedService daoAchievedService = new DAOAchievedService();

	public static boolean declareAchievedService(int serviceId, String userId, String image, String colocId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date(System.currentTimeMillis());
		String date = sdf.format(d);
		User from = daoUser.get(userId);
		Service service = daoService.get(serviceId);
		Colocation coloc = service.getColoc();
		if(from == null || service == null || coloc == null || !service.isValidated())
			return false;
		ArrayList<User> to = new ArrayList<User>();
		for (User u : coloc.getColocataires())
			if (u != from)
				to.add(u);
		AchievedService as = new AchievedService(from, to, date, image, service);
		daoAchievedService.persist(as);
		from.getServices().add(as);
		daoUser.persist(from);
		return true;
	}

	public static boolean validateServiceDeclaration(int achievedServiceId, String userId) {
		AchievedService as = daoAchievedService.get(achievedServiceId);
		User u = daoUser.get(userId);
		if(as == null || as.getService().getColoc().getGestionnaire() != u)
			return false;
		as.setValid(true);
		daoAchievedService.set(as);
		return true;
	}
	
	public static boolean voteAchievedService(int achievedServiceId, String userId, boolean statement) {
		AchievedService as = daoAchievedService.get(achievedServiceId);
		User u = daoUser.get(userId);
		if(!as.getTo_().contains(u)||as.getVotes().get(u) != null)
			return false;
		as.getVotes().put(u, statement);
		daoAchievedService.set(as);
		return true;
		
	}

	public static String getAchievedServiceInfo(int id) {
		AchievedService achservice = daoAchievedService.get(id);
		String response = "";
		if (achservice != null) {
			Service service = achservice.getService();
			response += "ID: " + service.getID() + "\tTitle: " + service.getTitle() + "\tColocation: "
					+ service.getColoc().getName() + "\tDescription: " + service.getDescription() + "\tCost:"+service.getCost()+"\tDate"+achservice.getDate()+"\tImage:"+achservice.getImage();
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

	public static Object getAllAchievedServices() {
		String response = "";
		for (AchievedService achievedservice : daoAchievedService.findAll()) {
			Service service = daoService.get(achievedservice.getService().getID());
			response += "ID: " + service.getID() + "\tTitle: " + service.getTitle() + "\tColocation: "
					+ service.getColoc().getName()+"\r\n";
		}
		return response;
	}
}
