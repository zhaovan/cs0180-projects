package shopnstop.src;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class LogError {
	
	/**
	 * Logs error message for when the user enters an invalid index when interacting with the 
	 */
	public static void indexNotValid() {
		System.out.println("Index not valid.");
	}
	
	/**
	 * Logs error message for when a Buyer tries to add a listing to the portal
	 */
	public static void onlyRealtorsAddListings() {
		System.out.println("Sorry, only Realtors can add listings to the Portal.");
	}
	
	/**
	 * Logs error message for when a Buyer tries to remove a listing from the portal
	 */
	public static void onlyRealtorsRemoveListings() {
		System.out.println("Sorry, only Realtors can remove listings from the Portal.");
	}
	
	/**
	 * Logs error message for when a Realtor enters an invalid listing type when creating a new
	 * listing for the portal
	 */
	public static void invalidListingType() {
		System.out.println("Not valid listing type");
	}
	
	/**
	 * Logs error message for when a Realtor enters an invalid listing price when creating a new
	 * listing for the portal
	 */
	public static void invalidListingPrice() {
		System.out.println("Not valid price.");
	}
	
	/**
	 * Logs error message for when a Realtor enters an invalid listing size when creating a new
	 * listing for the portal
	 */
	public static void invalidListingSize() {
		System.out.println("Not a valid size");
	}
	
	/**
	 * Logs error message for when a Realtor enters an invalid listing year built when creating a new
	 * listing for the portal
	 */
	public static void invalidListingYearBuilt() {
		System.out.println("Invalid year.");
	}
	
	/**
	 * Logs error message for when a Realtor enters invalid listing ratings when creating a new
	 * listing for the portal
	 */
	public static void invalidListingRatings() {
		System.out.println("Invalid ratings format.");
	}
	
	/**
	 * Logs error message for when Realtor tries to change an invalid field for a specific listing
	 */
	public static void invalidFieldChange() {
		System.out.println("Invalid field change. Voided.");
	}
	
	/**
	 * Logs error message when Buyer tries to book a Listing with dates that have already been booked
	 * 
	 * @param dates List<Date> - the dates that could not be reserved.
	 */
	public static void datesAlreadyBooked(List<Date> dates) {
		  List<Date> sortedCopy = new LinkedList<Date>(dates);
		  Collections.sort(sortedCopy);
		  System.out.println("Sorry, your listing could not be reserved for days: " + UserIO.convertDates(sortedCopy) + ". Operation voided.");
	  }

}
