package etude04;
/**
 * Move.java
 * COSC326 Etude 4
 * Peanuts and Pretzels
 *
 * @author Rhianne Price, Zac Gardner, Erina Jeffery, Caleb Mitchell
 * February 2017
 **/
import java.util.*;

/**
 * Move class encapsualates the relevant information for a move, and some
 * printing functionality.
 */
public class Move {

    char peanutsOperator;
    int peanuts;

    char pretzelsOperator;
    int pretzels;
    int reversed;

    /**
     * Move constructor taking all needed information for a single rule.
     * @param move input string rule.
     **/
    public Move(String move) {
        // separates the string into operator, number, space, operator, number
        String[] splitMove = move.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        peanutsOperator = splitMove[0].charAt(0);
        peanuts = Integer.parseInt(splitMove[1]);

        pretzelsOperator = splitMove[2].charAt(1); // charAt(1) to jump the space
        pretzels = Integer.parseInt(splitMove[3]);
        reversed = 0;
    }

    /**
     * toString reconstructs the input string 'move' and re outputs it.
     * @return a string representation of this move.
     **/
    public String toString() {
        StringBuilder move = new StringBuilder();
        move.append(peanutsOperator).append(peanuts);
        move.append(" ");
        move.append(pretzelsOperator).append(pretzels);
        return move.toString();
    }

    /**
     * Prints the move in acceptable solution format, without its operators.
     * @return a string representation of this move, without operators.
     */
    public String asSolution() {
        if (peanutsOperator == '=' && pretzelsOperator == '=') {
            return peanuts + " " + pretzels;
        }
        return "error: not a solution";
    }
}
