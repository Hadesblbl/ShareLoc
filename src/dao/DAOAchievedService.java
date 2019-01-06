package dao;

import entities.AchievedService;

public class DAOAchievedService extends DAOFactory<AchievedService> {

	public DAOAchievedService() {
		super(AchievedService.class);
	}
	
	@Override
	public AchievedService get(String ID) {
		return em.find(AchievedService.class, ID);
	}
}
