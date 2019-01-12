package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AchievedServices")
public class AchievedService {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int ID;

	@ManyToOne
	@JoinColumn(name = "from_", referencedColumnName = "mail")
	private User from_;

	@OneToMany
	@JoinColumn(name = "to", referencedColumnName = "mail")
	private List<User> to = new ArrayList<User>();

	@Column(name = "Date")
	private String date;

	@Column(name = "Image")
	private String image;

	@Column(name = "Valid")
	private boolean valid = false;

	@ManyToOne
	@JoinColumn(name = "Service", referencedColumnName = "ID")
	private Service service;

	 @ElementCollection
	 @CollectionTable(
	        name="achieved_service_votes",
	        joinColumns=@JoinColumn(name="ID")
	)
	private Map<User, Boolean> votes = new HashMap<User, Boolean>();

	public AchievedService() {

	}

	public AchievedService(User from, ArrayList<User> to, String date, String image, Service service) {
		setFrom_(from);
		setTo(to);
		setImage(image);
		setDate(date);
	}

	public User getFrom_() {
		return from_;
	}

	public void setFrom_(User from) {
		this.from_ = from;
	}

	public List<User> getTo() {
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

	public Map<User, Boolean> getVotes() {
		return votes;
	}

	public void setVotes(Map<User, Boolean> votes) {
		this.votes = votes;
	}

}
