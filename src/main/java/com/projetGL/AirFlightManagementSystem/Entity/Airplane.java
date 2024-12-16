package com.projetGL.AirFlightManagementSystem.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "Airplane")

public class Airplane {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
	private String Airline;
	
	
    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private int seatCapacity;

    @Column(nullable = false)
    private int yearOfManufacture;

    @Column(nullable = false)
    private String model;

	public Airplane(Integer id, String type, int seatCapacity, int yearOfManufacture, String model , String Airline) {
		super();
		this.id = id;
		this.type = type;
		this.seatCapacity = seatCapacity;
		this.yearOfManufacture = yearOfManufacture;
		this.model = model;
		this.Airline=Airline;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSeatCapacity() {
		return seatCapacity;
	}

	public void setSeatCapacity(int seatCapacity) {
		this.seatCapacity = seatCapacity;
	}

	public int getYearOfManufacture() {
		return yearOfManufacture;
	}

	public void setYearOfManufacture(int yearOfManufacture) {
		this.yearOfManufacture = yearOfManufacture;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	public String getAirline() {
		return Airline;
	}
	
	public void setAirline(String Airline) {
		this.Airline = Airline;
	}

	
	
	
	public Airplane() {
		super();
	}

	@Override
	public String toString() {
		return "Airplane [id=" + id + ", type=" + type + ", seatCapacity=" + seatCapacity + ", yearOfManufacture="
				+ yearOfManufacture + ", model=" + model + "]";
	}

    
    
}
