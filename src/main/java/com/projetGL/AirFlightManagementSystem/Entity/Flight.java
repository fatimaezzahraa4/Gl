package com.projetGL.AirFlightManagementSystem.Entity;

import java.sql.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name= "Flights")
public class Flight {
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flightId;

  
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "airline_id", referencedColumnName = "Id")
    private Airline airline;

    @Transient
    private Boolean isActive;

    @Transient
    private Boolean isPopular;
    
    


    @Column(nullable = false)
    private Date flightDate;
    

    @ManyToOne
    @JoinColumn(name = "origin_airport_id", nullable = false)
    private Airport origin;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private Airport destination;

    @ManyToOne
    @JoinColumn(name = "airplane_id", nullable = false)
    private Airplane airplaneName;
    

    @Transient
    private Boolean isSaved;
    
    
    @Length(max = 5000)
    private String flightDescription;

    private String flightType; // e.g., "Commercial", "Private", "Cargo"
    @Column
    private String ticketPrice;
    @Column

    private String duration; // e.g., "2h 30m"

    
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date departureDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date arrivalDate;

    private String flightTitle;

    public Flight() {
    }

	
	



	public Date getFlightDate() {
		return flightDate;
	}




	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}




	public Airport getOrigin() {
		return origin;
	}




	public void setOrigin(Airport origin) {
		this.origin = origin;
	}




	public Airport getDestination() {
		return destination;
	}




	public void setDestination(Airport destination) {
		this.destination = destination;
	}




	public Airplane getAirplane() {
		return airplaneName;
	}




	public void setAirplane(Airplane airplane) {
		this.airplaneName = airplane;
	}




	public Integer getFlightId() {
		return flightId;
	}
	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}



	public Boolean getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(Boolean isSaved) {
		this.isSaved = isSaved;
	}

	 @OneToMany(mappedBy = "flight")
	    private List<PassengerReservation> reservations;

	
	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsPopular() {
		return isPopular;
	}

	public void setIsPopular(Boolean isPopular) {
		this.isPopular = isPopular;
	}

	
	
	

	public String getFlightDescription() {
		return flightDescription;
	}

	public void setFlightDescription(String flightDescription) {
		this.flightDescription = flightDescription;
	}

	public String getFlightType() {
		return flightType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public String getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getFlightTitle() {
		return flightTitle;
	}

	public void setFlightTitle(String flightTitle) {
		this.flightTitle = flightTitle;
	}

	public List<PassengerReservation> getReservations() {
		return reservations;
	}




	public void setReservations(List<PassengerReservation> reservations) {
		this.reservations = reservations;
	}




	public Flight(Integer flightId, Airline airline, Boolean isActive, Boolean isPopular, 
			 Date flightDate, Airport origin, Airport destination, Airplane airplaneName,
			Boolean isSaved, @Length(max = 5000) String flightDescription, String flightType, String ticketPrice,
			String duration, Date departureDate, Date arrivalDate, String flightTitle,
			List<PassengerReservation> reservations) {
		super();
		this.flightId = flightId;
		this.airline = airline;
		this.isActive = isActive;
		this.isPopular = isPopular;
		this.flightDate = flightDate;
		this.origin = origin;
		this.destination = destination;
		this.airplaneName = airplaneName;
		this.isSaved = isSaved;
		this.flightDescription = flightDescription;
		this.flightType = flightType;
		this.ticketPrice = ticketPrice;
		this.duration = duration;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.flightTitle = flightTitle;
		this.reservations = reservations;
	}




	

	



	




	
	
}

    