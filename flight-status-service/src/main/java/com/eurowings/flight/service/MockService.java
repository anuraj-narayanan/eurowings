package com.eurowings.flight.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.eurowings.flight.vo.FlightStatus;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import reactor.core.publisher.Mono;

@Service
public class MockService {
	
	private static final Logger log = LoggerFactory.getLogger(MockService.class);	
	
	WebClient webClient = WebClient.create("http://mock:8081");
	
	CircuitBreaker circuitBreaker = CircuitBreaker.of("flightStatusBreak", CircuitBreakerConfig.ofDefaults());
	
	public Mono<FlightStatus> flightStatusCheck(String flightnumber, String flightdate) {
		log.info("flightStatusCheck service called");		
		return webClient.get().uri("/flightstatus/{flightnumber}/{flightdate}", flightnumber, flightdate).retrieve()
			.onStatus(HttpStatus::is5xxServerError, clientResponse->
			 Mono.error(new Exception()))
			.bodyToMono(FlightStatus.class)
			.transform(CircuitBreakerOperator.of(circuitBreaker))
			.onErrorResume(ex->flightStatusFallback(flightnumber,flightdate));		
	}
    
    
    public Mono<FlightStatus> flightStatusFallback(String flightnumber,String flightdate){
    	log.info("flightStatusFallback called");
    	return Mono.just(new FlightStatus(flightnumber,flightdate,null,"Flight status is not available now, please try again later"));
    }

}
