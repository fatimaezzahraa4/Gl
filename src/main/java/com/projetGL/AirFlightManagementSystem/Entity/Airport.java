package com.projetGL.AirFlightManagementSystem.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity

@Table(name = "Airport")

public class Airport {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private String IATACode;

	    @Column(nullable = false)
	    private String name;

	    @Column(nullable = false)
	    private String city;

	    @Column(nullable = false)
	    private String country;

	    @Column(nullable = false)
	    private int airportCapacity;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		

		public String getIATACode() {
			return IATACode;
		}

		public void setIATACode(String iATACode) {
			IATACode = iATACode;
		}
		
		

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public int getAirportCapacity() {
			return airportCapacity;
		}

		public void setAirportCapacity(int airportCapacity) {
			this.airportCapacity = airportCapacity;
		}

		
		
		
		public Airport(String string) {
			super();
		}

		
		public Airport() {
			super();
		}

		public Airport(Long id, String IATACode, String name, String city, String country, int airportCapacity) {
			super();
			this.id = id;
			this.IATACode = IATACode;
			this.name = name;
			this.city = city;
			this.country = country;
			this.airportCapacity = airportCapacity;
		}

		@Override
		public String toString() {
			return "Airport [id=" + id + ", IATA_Code=" + IATACode + ", name=" + name + ", city=" + city + ", country="
					+ country + ", passengerCapacity=" + airportCapacity + "]";
		}
	    
	    
	    
	    
}
