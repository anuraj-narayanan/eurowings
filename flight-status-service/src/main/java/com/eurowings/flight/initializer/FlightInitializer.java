package com.eurowings.flight.initializer;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.eurowings.flight.model.Flight;
import com.eurowings.flight.repository.FlightRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Component
@Profile("!test")
@Slf4j
public class FlightInitializer implements CommandLineRunner {

  private static final Logger log = LoggerFactory.getLogger(FlightInitializer.class);

  @Autowired
  private FlightRepository flightRepository;  

  @Override
  public void run(String... args) throws Exception {
    initialDataSetup();
  }

  private List<Flight> getFlightData() {

    return Arrays.asList(new Flight(null, "001", "EW", "MUC", "AMS", "10-Aug-2021"),
        new Flight(null, "002", "EW", "AMS", "MUC", "10-Aug-2021"));

  }  

  private void initialDataSetup() {    
    flightRepository.deleteAll().thenMany(Flux.fromIterable(getFlightData()))
        .flatMap(flightRepository::save).thenMany(flightRepository.findAll()).subscribe(flight -> {
          log.info("Flight inserted from commandLineRunner {}", flight);
        });
  }

}
