package com.eurowings.mock.resource;

import java.time.Duration;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class RouteHandlers {
	
	private static final Logger log = LoggerFactory.getLogger(RouteHandlers.class);

	Random rand = new Random();	
	
	public Mono<ServerResponse> getFlightStatus(ServerRequest serverRequest) {
		log.info("Flight status check");
		String flightnumber = serverRequest.pathVariable("flightnumber");	
		String flightdate = serverRequest.pathVariable("flightdate");	
		log.info("flightnumber {}",flightnumber);
		log.info("flightdate {}",flightdate);	
		return ServerResponse.ok().body(Mono.just(new FlightStatus(flightnumber,"10-Aug-2021 10:10:10","10-Aug-2021 12:10:10","Delayed")).delayElement(Duration.ofMillis(getRandomDelay())),
				FlightStatus.class);
	}

	private int getRandomDelay() {
		int lowerBound = 500;
		int upperBound = 800;
		return rand.nextInt(upperBound - lowerBound) + lowerBound;
	}

}
