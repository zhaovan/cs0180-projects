package shopnstop.src;

public class LogPrompt {

	/**
	 * Logs prompt for login page
	 */
	public static void login() {
		System.out.println("[1]<Login As New Buyer> [2]<Login As New Realtor> [3]<Quit>");
	}
	
	/**
	 * Logs prompt for the home page (right after user logs in)
	 */
	public static void home() {
		System.out.println("[1]<View Available Listings> [2]<Add Listing> [3]<Remove Listing> [4]<Select Listing> [5]<Logout> [6]<Quit>");
	}
	
	/**
	 * Logs prompt with sorting criteria available to the Buyer
	 */
	public static void buyerSortingOptions() {
		System.out.println("Sorting Options: [p]<Price> [s]<Size/Square Feet> [y]<Year Built>");
	}
	
	/**
	 * Logs prompt with sorting criteria available to the Listing (includes average ratings)
	 */
	public static void realtorSortingOptions() {
		System.out.println("Sorting Options: [p]<Price> [s]<Size/Square Feet> [y]<Year Built> [r]<Average Ratings>");
	}
	
	/**
	 * Logs prompt for listing type when Realtor requests to create a new listing
	 */
	public static void newListingType() {
		System.out.print("Lisitng type: [r]<Rent> [h]<Hotel> [p]<Purchase>: ");
	}
	
	/**
	 * Logs prompt for listing name when Realtor requests to create a new listing
	 */
	public static void newListingName() {
		System.out.print("Listing Name: ");
	}
	
	/**
	 * Logs prompt for listing price when Realtor requests to create a new listing
	 */
	public static void newListingPrice() {
		System.out.print("Price: ");
	}
	
	/**
	 * Logs prompt for listing size when Realtor requests to create a new listing
	 */
	public static void newListingSize() {
		System.out.print("Size: ");
	}
	
	/**
	 * Logs prompt for the year the listing was built when Realtor requests to create a new listing
	 */
	public static void newListingYearBuilt() {
		System.out.print("Year built: ");
	}
	
	/**
	 * Logs prompt for listing ratings when Realtor requests to create a new listing
	 */
	public static void newListingRatings() {
		System.out.print("Space separated ratings out of 5 (ex: 5 3 2 0 2): ");
	}
	
	/**
	 * Logs prompt to Realtor for which Listing should be removed based on its index
	 */
	public static void removeListingIndex() {
		System.out.print("Please enter listing to be removed: ");
	}
	
	/**
	 * Logs prompt when Realtor selects command to change the field of a particular listing
	 */
	public static void listingFieldChange() {
		System.out.println("Choose field to change: [n]<Name> [p]<Price> [s]<Square Feet/Size> [y]<Year Built>");
	}
	
	/**
	 * Logs prompt when Buyer selects command to book a Listing for a set of particular dates
	 */
	public static void enterDates() {
		System.out.print("Please specify dates you want to book your stay in the format MM-DD-YYYY: ");
	}
	
	/**
	 * Prompts user to enter the index of the Listing they plan on interacting with
	 */
	public static void listingIndex() {
		System.out.println("Please choose listing index to select.");
	}

}
