package shopnstop.sol;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import shopnstop.src.LogConfirmation;
import shopnstop.src.LogError;
import shopnstop.src.LogListing;
import shopnstop.src.LogPrompt;
import shopnstop.src.UserIO;

public class Hotel extends Listings {
  List<Date> date;

  public Hotel(String name, double price, double size, int year, double ratings) {
    super(name, price, size, year, ratings);

    this.date = new ArrayList<Date>();
  }

  @Override
  public void selectedByBuyer() {
    LogPrompt.enterDates();
    String dates = UserIO.getNextInput();
    List<Date> lofD = UserIO.parseDates(dates);
    List<Date> invalidDates = new LinkedList<Date>();
    for (Date d : lofD) {
      if (this.date.contains(d)) {
        lofD.remove(d);
        invalidDates.add(d);
      } else {
        this.date.add(d);
      }
    }
    // print any invalid, already booked dates
    if (!invalidDates.isEmpty()) {
      LogError.datesAlreadyBooked(invalidDates);
    }

    if (!lofD.isEmpty()) {
      LogConfirmation.dateReservation(lofD);
    }

  }

  @Override
  public void printedByBuyer(int displayNumber) {
    LogListing.forHotelBuyer(displayNumber, this.name, this.price, this.size,
        this.year);

  }

  @Override
  public void printedByRealtor(int displayNumber) {
    LogListing.forHotelRealtor(displayNumber, this.name, this.price, this.size,
        this.year, this.ratings);
  }
}
