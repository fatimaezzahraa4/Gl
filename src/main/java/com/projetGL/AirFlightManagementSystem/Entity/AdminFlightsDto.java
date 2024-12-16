package com.projetGL.AirFlightManagementSystem.Entity;

import java.sql.Date;

public class AdminFlightsDto {
	
	
	
	
	private Long totalReservation;
	
	private Integer flightPostId ;
	private String airline ;
	private String flightTitlle;
	
	
	
	public AdminFlightsDto(Long totalReservation, Integer flightPostId, String airline, String flightTitlle) {
		super();
		this.totalReservation = totalReservation;
		this.flightPostId = flightPostId;
		this.airline = airline;
		this.flightTitlle = flightTitlle;
	}
	public AdminFlightsDto() {
		super();
	}
	public AdminFlightsDto(Long totalReservations, int flight_post_id, String flght_title, String airline2,
			Airport origin, Airport destination, Date getflightDate) {
		// TODO Auto-generated constructor stub
	}



	public Long getTotalReservation() {
		return totalReservation;
	}
	public void setTotalReservation(Long totalReservation) {
		this.totalReservation = totalReservation;
	}
	public Integer getFlightPostId() {
		return flightPostId;
	}
	public void setFlightPostId(Integer flightPosrId) {
		this.flightPostId = flightPosrId;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getFlightTitlle() {
		return flightTitlle;
	}
	public void setFlightTitlle(String flightTitlle) {
		this.flightTitlle = flightTitlle;
	}
	@Override
	public String toString() {
		return "AdminFlightsDto [totalReservation=" + totalReservation + ", flightPosrId=" + flightPostId + ", airline="
				+ airline + ", FlightTitlle=" + flightTitlle + "]";
	}
	
	
	
	
	
	
	

}
