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

  private final int PEANUTS;
  private final int PRETZELS;
  private int peanutsLeft, pretzelsLeft;
  private Move[] rules;

  public Game(String game) {
    int rulesCount = game.split(System.getProperty("line.separator")).length - 1;
    Scanner scanner = new Scanner(game);

    PEANUTS = scanner.nextInt();
    PRETZELS = scanner.nextInt();

    peanutsLeft = PEANUTS;
    pretzelsLeft = PRETZELS;

    rules = new Move[rulesCount];

    for(int i = 0; i < rulesCount; i++) {
        rules[i] = new Move(scanner.nextLine());
    }
  }

  private static String bestMove() {
  }

  public static void main(String args) {
    Scanner scanner = new Scanner(System.in).useDelimeter("\n\n");
    while(scanner.hasNext()) {
        Game game = new Game(scanner.next());
        System.out.println(game.bestMove());
    }
  }
}
