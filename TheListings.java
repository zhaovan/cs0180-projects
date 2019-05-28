package shopnstop.sol;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class TheListings {
  static List<Listings> l = new ArrayList<Listings>();

  public static Comparator<Listings> decPriceComparator =
      new Comparator<Listings>() {
        @Override
        public int compare(Listings l1, Listings l2) {
          return Double.compare(l2.price, l1.price);
        }
      };

  public static Comparator<Listings> decSizeComparator =
      new Comparator<Listings>() {
        @Override
        public int compare(Listings l1, Listings l2) {
          return Double.compare(l2.size, l1.size);
        }
      };

  public static Comparator<Listings> decYearComparator =
      new Comparator<Listings>() {
        @Override
        public int compare(Listings l1, Listings l2) {
          return Integer.compare(l2.year, l1.year);
        }
      };

  public static Comparator<Listings> decRatingsComparator =
      new Comparator<Listings>() {
        @Override
        public int compare(Listings l1, Listings l2) {
          return Double.compare(l2.ratings, l1.ratings);
        }
      };
}
