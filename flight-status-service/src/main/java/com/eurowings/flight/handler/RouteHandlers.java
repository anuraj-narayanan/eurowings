package com.eurowings.flight.handler;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.eurowings.flight.model.Flight;
import com.eurowings.flight.model.FlightEvent;
import com.eurowings.flight.model.FlightStatusEvent;
import com.eurowings.flight.repository.FlightRepository;
import com.eurowings.flight.service.FlightService;
import com.eurowings.flight.service.MockService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Component
public class RouteHandlers {

	private static final Logger log = LoggerFactory.getLogger(RouteHandlers.class);

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private FlightService flightService;
	
	@Autowired
	private MockService mockService;

	public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
		log.info("Inside getAll method");
		return ServerResponse.ok().body(flightRepository.findAll(), Flight.class);
	}

	public Mono<ServerResponse> getId(ServerRequest serverRequest) {
		log.info("Inside getId method");
		Long id = Long.valueOf(serverRequest.pathVariable("id"));
		return ServerResponse.ok().body(flightRepository.findById(id), Flight.class);
	}

	public Mono<ServerResponse> getFlightInfo(ServerRequest serverRequest) {
		log.info("Inside getFlightInfo");
		String flightnumber = serverRequest.pathVariable("flightnumber");
		String flightdate = serverRequest.pathVariable("flightdate");
		log.info("flightnumber {}",flightnumber);
		log.info("flightdate {}",flightdate);
		return ServerResponse.ok().body(flightService.getFlightInfo(flightnumber, flightdate), Flight.class);

	}
	
	public Mono<ServerResponse> getFlightStatusEvents(ServerRequest serverRequest) {
		log.info("Inside getFlightStatusEvents :: Stream of flight status");
		String flightnumber = serverRequest.pathVariable("flightnumber");
		String flightdate = serverRequest.pathVariable("flightdate");
		
		return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
			.body(mockService.flightStatusCheck(flightnumber, flightdate).flatMapMany(status->{
				
					Flux<Long> interval = Flux.interval(Duration.ofMillis(500));
					Flux<FlightStatusEvent> flightEventFlux = Flux
							.fromStream(Stream.generate(() -> new FlightStatusEvent(status, new Date())));
					return Flux.zip(interval, flightEventFlux).map(Tuple2::getT2);
				
				}), FlightStatusEvent.class);		
	}

	public Mono<ServerResponse> getFlightEvents(ServerRequest serverRequest) {
		Long id = Long.valueOf(serverRequest.pathVariable("id"));
		return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
				.body(flightRepository.findById(id).flatMapMany(flight -> {
					Flux<Long> interval = Flux.interval(Duration.ofMillis(500));
					Flux<FlightEvent> flightEventFlux = Flux
							.fromStream(Stream.generate(() -> new FlightEvent(flight, new Date())));
					return Flux.zip(interval, flightEventFlux).map(Tuple2::getT2);

				}), FlightEvent.class);
	}

}
