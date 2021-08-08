package com.eurowings.flight.repository;

import com.eurowings.flight.model.Flight;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightRepository extends ReactiveCrudRepository<Flight, Long>{

	@Query("SELECT flightnumber from Flight f where f.origin like :#{#origin==null || #origin.isEmpty() ? '%' : #origin}"
			+ " and f.destination like :#{#destination==null || #destination.isEmpty() ? '%' : #destination}"
			+ " and f.flightdate like :#{#flightdate==null || #flightdate.isEmpty() ? '%' : #flightdate}")
	Mono<String> findFlight(String origin, String destination, String flightdate);

	Mono<Flight> findByFlightdateAndOriginAndDestination(String flightdate, String origin, String destination);
	
	Mono<Flight> findByFlightnumberAndFlightdate(String flightnumber, String flightdate);

}
