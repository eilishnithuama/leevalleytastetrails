package leevalleytt.project.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CustomerDetails {
	
	private int id;
	private String name;
	private String extraInfo;
	private String phoneNo;
	private Tour tour;
	private String stripeToken;
	private int amount;
	private String email;
	private int noOfTickets;
	
	public CustomerDetails(){
		
	}
	

	public CustomerDetails(String name, String email, int noOfTickets, String extraInfo, String phoneNo, Tour tour, String stripeToken, int amount) {
		super();
		this.name = name;
		this.extraInfo = extraInfo;
		this.email = email;
		this.noOfTickets = noOfTickets;
		this.phoneNo = phoneNo;
		this.tour = tour;
		this.stripeToken = stripeToken;
		this.amount = amount;
	}
	
	@Id
    @Column(name = "ID")
    @GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
    @Column(name = "EXTRA_INFO")
	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	@Basic
    @Column(name = "PHONE_NO")
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@ManyToOne
	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public int getNoOfTickets() {
		return noOfTickets;
	}

	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

	@Basic
    @Column(name = "STRIPE_TOKEN")
	public String getStripeToken() {
		return stripeToken;
	}

	public void setStripeToken(String stripeToken) {
		this.stripeToken = stripeToken;
	}

	@Basic
    @Column(name = "AMOUNT")
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Basic
    @Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CustomerDetails [name=" + name + ", extraInfo=" + extraInfo + ", phoneNo=" + phoneNo + ", tour=" + tour
				+ ", stripeToken=" + stripeToken + ", amount=" + amount + "]";
	}

}
