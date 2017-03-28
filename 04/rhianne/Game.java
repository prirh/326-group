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

  private static final int PLAYER_ONE = 0;
  private static final int PLAYER_TWO = 1;

  private final int PEANUTS;
  private final int PRETZELS;
  private int peanutsLeft, pretzelsLeft;
  private Move[] rules;

  public Game(String game) {
    int rulesCount = game.split(System.getProperty("line.separator")).length + 1;
    Scanner scanner = new Scanner(game);

    PEANUTS = scanner.nextInt();
    PRETZELS = scanner.nextInt();

    peanutsLeft = PEANUTS;
    pretzelsLeft = PRETZELS;

    rules = new Move[rulesCount];

    /* It is always permissable to take exactly one of either. */
    rules[0] = new Move("=1 =0");
    rules[1] = new Move("=0 =1");

    scanner.nextLine();
    for(int i = 2; i < rulesCount; i++) {
        rules[i] = new Move(scanner.nextLine().trim());
    }
  }

  private String bestMove(int player) {
    return "pretzelsLeft = " + pretzelsLeft + "peanutsLeft = " + peanutsLeft;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in).useDelimiter("\n\n");
    while(scanner.hasNext()) {
        Game game = new Game(scanner.next().trim());
        for(Move rule : game.rules) {
          System.out.println(rule.toString());
        }
        System.out.println(game.bestMove(PLAYER_ONE));
    }
  }
}
