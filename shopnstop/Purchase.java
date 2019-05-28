package shopnstop.sol;

import shopnstop.src.LogConfirmation;
import shopnstop.src.LogListing;

public class Purchase extends Listings {
  String buyer;

  public Purchase(String name, double price, double size, int year,
      double ratings) {
    super(name, price, size, year, ratings);
    this.buyer = null;
  }

  @Override
  public void selectedByBuyer() {
    LogConfirmation.purchaseComplete();
    TheListings.l.remove(this);
  }

  @Override
  public void printedByBuyer(int displayNumber) {
    LogListing.forPurchaseBuyer(displayNumber, this.name, this.price,
        this.size, this.year);
  }

  @Override
  public void printedByRealtor(int displayNumber) {
    LogListing.forPurchaseRealtor(displayNumber, this.name, this.price,
        this.size, this.year, this.ratings);
  }
}
