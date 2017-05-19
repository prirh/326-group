import java.util.*;
/**
 * Game.java
 * COSC326 Etude 4
 * Peanuts and Pretzels
 *
 * @author Rhianne Price, Zac Gardner, Erina Jeffery, Caleb Mitchell
 * February 2017
 **/
public class Game {
  private final int PEANUTS, PRETZELS;
  private TreeSet<String> moves;
  private Map<String, Boolean> memo;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in).useDelimiter("\n\n");
    while (scanner.hasNext()) {
      Game game = new Game(scanner.next().trim());
      if(game.PEANUTS > 1000 || game.PRETZELS > 1000) {
        System.out.println(game.PEANUTS + " peanuts and " + game.PRETZELS + "pretzels is too many snacks");
        continue;
      }
      System.out.println(game.getWinningMove());
    }
  }

  /**
   * Constructor initialises a new game from a string with the number of peanuts
   * and rule list.
   * @param game A string describing the rules of the game and the initial
   * number of snacks.
   */
  public Game(String game) {
    int rulesCount = game.split(System.getProperty("line.separator")).length - 1;
    memo = new HashMap<String, Boolean>();
    /* Moves with more snacks appear first to get the least moves. */
    moves = new TreeSet<String>(new Comparator<String>() {
      @Override
      public int compare(String m1, String m2) {
        Scanner scanner = new Scanner(m1);
        int sum1 = scanner.nextInt() + scanner.nextInt();
        scanner = new Scanner(m2);
        int sum2 = scanner.nextInt() + scanner.nextInt();
        if(sum1 > sum2) return -1;
        if(sum1 < sum2) return 1;
        else return m1.compareTo(m2);
      }
    });

    Scanner scanner = new Scanner(game);
    PEANUTS = scanner.nextInt();
    PRETZELS = scanner.nextInt();

    /* Always permissable to take exactly one of either. */
    moves.add("0 1");
    moves.add("1 0");

    if(scanner.hasNextLine()) {
      scanner.nextLine();
      for(int i = 0; i < rulesCount; i++) {
        addMoves(scanner.nextLine().trim());
      }
    }
  }

  /**
   * Checks all moves to see if they are winning ones. Starts with the move with
   * the most snacks.
   * @return the winning move.
   */
  private String getWinningMove() {
    for(String move : moves) {
      Scanner moveScanner = new Scanner(move);
      int peanutMove = moveScanner.nextInt();
      int pretzelMove = moveScanner.nextInt();
      if (winningMove(PEANUTS, peanutMove, PRETZELS, pretzelMove, true)) {
        return move;
      }
    }
    return "0 0";
  }

  /**
   * Plays out all posible options to see if the given state of the game allows
   * for a win.
   * @param peanuts the number of peanuts available.
   * @param pretzels the number of pretzels available.
   * @param peanutMove the number of peanuts to be taken in this move.
   * @param pretzelMove the number of pretzels to be taken in this move.
   * @param turn true if this move is to be executed by the us. false if not.
   * @return whether this move will lead to a win.
   */
  private boolean winningMove(int peanuts, int peanutMove, int pretzels, int pretzelMove, boolean turn) {
    if(peanuts == 0 && pretzels == 0) return !turn;
    int peanutsLeft = peanuts - peanutMove;
    int pretzelsLeft = pretzels - pretzelMove;
    for(String move : moves) {
      Scanner moveScanner = new Scanner(move);
      int nextPeanut = moveScanner.nextInt();
      int nextPretzel = moveScanner.nextInt();
      if (peanutsLeft - nextPeanut < 0 || pretzelsLeft - nextPretzel < 0) continue;
      String situation = nextPeanut + " " + nextPretzel + " " + peanutsLeft + " " + pretzelsLeft;
      if(memo.get(situation) != null) {
        if((memo.get(situation) && !turn) || (!memo.get(situation) && turn)) {
          return false;
        }
      } else {
        boolean outcome = winningMove(peanutsLeft, nextPeanut, pretzelsLeft, nextPretzel, !turn);
        memo.put(situation, outcome);
        if((!outcome && turn) || (outcome && !turn)) return outcome;
      }
    }
    return turn;
  }

  /**
   * Given a rule, this method expands the rule out into induvidual moves. All
   * possible moves are added to a sorted set.
   * @param rule the string representation of the rule to add moves for.
   */
  private void addMoves(String rule) {
    String[] moveBits = rule.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
    int peanuts = Integer.parseInt(moveBits[1]);
    int pretzels = Integer.parseInt(moveBits[3]);

    switch(moveBits[0].charAt(0) + "" + moveBits[2].charAt(1)) {
      case "==":
      moves.add(peanuts + " " + pretzels);
      break;
      case "<=":
      for(Integer i : range(0, peanuts)) {
        moves.add(i + " " + pretzels);
      }
      break;
      case ">=":
      for(Integer i : range(peanuts + 1, PEANUTS + 1)) {
        moves.add(i + " " + pretzels);
      }
      break;
      case "=<":
      for(Integer i : range(0, pretzels)) {
        moves.add(peanuts + " " + i);
      }
      break;
      case "=>":
      for(Integer i : range(pretzels + 1, PRETZELS + 1)) {
        moves.add(peanuts + " " + i);
      }
      break;
      case "<<":
      for(Integer i : range(0, peanuts)) {
        for(Integer j : range(0, pretzels)) {
          if(i == 0 && j == 0) break;
          moves.add(i + " " + j);
        }
      }
      break;
      case "<>":
      for(Integer i : range(0, peanuts)) {
        for(Integer j : range(pretzels + 1, PRETZELS + 1)) {
          moves.add(i + " " + j);
        }
      }
      break;
      case "><":
      for(Integer i : range(peanuts + 1, PEANUTS + 1)) {
        for(Integer j : range(0, pretzels)) {
          moves.add(i + " " + j);
        }
      }
      break;
      case ">>":
      for(Integer i : range(peanuts + 1, PEANUTS + 1)) {
        for(Integer j : range(pretzels + 1, PRETZELS + 1)) {
          moves.add(i + " " + j);
        }
      }
    }
  }

  /**
   * Finds all integers within a range. Used for expanding each rule out.
   * @param start the start of the range to be returned (inclusive).
   * @param end the end of the range to be returned (exclusive).
   * @return a list containing the numbers in the range.
   */
  private List<Integer> range(int start, int end) {
    ArrayList<Integer> range = new ArrayList<Integer>();
    for(int i = start; i < end; i++) {
      range.add(i);
    }
    return range;
  }
}
