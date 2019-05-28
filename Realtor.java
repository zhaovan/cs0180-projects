package shopnstop.sol;

import java.util.Collections;
import java.util.List;

import shopnstop.src.LogConfirmation;
import shopnstop.src.LogError;
import shopnstop.src.LogListing;
import shopnstop.src.LogPrompt;
import shopnstop.src.UserIO;

public class Realtor implements User {

  public Realtor() {
  }

  @Override
  public void sortingOptions() {
    LogPrompt.realtorSortingOptions();
  }

  @Override
  public void addListing(List<Listings> lOfL) {
    LogPrompt.newListingName();
    String name = UserIO.getNextInput();

    LogPrompt.newListingPrice();
    String price = UserIO.getNextInput();
    double intPrice;
    try {
      intPrice = Double.parseDouble(price);
    } catch (NumberFormatException nfe) {
      LogError.invalidListingPrice();
      return;
    }

    LogPrompt.newListingSize();
    String size = UserIO.getNextInput();
    double doubleSize;
    try {
      doubleSize = Double.parseDouble(size);
    } catch (NumberFormatException nfe) {
      LogError.invalidListingSize();
      return;
    }

    LogPrompt.newListingYearBuilt();
    String year = UserIO.getNextInput();
    int intYear;
    try {
      intYear = Integer.parseInt(year);
    } catch (NumberFormatException nfe) {
      LogError.invalidListingYearBuilt();
      return;
    }

    LogPrompt.newListingRatings();
    String ratings = UserIO.getNextInput();
    String[] arrRatings = ratings.split(" ");
    double avg = 0;
    for (String s : arrRatings) {
      try {
        double ds = Double.parseDouble(s);
        avg += ds;
      } catch (NumberFormatException nfe) {
        LogError.invalidListingRatings();
        return;
      }
    }
    double dRs = avg / arrRatings.length;
    LogPrompt.newListingType();
    String nlt = UserIO.getNextInput();
    if (nlt.equals("r")) {
      Rent r = new Rent(name, intPrice, doubleSize, intYear, dRs);
      lOfL.add(r);
    } else if (nlt.equals("h")) {
      Hotel h = new Hotel(name, intPrice, doubleSize, intYear, dRs);
      lOfL.add(h);
    } else if (nlt.equals("p")) {
      Purchase p = new Purchase(name, intPrice, doubleSize, intYear, dRs);
      lOfL.add(p);
    } else {
      LogError.invalidListingType();
    }
  }

  @Override
  public void removeListing(List<Listings> lOfL) {
    for (int j = 0; j < lOfL.size(); j++) {
      lOfL.get(j).printedByRealtor(j + 1);
    }
    LogPrompt.removeListingIndex();
    String removeIndex = UserIO.getNextInput();
    try {
      int intRemoveIndex = Integer.parseInt(removeIndex);
      lOfL.remove(intRemoveIndex - 1);
      LogConfirmation.listingRemoved();
    } catch (NumberFormatException nfe) {
      LogError.indexNotValid();
    }
  }

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
    } else if (s.equals("r")) {
      Collections.sort(lOfL, TheListings.decRatingsComparator);
      LogListing.displayByRating();
    } else { // if the user didn't input a correct sorting
      // characteristic, then sort by price
      Collections.sort(lOfL, TheListings.decPriceComparator);
      LogListing.displayByDefault();
    }
    for (int i = 0; i < lOfL.size(); i++) {
      lOfL.get(i).printedByRealtor(i + 1);
    }
  }
}
