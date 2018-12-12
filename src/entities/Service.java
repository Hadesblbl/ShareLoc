package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Services")
public class Service {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int ID;
	
	@Column(name = "Colocation")
	private Colocation coloc;
	
	@Column(name = "Title")
	private String title;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "Cost")
	private int cost;
	
	
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
   
}
