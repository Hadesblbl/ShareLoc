package dao;

import entities.Message;

public class DAOMessage extends DAOFactory<Message>{

	public DAOMessage() {
		super(Message.class);
	}
	
	@Override
	public Message get(int ID) {
		return em.find(Message.class, ID);
	}
}
