package shopnstop.sol;

import shopnstop.src.LogPrompt;
import shopnstop.src.UserIO;

public abstract class Listings implements IListings {
  String name;
  double price;
  double size;
  int year;
  double ratings;

  public Listings(String name, double price, double size, int year,
      double ratings) {
    this.name = name;
    this.price = price;
    this.size = size;
    this.year = year;
    this.ratings = ratings;
  }

  public void selectedByRealtor() {
    LogPrompt.listingFieldChange();
    String field = UserIO.getNextInput();
    if (field.equals("n")) {
      LogPrompt.newListingName();
      String newName = UserIO.getNextInput();
      this.name = newName;
    }
  }

  public String getName() {
    return this.name;
  }

  public void setName(String n) {
    this.name = n;
  }
}
