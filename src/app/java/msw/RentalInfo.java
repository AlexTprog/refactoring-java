package app.java.msw;

import java.util.HashMap;
import java.util.Map;

public class RentalInfo {

  public String statement(Customer customer) {
    
    Map<String, Movie> movies = new HashMap<String, Movie>();    
    movies.put("F001", new Movie("You've Got Mail", MovieCode.regular));
    movies.put("F002", new Movie("Matrix", MovieCode.regular));
    movies.put("F003", new Movie("Cars", MovieCode.childrens));
    movies.put("F004", new Movie("Fast & Furious X", MovieCode.premiere));

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.getName() + "\n";
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = 0;

      // determine amount for each movie
      if (movies.get(r.getMovieId()).getCode().equals(MovieCode.regular)) {
        thisAmount = 2;
        if (r.getDays() > 2) {
          thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
        }
      }
      if (movies.get(r.getMovieId()).getCode().equals(MovieCode.premiere)) {
        thisAmount = r.getDays() * 3;
      }
      if (movies.get(r.getMovieId()).getCode().equals(MovieCode.childrens)) {
        thisAmount = 1.5;
        if (r.getDays() > 3) {
          thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
        }
      }

      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (movies.get(r.getMovieId()).getCode() == MovieCode.premiere && r.getDays() > 2) frequentEnterPoints++;

      //print figures for this rental
      result += "\t" + movies.get(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";

    return result;
  }
}
