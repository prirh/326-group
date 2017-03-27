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
    peanuts = move.charAt(1);

    pretzelsOperator = move.charAt(3);
    peanuts = move.charAt(4);
  }
}
