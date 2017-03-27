package etude04;
import java.util.*;
/**
 * Game.java
 * COSC326 Etude 4
 * Peanuts and Pretzels
 *
 * @author Rhianne Price
 * February 2017
 **/
public class Game {

  private int peanutsLeft, pretzelsLeft;
  private Move[] rules;

  public Game(int peanuts, int pretzels, String rules) {
    int rulesCount = rules.split(System.getProperty("line.separator")).length;

    rules = new Move[rulesCount];

    pretzelsLeft = pretzels;
    peanutsLeft = peanuts;
  }
}
