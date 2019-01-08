package controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
		if(from == null || service == null || coloc == null || service.isValidated())
			return false;
		ArrayList<User> to = new ArrayList<User>();
		for (User u : coloc.getColocataires())
			if (u != from)
				to.add(u);
		AchievedService as = new AchievedService(from, to, date, image, service);
		daoAchievedService.set(as);
		from.getServices().add(as);
		daoUser.set(from);
		return true;
	}

	public static boolean validateServiceDeclaration(int achievedServiceId, String userId, boolean statement) {
		AchievedService as = daoAchievedService.get(achievedServiceId);
		User u = daoUser.get(userId);
		if(!as.getTo().contains(u)||as.getVotes().get(u) != null)
			return false;
		as.getVotes().put(u, statement);
		daoAchievedService.set(as);
		return true;
		
	}
}
