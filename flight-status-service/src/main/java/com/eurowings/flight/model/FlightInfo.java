package com.eurowings.flight.model;

import com.eurowings.flight.vo.FlightStatus;

public class FlightInfo {
	
	private Flight flight;
	private FlightStatus status;
	
	public FlightInfo(Flight flight, FlightStatus status) {		
		this.flight = flight;		
		this.status=status;	
	}

	public FlightInfo() {
		super();		
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public FlightStatus getStatus() {
		return status;
	}

	public void setStatus(FlightStatus status) {
		this.status = status;
	} 

}
