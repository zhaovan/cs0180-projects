package shopnstop.sol;

import java.util.Collections;
import java.util.List;

import shopnstop.src.LogError;
import shopnstop.src.LogListing;
import shopnstop.src.LogPrompt;

public class Buyer implements User {

  public Buyer() {
  }

  @Override
  public void sortingOptions() {
    LogPrompt.buyerSortingOptions();
  }

  /**
   * prints error message
   */
  @Override
  public void addListing(List<Listings> lOfL) {
    LogError.onlyRealtorsAddListings();
  }

  /**
   * @throws RuntimeException because buyers can't remove listings
   */
  @Override
  public void removeListing(List<Listings> lOfL) {
    LogError.onlyRealtorsRemoveListings();
  }

  /**
   * prompts Buyer to purchase/rent/book a listing
   */
  @Override
  public void selectListing(int i) {
    Listings selected = TheListings.l.get(i);
    selected.selectedByBuyer();
  }

  @Override
  public void sortListings(String s, List<Listings> lOfL) {
    if (s.equals("p")) {
      Collections.sort(lOfL, TheListings.decPriceComparator);
      LogListing.displayByPrice();
    } else if (s.equals("s")) {
      Collections.sort(lOfL, TheListings.decSizeComparator);
      LogListing.displayBySize();
    } else if (s.equals("y")) {
      Collections.sort(lOfL, TheListings.decYearComparator);
      LogListing.displayByYearBuilt();
    } else { // if the user didn't input a correct sorting
      // characteristic, then sort by price
      Collections.sort(lOfL, TheListings.decPriceComparator);
      LogListing.displayByDefault();
    }
    for (int i = 0; i < lOfL.size(); i++) {
      lOfL.get(i).printedByBuyer(i + 1);
    }
  }
}
