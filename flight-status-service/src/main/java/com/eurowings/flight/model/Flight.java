package com.eurowings.flight.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@Table("flight")
public class Flight {
	
	@Id	
	private Long id;	
	private String flightnumber;	
	private String carriercode;
	private String origin;
	private String destination;
	private String flightdate;	
	
	public Flight() {
		
	}
	
	public Flight(Long id, String flightnumber, String carriercode, String origin, String destination, String flightdate) {
		this.id = id;
		this.flightnumber=flightnumber;
		this.carriercode=carriercode;
		this.origin=origin;
		this.destination=destination;
		this.flightdate=flightdate;		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFlightnumber() {
		return flightnumber;
	}

	public void setFlightnumber(String flightnumber) {
		this.flightnumber = flightnumber;
	}

	public String getCarriercode() {
		return carriercode;
	}

	public void setCarriercode(String carriercode) {
		this.carriercode = carriercode;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getFlightdate() {
		return flightdate;
	}

	public void setFlightdate(String flightdate) {
		this.flightdate = flightdate;
	}
	
}
