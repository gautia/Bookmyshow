package com.bookmyshow;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Movie {

	private int showNumber; 
	private int rows;
	private int seatsPerRow;

	private HashMap<Integer, LinkedHashMap<String, String>> seats;

	//1 , [A1,A2,A3,A4]
	//2, [b1,b2,b3,b4]
	//seats length = no of rows 
	// 


	public Movie(int showNumber, int rows, int seatsPerRow, HashMap<Integer, LinkedHashMap<String, String>> seats) {
		super();
		this.showNumber = showNumber;
		this.rows = rows;
		this.seatsPerRow = seatsPerRow;
		this.seats = seats;
	}


	public int getShowNumber() {
		return showNumber;
	}


	public void setShowNumber(int showNumber) {
		this.showNumber = showNumber;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getSeatsPerRow() {
		return seatsPerRow;
	}

	public void setSeatsPerRow(int seatsPerRow) {
		this.seatsPerRow = seatsPerRow;
	}

	public HashMap<Integer, LinkedHashMap<String, String>> getSeats() {
		return seats;
	}

	public void setSeats(HashMap<Integer, LinkedHashMap<String, String>> seats) {
		this.seats = seats;
	}






}
