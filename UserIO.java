package shopnstop.src;

import java.io.BufferedReader;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class UserIO {

	  private static BufferedReader _reader = new BufferedReader(new InputStreamReader(System.in));

	  /**
	   * Reads a single line of input from stdin (System.in)
	   *
	   * @return the line read or NULL if reached EOF
	   */
	  public static String getNextInput() {
	    try {
	      String line = _reader.readLine();
	      if (line == null) {
	    	  System.exit(0);
	      }
	      return line;
	    } catch (IOException e) {
	      System.out.println("Error reading user input, exiting.");
	      System.exit(1);
	    }
	    return "";
	  }

	  /**
	   * Method responsible for parsing dates. Takes in format MM-dd-yyyy
	   *
	   * @param rawInput String - Date formatted String
	   * @return list of date objects
	   */
	  public static List<Date> parseDates(String rawInput) {
		  String[] individualDates = rawInput.split(" ");
		  List<Date> parsedDates = new LinkedList<Date>();
		  SimpleDateFormat parser = new SimpleDateFormat("MM-dd-yyyy");
		  for (String rawDate : individualDates) {
			Date parsedDate;
			try {
				parsedDate = parser.parse(rawDate);
			} catch (ParseException e) {
				System.out.println("Invalid date format, please try again.");
				return new LinkedList<Date>();
			}
			parsedDates.add(parsedDate);
		  }
		  return parsedDates;
	  }

	  /**
	   * Method responsible for converting dates into formatted strings
	   *
	   * @param datesToDisplay - a list of dates the user wants to display
	   * @return a string with the dates to be displayed
	   */
	  public static String convertDates(List<Date> datesToDisplay) {
		  String readableDates = "";
		  for (Date date : datesToDisplay) {
			  readableDates += String.format("%tb %te, %tY; ", date, date, date);
		  }
		  return readableDates.substring(0, readableDates.length() -2);
	  }
}
