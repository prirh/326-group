package etude04;
import java.util.*;
/**
 * Move.java
 * COSC326 Etude 4
 * Peanuts and Pretzels
 *
 * @author Rhianne Price
 * February 2017
 **/
public class Move {

  char peanutsOperator;
  int peanuts;

  char pretzelsOperator;
  int pretzels;

  public Move(String move) {
    peanutsOperator = move.charAt(0);
    peanuts = Character.getNumericValue(move.charAt(1));

    pretzelsOperator = move.charAt(3);
    pretzels = Character.getNumericValue(move.charAt(4));
  }

  public String toString() {
    StringBuilder move = new StringBuilder();
    move.append(peanutsOperator).append(peanuts);
    move.append(" ");
    move.append(pretzelsOperator).append(pretzels);
    return move.toString();
  }
}
