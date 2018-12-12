package entities;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AchievedServices")
public class AchievedService {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int ID;
	
	@Column(name = "From")
	private User from;
	
	@Column(name = "To")
	private ArrayList<User> to = new ArrayList<User>();
	
	@Column(name = "Date")
	private String date;
	
	@Column(name = "valid")
	private boolean valid = false;
	
	public AchievedService() {
		
	}
	
	public AchievedService(User from, ArrayList<User> to, String date) {
		setFrom(from);
		setTo(to);
		setDate(date);
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public ArrayList<User> getTo() {
		return to;
	}

	public void setTo(ArrayList<User> to) {
		this.to = to;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
   
}
