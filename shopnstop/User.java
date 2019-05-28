package shopnstop.sol;

import java.util.List;

public interface User {
  /**
   * prompts the realtor to add a listing to the system
   */
  public void addListing(List<Listings> lOfL);

  /**
   * prompts the realtor to remove a listing to the system
   */
  public void removeListing(List<Listings> lOfL);

  /**
   * prompts buyer to purchase/rent/book/ a listing. Prompts realtor to edit the
   * listing
   */
  public void selectListing(int i);

  public void sortListings(String s, List<Listings> lOfL);

  public void sortingOptions();

}
