package shopnstop.sol;

import java.util.List;

import shopnstop.src.LogError;
import shopnstop.src.LogPrompt;
import shopnstop.src.UserIO;

public class Portal {

  public static User determineUser() {
    String userType = UserIO.getNextInput();
    while ((!userType.equals("1") && !userType.equals("2"))
        && !userType.equals("3")) {
      userType = UserIO.getNextInput();
      LogError.indexNotValid();
    }
    if (userType.equals("1")) {
      return new Buyer();
    } else if (userType.equals("2")) {
      return new Realtor();
    } else {
      System.exit(0);
      return null;
    }
  }

  public static void main(String[] args) {
    LogPrompt.login();
    User userType = determineUser();
    String index = "0";
    List<Listings> lOfL = TheListings.l;
    String sorter;
    while (true) {
      LogPrompt.home();
      index = UserIO.getNextInput();
      // view listings
      if (index.equals("1")) {
        userType.sortingOptions();
        sorter = UserIO.getNextInput();
        userType.sortListings(sorter, lOfL);
      } else if (index.equals("2")) {
        userType.addListing(lOfL);
      } else if (index.equals("3")) {
        userType.removeListing(lOfL);
      } else if (index.equals("4")) { // select listings
        userType.sortingOptions();
        sorter = UserIO.getNextInput();
        userType.sortListings(sorter, lOfL);
        // getting the index for selecting a listing
        LogPrompt.listingIndex();
        String listingIndex = UserIO.getNextInput();
        int intListingIndex = Integer.parseInt(listingIndex);
        if ((0 < intListingIndex && intListingIndex <= lOfL.size())
            && (intListingIndex % 1) == 0) {
          userType.selectListing(intListingIndex - 1);
        } else {
          LogError.indexNotValid();
        }
      } else if (index.equals("5")) {
        LogPrompt.login();
        userType = determineUser();
      } else if (index.equals("6")) {
        System.exit(0);
      }
    }
  }
}
