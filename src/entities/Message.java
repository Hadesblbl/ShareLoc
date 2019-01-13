package entities;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "coloc", referencedColumnName = "name")
	private Colocation coloc;
	
	@ManyToOne
	@JoinColumn(name = "user", referencedColumnName = "mail")
	private User user;
	
	@Column(name = "Date")
	private String date;
	
	@Column(name = "Message")
	private String contain;
	
	public Message() {
		
	}
	
	public Message(User user, String contain, Colocation coloc) {
		setUser(user);
		setContain(contain);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date(System.currentTimeMillis());
		String date = sdf.format(d);
		setDate(date);
		setColoc(coloc);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setContain(String contain) {
		this.contain = contain;
		
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public User getUser() {
		return user;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getContain() {
		return contain;
	}

	public Colocation getColoc() {
		return coloc;
	}

	public void setColoc(Colocation coloc) {
		this.coloc = coloc;
	}
}
