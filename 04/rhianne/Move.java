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

    /**
     *Move constructor class taking all needed information for a single rule.
     *@arg move. input string rule.
     **/
    public Move(String move) {
        // separates the string into operator, number, space, operator, number
        String[] splitMove = move.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        peanutsOperator = splitMove[0].charAt(0);
        peanuts = Integer.parseInt(splitMove[1]);

        pretzelsOperator = splitMove[2].charAt(1); // charAt(1) to jump the space
        pretzels = Integer.parseInt(splitMove[3]);
    }

    /**
     * to String class reconstructs the input string 'move' and re outputs it.
     **/
     
    public String toString() {
        StringBuilder move = new StringBuilder();
        move.append(peanutsOperator).append(peanuts);
        move.append(" ");
        move.append(pretzelsOperator).append(pretzels);
        return move.toString();
    }

    public String asSolution() {
        if (peanutsOperator == '=' && pretzelsOperator == '=') {
            return peanuts + " " + pretzels;
        }
        return "error: not a solution";
    }
}
