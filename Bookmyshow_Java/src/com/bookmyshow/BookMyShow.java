package com.bookmyshow;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BookMyShow {
	static List<Movie> movieList = new ArrayList<>();
	static List<Booking> bookingList = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		//preloadAdminOperation();
		Scanner instance = new Scanner(System.in);
		startSystem(instance);

	}

	public static void startBookingOptions(Scanner instance) {
		startSystem(instance);
	}

	public static void startSystem(Scanner instance) {
		loadWelcomeMessage();
		loadSystemOptions(instance);
		//getUserOptions(instance);
	}

	public static void preloadAdminOperation() {
		HashMap<Integer, LinkedHashMap<String, String>> seats = Utility.generateSeatNumbers(26, 10);
		Movie movie = new Movie(Integer.parseInt("1234"), 26, 10, seats);
		movieList.add(movie);
	}

	public static void loadWelcomeMessage() {
		System.out.println("\nWelcome to Movie booking system: ");
	}

	public static void loadSystemOptions(Scanner instance) {
		
		try {
			System.out.println("\n Choose your option? 1. Admin 2. Buyer");
			String userOption = instance.nextLine();
			if (Integer.parseInt(userOption) == 1) {
				callAdmin(instance);
			}
			
			else if (Integer.parseInt(userOption) == 2) {
				callBuyer(instance);
			}
			 else {
				 	System.err.println("\nEnter valid option...");
					startSystem(instance);

				}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			loadSystemOptions(instance);
			e.printStackTrace();
		}
		
	}
	
	public static void callBuyer(Scanner instance) {
		
		try {
			System.out.println(
"\nHow can we help today? (Type 1: Availability, 2: Book a ticket, 3: Cancel, 4: Main Menu )");
			System.out.println("Please Enter Your Option ... ");
			String userOption = instance.nextLine();
			if (Integer.parseInt(userOption) == 1) {
				if(movieList.size()==0) {
					System.out.println("No Movies available now. Ask admin to setup a movie first");
					callBuyer(instance);
					
				}
				else{
					Utility.printSeats(movieList.get(0).getSeats());
					callBuyer(instance);
				}
			}
			else if (Integer.parseInt(userOption) == 2) {
				bookTickets(instance);
			}
			else if (Integer.parseInt(userOption) == 3) {
				cancelBooking(instance);
				callBuyer(instance);
			}
			else if (Integer.parseInt(userOption) == 4) {
				startSystem(instance);
			}
			 else {
				 System.err.println("\nEnter valid option...");
				 callBuyer(instance);
				}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			callBuyer(instance);
			e.printStackTrace();
		}
	}
	
	public static void callAdmin(Scanner instance) {

		try {
			
			System.out.println("Enter Username: <Type admin>");
			String username = instance.nextLine();

			if (username.equals("admin")) {
				System.out.println("Welcome Admin !!");
				callAdminOption(instance);

				/*
				 * create new show max seat 10 max row 26 total 260 seats need to have showID
				 * movie1 - 260 - row 10 seats [ [A1,A2,A3,A4,A5,A6,A7,A8,A9,A10] [B1,
				 * B2,B3,B4,B5,B6,B7,B8,B9,B10] ] HashMap<key, value> = A1, bookingno
				 */
				
			} else {
				System.err.println("Invalid username, try gain!");
				startSystem(instance);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("\nEnter valid option...");
			startSystem(instance);
			//e.printStackTrace();
		}
	
	}

	public static void callAdminOption(Scanner instance) {
		
		try {
		System.out.println("\n Choose your admin option? 1. Setup 2. View 3. Main Menu");
		String userOption = instance.nextLine();
		if (Integer.parseInt(userOption) == 1) {
			
		int seatPerRow = 10; // hardcoded as per constraints
		int noOfRows = 26; // hardcoded as per constraints
		System.out.println("You'd like to set up a Movie? <Enter yes to proceed>");
		String optionCreateMovie = instance.nextLine();

		if (optionCreateMovie.equals("yes")) {
			System.out.println("Enter the show number? ");
			String showNumber = instance.nextLine();

			HashMap<Integer, LinkedHashMap<String, String>> seats = Utility.generateSeatNumbers(noOfRows,
					seatPerRow);

			System.out.println(Arrays.toString(seats.entrySet().toArray()));
			Utility.printSeats(seats);
			Movie movie = new Movie(Integer.parseInt(showNumber), 26, 10, seats);
			movieList.add(movie);
			System.out.println("\nSuccessfully created a new Movie with id, " + showNumber);
			System.out.println("\nNumber of rows allocated, " + noOfRows);
			System.out.println("\nNumber of seats per row allocated, " + seatPerRow);
			callAdminOption(instance);
		}
		else {
			 System.err.println("\nEnter valid option...");
			 callAdminOption(instance);
			}
		
		}
		else if (Integer.parseInt(userOption) == 2) {
			if(movieList.size()==0) {
				System.out.println("Set up a movie first");
				callAdmin(instance);
			}
			else{
				Utility.printSeats(movieList.get(0).getSeats());
				callAdminOption(instance);
			}
			}
		else if (Integer.parseInt(userOption) == 3) {
			startSystem(instance);
			}
		
	 else {
		System.err.println("\\nEnter valid option...");
		callAdminOption(instance);

	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	System.out.println("\nEnter valid option...");
	callAdminOption(instance);
	//e.printStackTrace();
}
	}
	public static void markSeatsAsBooked(Movie movie, int rowId, String bookedSeats) {
		HashMap<Integer, LinkedHashMap<String, String>> currentShowSeats = movie.getSeats();
		HashMap<Integer, LinkedHashMap<String, String>> _seats = Utility.bookSeats(currentShowSeats, rowId,
				bookedSeats);
		movieList.get(0).setSeats(_seats);
		Utility.printSeats(_seats);
	}
	
	public static void bookTickets(Scanner instance) {
		

		try {
			// book
			/***
			 * need to show book no. phone and comma separated seats
			 */
			if(movieList.size()==0) {
				System.out.println("No Movies available now. Ask admin to setup a movie first");
				callBuyer(instance);
			}
			else {
			System.out.println("\nPlease enter your mobile no ... ");
			String mobileNo = instance.nextLine();
			
			boolean isAlreadyBooked = Utility.isAlreadyBooked(bookingList, mobileNo);

			if(isAlreadyBooked) {
				System.err.println("\nAlready tickets are booked in the given mobile no " + mobileNo + " please use different mobile no");
				callBuyer(instance);
			}
			else {

				Utility.printSeats(movieList.get(0).getSeats());
				System.out.println("\nPlease enter the row you want to book a ticket... Ex: 1 or 2 or 3");
				String chosenRow = instance.nextLine();
				System.out.println(
						"\nPlease enter the seats based on availbilty as comma separated value ex: A1,A2,A3 etc...");

				String chosenSeats = instance.nextLine();

				// need to check if the seats are available else throw error

				boolean isSeatsAvailable = Utility.isSeatAvailable(movieList.get(0).getSeats(), Integer.parseInt(chosenRow),
						chosenSeats);

				if (isSeatsAvailable) {

					// now the seats are available - let us book them and provide a booking Id

					String bookingId = Utility.generateBookingId();
					System.out.println(bookingId);
					HashMap<String, String> bookedSeats = new HashMap<>();
					bookedSeats.put(chosenRow, chosenSeats);
					LocalDateTime lt = LocalDateTime.now();
					Booking newBooking = new Booking(bookingId, mobileNo, bookedSeats, lt);
					bookingList.add(newBooking);

					// mark the seat as booked in the movie
					markSeatsAsBooked(movieList.get(0), Integer.parseInt(chosenRow), chosenSeats);
					System.out.println("\nYour tickets are booked successfully!");
					System.out.println("************************************");
					System.out.println("\n Booked Id : " + bookingId);
					System.out.println("************************************");
					callBuyer(instance);

				} else {
					System.err.println("Chosen seats are already booked, please try again");
					callBuyer(instance);
				}

			}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			callBuyer(instance);
			e.printStackTrace();
		}

	
	}
	
	public static void cancelBooking(Scanner instance) {
		

		// cancel
		try {
			System.out.println("Please enter your mobile no used for booking");
			String mobileNo = instance.nextLine();
			System.out.println("Please enter your booking Id");
			String bookingId = instance.nextLine();

			// find the time if greater than 2 min then dont allow cancellation
			LocalDateTime timeNow = LocalDateTime.now();
			LocalDateTime bookingTime = Utility.getBookingTime(bookingList, bookingId);
			if (bookingTime == null) {
				System.out.println("There's no booking available in the given details");
				startBookingOptions(instance);
			}
			else {
			//System.out.println(timeNow);
			//System.out.println(bookingTime);
			long millis = Duration.between(bookingTime, timeNow).toMillis();
			long timeGap = TimeUnit.MILLISECONDS.toMinutes(millis);
			System.out.println("time " + timeGap);
			// startBookingOptions(instance);
			// reset the booking to empty
			if (timeGap <= 2) {
				HashMap<Integer, LinkedHashMap<String, String>> cancelBooking = Utility.cancelBooking(bookingList,
						movieList.get(0).getSeats(), bookingId, mobileNo);
				movieList.get(0).setSeats(cancelBooking);
				System.err.println("************************************");
				System.out.println("BOOKING cancelled!\n");
				//System.err.println("************************************");
				Utility.printSeats(cancelBooking);
				startBookingOptions(instance);
			} else {
				System.err.println("***********************************************************");
				System.err.println("Cannot perform cancellation due to expired time");
				System.err.println("***********************************************************");
				startBookingOptions(instance);
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("\n Please enter valid row or Seat number");
			callBuyer(instance);
		}

	
	}
}

