package com.projetGL.AirFlightManagementSystem.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "airlines")
public class Airline {

	
	


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "airlineName")
	    private String airlineName;

	    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL)
	    private List<Flight> flights; // Liste des vols associés à cette compagnie aérienne

	    // Getters et setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getairlineName() {
	        return airlineName;
	    }

	    public void setName(String airlineName) {
	        this.airlineName = airlineName;
	    }

	    public List<Flight> getFlights() {
	        return flights;
	    }

	    public void setFlights(List<Flight> flights) {
	        this.flights = flights;
	    }
	}


