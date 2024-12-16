package com.projetGL.AirFlightManagementSystem.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projetGL.AirFlightManagementSystem.Entity.Flight;
import com.projetGL.AirFlightManagementSystem.Entity.IAdminFlight;

public interface FlightPostActivityRepository extends JpaRepository<Flight, Integer> {

	
	@Query(value = "SELECT " + 
	        "COUNT(r.user_id) AS totalReservation, " + 
	        "f.flight_id AS flightId, " +            // Utilisation de l'ID du vol
	        "a.name AS airline " +                   // Utilisation de `a.name` pour le nom de la compagnie aérienne
	        "FROM Flights f " +
	        "INNER JOIN airlines a ON f.airline_id = a.id " +  // L'ID de la compagnie aérienne est lié via `airline_id`
	        "LEFT JOIN reservations r ON r.flight_id = f.flight_id " +  // Les réservations sont liées via `flight_id`
	        "GROUP BY f.flight_id, a.name",  // Regroupement par `flight_id` et `a.name`
	    nativeQuery = true)

	
	List<IAdminFlight> getAdminFlights(@Param("admin") int admin);

	
	
	
	@Query(
	       value = "SELECT * FROM Flight f " +
            "INNER JOIN airport o ON f.origin_airport_id = o.id " +
            "INNER JOIN airport d ON f.destination_airport_id = d.id " +
            "WHERE f.airlineIATA LIKE %:airlineIATA% " +
            "AND f.flightNumber = :flightNumber " +
            "AND (o.city LIKE %:location% " +
            "OR o.country LIKE %:location% " +
            "OR o.state LIKE %:location%) " +
            "AND (d.city LIKE %:location% " +
            "OR d.country LIKE %:location% " +
            "OR d.state LIKE %:location%)", nativeQuery = true)
List<Flight> searchFlightsWithoutDate(
	                           	   @Param("flight") String flight,
		                           @Param("airlineName") String airlineName,
                                   @Param("flightNumber") int flightNumber,
                                   @Param("location") String location);
	
	
	

	@Query(value = "SELECT * FROM flight f " +
            "INNER JOIN airport o ON f.origin_id = o.id " +
            "INNER JOIN airport d ON f.destination_id = d.id " +
            "WHERE f.airlineIATA LIKE %:airlineIATA% " +
            "AND f.flightNumber = :flightNumber " +
            "AND (o.city LIKE %:location% " +
            "OR o.country LIKE %:location% " +
            "OR o.state LIKE %:location%) " +
            "AND (d.city LIKE %:location% " +
            "OR d.country LIKE %:location% " +
            "OR d.state LIKE %:location%) " +
            "AND f.flightDate >= :date", nativeQuery = true)
List<Flight> searchFlights(
		                @Param("flight") String flight,
		                @Param("airlineName") String airlineName,
                        @Param("flightNumber") int flightNumber,
                        @Param("location") String location,
                        @Param("date") LocalDate date);

	
	
	
	
	
	@Query("SELECT f FROM Flight f " +
		       "WHERE f.origin = :origin " +
		       "AND f.destination = :destination " +
		       "AND f.airline = :airline " +
		       "AND f.flightType = :flightType " +
		       "AND f.ticketPrice = :ticketPrice")
		List<Flight> searchWithoutDate(
		    @Param("origin") String origin,
		    @Param("destination") String destination,
		    @Param("airline") String airline,
		    @Param("flightType") String flightType,
		    @Param("ticketPrice") String ticketPrice
		);


	

	
	
	@Query(value = "SELECT * FROM flight f " +
            "INNER JOIN airport o ON f.origin_airport_id = o.id " +
            "INNER JOIN airport d ON f.destination_airport_id = d.id " +
            "WHERE f.airlineIATA LIKE %:airlineIATA% " +
            "AND f.flightNumber = :flightNumber " +
            "AND (o.city LIKE %:location% " +
            "OR o.country LIKE %:location% " +
            "OR o.state LIKE %:location%) " +
            "AND (d.city LIKE %:location% " +
            "OR d.country LIKE %:location% " +
            "OR d.state LIKE %:location%) " +
            "AND f.flightDate >= :date", nativeQuery = true)
	List<Flight> search(@Param("flight") String flight,
                                    @Param("origine") String origin,
                                    @Param("destination") String destination,
                                    @Param("airline") String airline,
                                    @Param("status") String status,
                                    @Param("searchDate") LocalDate searchDate);




}
