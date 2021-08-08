package com.eurowings.mock.resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class MockResource {
	
	@Bean
	RouterFunction<ServerResponse> routerFunction(RouteHandlers routeHandlers){
		
		return RouterFunctions.route(RequestPredicates.GET("/flightstatus/{flightnumber}/{flightdate}"),routeHandlers::getFlightStatus);		
	}

}
