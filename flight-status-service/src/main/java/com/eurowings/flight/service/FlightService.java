package com.eurowings.flight.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eurowings.flight.model.Flight;
import com.eurowings.flight.model.FlightInfo;
import com.eurowings.flight.repository.FlightRepository;
import com.eurowings.flight.vo.FlightStatus;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class FlightService {
	
	private static final Logger log = LoggerFactory.getLogger(FlightService.class);

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private MockService mockService;
	
	public Mono<FlightInfo> getFlightInfo(String flightnumber, String flightdate) {
		
		log.info("Reached inside getFlightInfo of Service");		
		Mono<FlightStatus> flightstatus = mockService.flightStatusCheck(flightnumber, flightdate)
				.subscribeOn(Schedulers.boundedElastic());
		log.info("flightstatus received");
		Mono<Flight> flight = flightRepository.findByFlightnumberAndFlightdate(flightnumber, flightdate);
		log.info("flightinfo received");
		// Merge the monos to a new Mono
		log.info("Merging infos");
		return Mono.zip(flightstatus, flight).map(entry -> new FlightInfo(entry.getT2(), entry.getT1()));

	}	

}
