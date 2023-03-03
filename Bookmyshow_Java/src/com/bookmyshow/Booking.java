package com.bookmyshow;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Booking {

	private String bookingId;
	private String mobileNo; 
	private HashMap<String, String> bookedSeats;
	private LocalDateTime time;
	
	public Booking(String bookingId, String mobileNo, HashMap<String, String> bookedSeats, LocalDateTime time) {
		super();
		this.bookingId = bookingId;
		this.mobileNo = mobileNo;
		this.bookedSeats = bookedSeats;
		this.time = time;
	}
	
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public HashMap<String, String> getBookedSeats() {
		return bookedSeats;
	}
	public void setBookedSeats(HashMap<String, String> bookedSeats) {
		this.bookedSeats = bookedSeats;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
}
