package shopnstop.src;

public class LogListing {

	private static void listingRealtor(int displayNumber, String name, String type, double price, double size, int year, double ratings) {
		String description = displayNumber + ". " + name + " | " + type + "\n";
		description += "$" + price + " | " + size + " sqr feet | Built " + year + " | " + ratings + " ratings on average";
		System.out.println(description);
	}

	private static void listingBuyer(int displayNumber, String name, String type, double price, double size, int year) {
		String description = displayNumber + ". " + name + " | " + type + "\n";
		description += "$" + price + " | " + size + " sqr feet | Built " + year;
		System.out.println(description);
	}

	/**
	 * Displays the listing information for a Hotel with following fields for a Realtor
	 *
	 * @param displayNumber int - the relative order, amongst other listings, of the Listing
	 * to be displayed (ex: if it's the first listing, its displayNumber will be 1)
	 * @param name String - the name of the listing
	 * @param price double - the price of the listing
	 * @param size double - the size (in square feet) of the listing
	 * @param year int - the year the listing was built
	 * @param ratings double - the average ratings for the listing
	 */
	public static void forHotelRealtor(int displayNumber, String name, double price, double size, int year, double ratings) {
		listingRealtor(displayNumber, name, "Hotel", price, size, year, ratings);
	}

	/**
	 * Displays the listing information for a Rental Listing with following fields for a Realtor
	 *
	 * @param displayNumber int - the relative order, amongst other listings, of the Listing
	 * to be displayed (ex: if it's the first listing, its displayNumber will be 1)
	 * @param name String - the name of the listing
	 * @param price double - the price of the listing
	 * @param size double - the size (in square feet) of the listing
	 * @param year int - the year the listing was built
	 * @param ratings double - the average ratings for the listing
	 */
	public static void forRentRealtor(int displayNumber, String name, double price, double size, int year, double ratings) {
		listingRealtor(displayNumber, name, "Rent Listing", price, size, year, ratings);
	}

	/**
	 * Displays the listing information for a Purchase Listing with following fields for a Realtor
	 *
	 * @param displayNumber int - the relative order, amongst other listings, of the Listing
	 * to be displayed (ex: if it's the first listing, its displayNumber will be 1)
	 * @param name String - the name of the listing
	 * @param price double - the price of the listing
	 * @param size double - the size (in square feet) of the listing
	 * @param year int - the year the listing was built
	 * @param ratings double - the average ratings for the listing
	 */
	public static void forPurchaseRealtor(int displayNumber, String name, double price, double size, int year, double ratings) {
		listingRealtor(displayNumber, name, "Listing for Purchase", price, size, year, ratings);
	}

	/**
	 * Displays the listing information for a Hotel Listing with following fields for a Buyer
	 *
	 * @param displayNumber int - the relative order, amongst other listings, of the Listing
	 * to be displayed (ex: if it's the first listing, its displayNumber will be 1)
	 * @param name String - the name of the listing
	 * @param price double - the price of the listing
	 * @param size double - the size (in square feet) of the listing
	 * @param year int - the year the listing was built
	 */
	public static void forHotelBuyer(int displayNumber, String name, double price, double size, int year) {
		listingBuyer(displayNumber, name, "Hotel", price, size, year);
	}

	/**
	 * Displays the listing information for a Rental Listing with following fields for a Buyer
	 *
	 * @param displayNumber int - the relative order, amongst other listings, of the Listing
	 * to be displayed (ex: if it's the first listing, its displayNumber will be 1)
	 * @param name String - the name of the listing
	 * @param price double - the price of the listing
	 * @param size double - the size (in square feet) of the listing
	 * @param year int - the year the listing was built
	 */
	public static void forRentBuyer(int displayNumber, String name, double price, double size, int year) {
		listingBuyer(displayNumber, name, "Rent Listing", price, size, year);
	}

	/**
	 * Displays the listing information for a Purchase Listing with following fields for a Buyer
	 *
	 * @param displayNumber int - the relative order, amongst other listings, of the Listing
	 * to be displayed (ex: if it's the first listing, its displayNumber will be 1)
	 * @param name String - the name of the listing
	 * @param price double - the price of the listing
	 * @param size double - the size (in square feet) of the listing
	 * @param year int - the year the listing was built
	 */
	public static void forPurchaseBuyer(int displayNumber, String name, double price, double size, int year) {
		listingBuyer(displayNumber, name, "Listing for Purchase", price, size, year);
	}

	/**
	 * Logs message informing user that the listings will be sorted in decreasing order by price
	 */
	public static void displayByPrice() {
		System.out.println("Displaying sorted by price.");
	}

	/**
	 * Logs message informing user that the listings will be sorted in decreasing order by size
	 */
	public static void displayBySize() {
		System.out.println("Displaying sorted by size.");
	}

	/**
	 * Logs message informing user that the listings will be sorted in decreasing order by year built
	 */
	public static void displayByYearBuilt() {
		System.out.println("Displaying sorted by year built.");
	}

	/**
	 * Logs message informing user that the listings will be sorted in decreasing order by average rating
	 */
	public static void displayByRating() {
		System.out.println("Displaying sorted by average ratings.");
	}

	/**
	 * Logs message informing user that the listings will be sorted in decreasing order by price since
	 * the desired sorting factor could not be recognized
	 */
	public static void displayByDefault() {
		System.out.println("Not recognized. Displaying sorted by price.");
	}

}
