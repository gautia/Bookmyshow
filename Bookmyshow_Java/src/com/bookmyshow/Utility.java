package com.bookmyshow;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class Utility {

	public static HashMap<Integer, LinkedHashMap<String, String>> generateSeatNumbers(int row, int seatsPerRow) {

		HashMap<Integer, LinkedHashMap<String, String>> seats;
		seats = new HashMap<>();
		try {
			for (int i = 0; i < row; i++) { // row 26
				// create new hash map of string, string like A1 -> empty - add to main hash
				int charStart = 65 + i;
				char c = (char) charStart;
				LinkedHashMap<String, String> _dynamicRow = new LinkedHashMap<>();

				for (int j = 1; j <= seatsPerRow; j++) {
					String rowId = c + String.valueOf(j);
					_dynamicRow.put(rowId, "EMPTY");
				}

				seats.put(i + 1, _dynamicRow);
			}
			System.out.println("Total no of rows created :" + seats.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seats;
	}

	public static void getSeatAvailbility(HashMap<Integer, LinkedHashMap<String, String>> seats) {
		try {
			int totalEmptySeats = 0;
			Set<Integer> keys = seats.keySet();
			for (Integer key : keys) {
				LinkedHashMap<String, String> currentRow = seats.get(key);
				Set<String> rowKeys = currentRow.keySet();
				for (String r : rowKeys) {
					if (currentRow.get(r).equals("EMPTY")) {
						totalEmptySeats++;
					}
				}

			}
			System.out.println("Availale seats in this show " + totalEmptySeats);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean isSeatAvailable(HashMap<Integer, LinkedHashMap<String, String>> seats, int chosenRow, String chosenSeats) {
		try {
			LinkedHashMap<String, String> row = seats.get(chosenRow);
			String[] selectedSeats = chosenSeats.split(",");
			for (String s : selectedSeats) {
				if (row.get(s) != null && !row.get(s).equals("EMPTY")) {
					System.out.println("Seat " + s  + " is already booked");
					return false;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public static HashMap<Integer, LinkedHashMap<String, String>> 
	cancelBooking(List<Booking> bookingList,

			HashMap<Integer, LinkedHashMap<String, String>> seats, 

			String bookingId, String mobileNo) {

		try {
			for(Booking booking: bookingList) {
				if(booking.getBookingId().equals(bookingId) && booking.getMobileNo().equals(mobileNo)) {

					HashMap<String, String> bookedSeats = booking.getBookedSeats();
					for(Entry<String, String> entry: bookedSeats.entrySet()) {
						String rowId = entry.getKey();
						System.out.println("rowId " + rowId);

						LinkedHashMap<String, String> row = seats.get(Integer.parseInt(rowId));
						String[] _seats = entry.getValue().split(",");
						for(String s: _seats) {
							if (row.get(s) != null && row.get(s).equals("Booked")) {
								row.put(s, "EMPTY");
							}
						}
						seats.put(Integer.parseInt(rowId), row);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return seats;
	}
	public static LocalDateTime getBookingTime(List<Booking> bookingList, String bookingId) {
		try {
			//System.out.println(bookingId);
			for(Booking b: bookingList) {
				if(b.getBookingId().equals(bookingId)) {
					return b.getTime();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isAlreadyBooked(List<Booking> bookingList, String mobileNo) {
		try {
			System.out.println(mobileNo);
			for(Booking b: bookingList) {
				if(b.getMobileNo().equals(mobileNo)) {
					return true;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static HashMap<Integer, LinkedHashMap<String, String>> bookSeats(HashMap<Integer, LinkedHashMap<String, String>> seats, int chosenRow, String chosenSeats) {
		try {
			LinkedHashMap<String, String> row = seats.get(chosenRow);
			String[] selectedSeats = chosenSeats.split(",");
			System.out.println();
			for (String s : selectedSeats) {
				if (row.get(s) != null && row.get(s).equals("EMPTY")) {
					System.out.println("Booked!");
					row.put(s, "Booked");
				}
			}
			seats.put(chosenRow, row);
			//printSeats(seats);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seats;
	}

	public static void printSeats(HashMap<Integer, LinkedHashMap<String, String>> seats) {
		try {
			Set<Integer> keys = seats.keySet();
			System.out.print("\t");
			for (Integer key : keys) {
				System.out.println(key + " row -> " + seats.get(key));
				System.out.print("\t");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String generateBookingId() {
		int n = 20;
		StringBuilder sb = new StringBuilder(n);
		try {
			String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";


			for (int i = 0; i < n; i++) {
				int index = (int) (AlphaNumericString.length() * Math.random());
				sb.append(AlphaNumericString.charAt(index));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

}
