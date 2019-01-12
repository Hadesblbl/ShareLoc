package entities;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Service")
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int ID;

	@JoinColumn(name = "Colocation", referencedColumnName = "name")
	private Colocation coloc;

	@Column(name = "Title")
	private String title;

	@Column(name = "Description")
	private String description;

	@Column(name = "Cost")
	private int cost;

	@Column(name = "isValidated")
	private boolean isValidated = false;

	@ElementCollection
	@CollectionTable(name = "service_votes", joinColumns = @JoinColumn(name = "ID"))
	private Map<User, Boolean> votes = new HashMap<User, Boolean>();

	public Service() {

	}

	public Service(Colocation coloc, String title, String description, int cost) {
		setColoc(coloc);
		setTitle(title);
		setDescription(description);
		setCost(cost);
	}

	public int getID() {
		return ID;
	}

	public Colocation getColoc() {
		return coloc;
	}

	public void setColoc(Colocation coloc) {
		this.coloc = coloc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public boolean isValidated() {
		return isValidated;
	}

	public void setValidated(boolean isValidated) {
		this.isValidated = isValidated;
	}

	public void putVote(User user, boolean response) {
		votes.put(user, response);
	}

	public Map<User, Boolean> getVotes() {
		return votes;
	}

}
