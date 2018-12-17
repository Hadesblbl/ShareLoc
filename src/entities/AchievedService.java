package entities;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name="AchievedServices")
public class AchievedService {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int ID;
	
	@JoinColumn(name = "From")
	@ManyToOne
	private User from;
	
	@OneToMany(fetch = EAGER)
	@JoinColumn
	private ArrayList<User> to = new ArrayList<User>();
	
	@Column(name = "Date")
	private String date;
	
	private String image;
	
	@Column(name = "valid")
	private boolean valid = false;
	
	@JoinColumn(name = "Service")
	private Service service;
	
	
	public AchievedService() {
		
	}
	
	public AchievedService(User from, ArrayList<User> to, String date, String image, Service service) {
		setFrom(from);
		setTo(to);
		setImage(image);
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
   
}
