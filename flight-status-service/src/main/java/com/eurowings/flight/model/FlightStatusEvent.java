package com.eurowings.flight.model;

import java.util.Date;

import com.eurowings.flight.vo.FlightStatus;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FlightStatusEvent {
	
	private FlightStatus status;
	private Date date;
	public FlightStatusEvent() {
		super();
	}
	public FlightStatusEvent(FlightStatus status, Date date) {
		super();
		this.status = status;
		this.date = date;
	}
	public FlightStatus getStatus() {
		return status;
	}
	public void setStatus(FlightStatus status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}	

}
