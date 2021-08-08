package com.eurowings.flight.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FlightEvent {
	
	private Flight flight;
	private Date date;
	
	public FlightEvent(Flight flight, Date date) {
		this.flight=flight;
		this.date=date;
	}
	public FlightEvent(String origin, Date date) {
		flight = new Flight();
		flight.setOrigin(origin);
		this.date=date;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
