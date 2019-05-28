package shopnstop.src;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class LogConfirmation {
	
	/**
	 * Logs message for when a purchase from the Buyer was successful.
	 */
	public static void purchaseComplete() {
		System.out.println("Purchase complete!");
	}
	
	/**
	 * Logs message when for when a listing was successfully removed by the Realtor
	 */
	public static void listingRemoved() {
		System.out.println("Successfully removed.");
	}

	/**
	 * Logs message when desired dates were reserved successfully
	 * 
	 * @param dates List<Date> - the dates that were successfully reserved
	 */
	public static void dateReservation(List<Date> dates) {
		List<Date> sortedCopy = new LinkedList<Date>(dates);
		Collections.sort(sortedCopy);
		System.out.println("Your listing is reserved for " + UserIO.convertDates(sortedCopy));
	}

}
