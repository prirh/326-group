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
  private static final Move YOU_LOSE = new Move("=0 =0");

  private final int PEANUTS, PRETZELS;
  private Move[] rules;

  public Game(String game) {
    int rulesCount = game.split(System.getProperty("line.separator")).length + 1;
    Scanner scanner = new Scanner(game);

    PEANUTS = scanner.nextInt();
    PRETZELS = scanner.nextInt();

    rules = new Move[rulesCount];

    /* It is always permissable to take exactly one of either. */
    rules[0] = new Move("=1 =0");
    rules[1] = new Move("=0 =1");

    scanner.nextLine();
    for(int i = 2; i < rulesCount; i++) {
        rules[i] = new Move(scanner.nextLine().trim());
    }
  }

  private Move bestMove(int peanutsLeft, int pretzelsLeft) {
    return YOU_LOSE;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in).useDelimiter("\n\n");
    while(scanner.hasNext()) {
        Game game = new Game(scanner.next().trim());
        System.out.println(game.bestMove(game.PEANUTS, game.PRETZELS).asSolution());
    }
  }
}
