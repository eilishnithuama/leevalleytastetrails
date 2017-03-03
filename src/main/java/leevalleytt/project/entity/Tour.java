package leevalleytt.project.entity;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TOURS", schema = "PUBLIC", catalog = "DEFAULT")
public class Tour {
	
	private int tour_id;
	private String name;
	private String image;
	private Date date;
	private int amountOfPeopleBooked;
	final int MAX_PEOPLE = 15;
	private int completed;
	private String description;
	
	public Tour(){
		
	}
	
	public Tour(int tour_id, String name, String image, Date date, int amountOfPeopleBooked, int completed,
			String description) {
		super();
		this.tour_id = tour_id;
		this.name = name;
		this.image = image;
		this.date = date;
		this.amountOfPeopleBooked = amountOfPeopleBooked;
		this.completed = completed;
		this.description = description;
	}

	@Id
    @Column(name = "TOUR_ID")
    @GeneratedValue
	public int getId() {
		return tour_id;
	}

	public void setId(int id) {
		this.tour_id = id;
	}

    @Basic
    @Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Basic
    @Column(name = "DATE")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
    @Basic
    @Column(name = "AMOUNT_BOOKED")
	public int getAmountOfPeopleBooked() {
		return amountOfPeopleBooked;
	}

	public void setAmountOfPeopleBooked(int amountOfPeopleBooked) {
		this.amountOfPeopleBooked = amountOfPeopleBooked;
	}

    @Basic
    @Column(name = "COMPLETED")
	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

    @Basic
    @Column(name = "IMAGE")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

    @Basic
    @Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Tour [tour_id=" + tour_id + ", name=" + name + ", image=" + image + ", date=" + date
				+ ", amountOfPeopleBooked=" + amountOfPeopleBooked + ", MAX_PEOPLE=" + MAX_PEOPLE + ", completed="
				+ completed + ", description=" + description + "]";
	}
}
