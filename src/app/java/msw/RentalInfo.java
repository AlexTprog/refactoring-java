package app.java.msw;

import java.util.HashMap;
import java.util.Map;

public class RentalInfo {

  public String statement(Customer customer) {

    Map<String, Movie> movies = new HashMap<>();
    movies.put("F001", new Movie("You've Got Mail", MovieCode.REGULAR));
    movies.put("F002", new Movie("Matrix", MovieCode.REGULAR));
    movies.put("F003", new Movie("Cars", MovieCode.CHILDRENS));
    movies.put("F004", new Movie("Fast & Furious X", MovieCode.PREMIERE));

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    StringBuilder result = new StringBuilder();
        
    result.append("Rental Record for " + customer.getName() + "\n");

    for (MovieRental r : customer.getRentals()) {
      double thisAmount = 0;

      // determine amount for each movie
      switch (movies.get(r.getMovieId()).getCode()) {
        case REGULAR:
          thisAmount = 2;
          if (r.getDays() > 2) {
            thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
          }
          break;
        case PREMIERE:
          thisAmount = r.getDays() * 3;
          // add bonus for a two day new release rental
          if (r.getDays() > 2)
            frequentEnterPoints++;
          break;
        case CHILDRENS:
          thisAmount = 1.5;
          if (r.getDays() > 3) {
            thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
          }
          break;
        default:
          break;
      }
      // add frequent bonus points
      frequentEnterPoints++;
      // print figures for this rental
      result.append("\t" + movies.get(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n");      
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines    
    result.append("Amount owed is " + totalAmount + "\n");
    result.append("You earned " + frequentEnterPoints + " frequent points\n");
    
    return result.toString();
  }
}
