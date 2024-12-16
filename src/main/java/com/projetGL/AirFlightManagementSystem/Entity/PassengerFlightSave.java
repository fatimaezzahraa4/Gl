package com.projetGL.AirFlightManagementSystem.Entity;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints ={
		@UniqueConstraint(columnNames={"userId" ,"flight"})
		})
public class PassengerFlightSave  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId ",referencedColumnName = "user_account_id")
	private PassengerProfile  userId;
	

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight", referencedColumnName = "flightId")
    private Flight flight;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public PassengerProfile getUserId() {
		return userId;
	}


	public void setUserId(PassengerProfile userId) {
		this.userId = userId;
	}


	public Flight getFlight() {
		return flight;
	}


	public void setFlight(Flight flight) {
		this.flight = flight;
	}


	public PassengerFlightSave(Integer id, PassengerProfile userId, Flight flight) {
		super();
		this.id = id;
		this.userId = userId;
		this.flight = flight;
	}


	public PassengerFlightSave() {
		super();
	}


	@Override
	public String toString() {
		return "FlightSave [id=" + id + ", userId=" + userId + ", flight=" + flight + "]";
	}


	
	

}
