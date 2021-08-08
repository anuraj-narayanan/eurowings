package com.eurowings.mock.resource;

public class FlightStatus {
	
	private String flightnumber;
	private String departuredate;
	private String arrivaldate;
	private String status;
	public FlightStatus(String flightnumber, String departuredate,String arrivaldate, String status) {
		super();
		this.flightnumber = flightnumber;
		this.departuredate = departuredate;
		this.arrivaldate = arrivaldate;
		this.status = status;
	}
	public FlightStatus() {
		super();		
	}
	public String getFlightnumber() {
		return flightnumber;
	}
	public void setFlightnumber(String flightnumber) {
		this.flightnumber = flightnumber;
	}	
	public String getDeparturedate() {
		return departuredate;
	}
	public void setDeparturedate(String departuredate) {
		this.departuredate = departuredate;
	}
	public String getArrivaldate() {
		return arrivaldate;
	}
	public void setArrivaldate(String arrivaldate) {
		this.arrivaldate = arrivaldate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
