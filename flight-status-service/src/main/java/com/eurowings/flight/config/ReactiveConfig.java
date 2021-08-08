package com.eurowings.flight.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.eurowings.flight.handler.RouteHandlers;

@Configuration
public class ReactiveConfig {
	
	private static final Logger log = LoggerFactory.getLogger(ReactiveConfig.class);
	
	@Bean
	RouterFunction<ServerResponse> routerFunction(RouteHandlers routeHandlers){
		log.info("Service call....");
		return RouterFunctions.route(				
				RequestPredicates.GET("/flight/all"),routeHandlers::getAll)
				.andRoute(RequestPredicates.GET("/flight/{id}"),routeHandlers::getId)
				.andRoute(RequestPredicates.GET("/flight/{id}/events"),routeHandlers::getFlightEvents)				
				.andRoute(RequestPredicates.GET("/flight/{flightnumber}/{flightdate}"),routeHandlers::getFlightInfo)
				.andRoute(RequestPredicates.GET("/flight/{flightnumber}/{flightdate}/events"),routeHandlers::getFlightStatusEvents);	
	}

}
