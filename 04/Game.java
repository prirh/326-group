package etude04;
/**
 * Game.java
 * COSC326 Etude 4
 * Peanuts and Pretzels
 *
 * @author Rhianne Price, Zac Gardner, Erina Jeffery, Caleb Mitchell
 * February 2017
 **/

import java.util.*;

public class Game {
    private Move winningMove;
    private Map<String, Integer> memo;
    private final int PEANUTS, PRETZELS;
    private Move[] rules;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n\n");
        while (scanner.hasNext()) {
            Game game = new Game(scanner.next().trim());
            if(game.PEANUTS > 1000 || game.PRETZELS > 1000) {
                System.out.println(game.PEANUTS + " peanuts and " + game.PRETZELS + " is too many snacks");
                continue;
            }
            System.out.println(game.getWinningMove());
        }
    }

    public Game(String game) {
        int rulesCount = game.split(System.getProperty("line.separator")).length - 1;
        memo = new HashMap<String, Integer>();
        rules = new Move[rulesCount];
        winningMove = new Move("=0 =0");

        Scanner scanner = new Scanner(game);
        PEANUTS = scanner.nextInt();
        PRETZELS = scanner.nextInt();

        if(scanner.hasNextLine()) {
            scanner.nextLine();
            for (int i = 0; i < rulesCount; i++) {
                rules[i] = new Move(scanner.nextLine().trim());
            }
            fixRules();
        }
    }

    /**
     * fixRules modifies rules to allow them to function correctly in the calc
     * method.
     **/
    private void fixRules() {
        for (int val = 0; val < rules.length; val++) {
            if (rules[val].peanutsOperator == '<' && rules[val].pretzelsOperator == '>') {
                char temp1 = rules[val].peanutsOperator;
                int temp2 = rules[val].peanuts;
                rules[val].peanutsOperator = rules[val].pretzelsOperator;
                rules[val].pretzelsOperator = temp1;
                rules[val].peanuts = rules[val].pretzels;
                rules[val].pretzels = temp2;
                rules[val].reversed = 1;
            }
            if (rules[val].peanutsOperator == '=' && rules[val].pretzelsOperator != '=') {
                char temp1 = rules[val].peanutsOperator;
                int temp2 = rules[val].peanuts;
                rules[val].peanutsOperator = rules[val].pretzelsOperator;
                rules[val].pretzelsOperator = temp1;
                rules[val].peanuts = rules[val].pretzels;
                rules[val].pretzels = temp2;
                rules[val].reversed = 1;
            }
        }
    }

    /**
     * Handles the call to calc and returns the winning move as a string.
     * makes sure the calc is called before winning move is accessed.
     * @return the winning move.
     */
    private String getWinningMove() {
        calc(PEANUTS, PRETZELS, 1, 0);
        return winningMove.asSolution();
    }

    /**
     * calc performs various if statements to attempt every possible answer
     * until the first correct one is found. Moves are cached in a hashmap to
     * avoid repeated calculations.
     * @param peanutsLeft the total number of pretzels at that instance
     * @param pretzelsLeft  the total number of peanuts at that instance
     * @param turn keeps track of whose turn it is
     * @param turnNo keeps track of the number of turns that have passed.
     * @return 1 if this is a winning move, 0 otherwise.
     **/
    private int calc(int peanutsLeft, int pretzelsLeft, int turn, int turnNo) {
        String situation = peanutsLeft + " " + pretzelsLeft + " " + turn;
        if(memo.get(situation) != null) return memo.get(situation);

        int peanutMove;
        int pretzelMove;
        int revTemp;
        if (peanutsLeft == 0 && pretzelsLeft == 0) {
            return 1 - turn;
        } else if (turn == 1) {
            turn = 0;
        } else {
            turn = 1;
        }

        for (int c = 0; c < rules.length; c++) {
            /* Case One: > and <
              loop  ><, i=(totalx-rulex)*ruley-1, all division by ruleY, calc+ is rulesx +1 */
            if (rules[c].peanutsOperator == '>' && rules[c].pretzelsOperator == '<') {
                for (int i = ((peanutsLeft - rules[c].peanuts) * rules[c].pretzels) - 1; i >= 0; i--) {
                    peanutMove = (i / rules[c].pretzels) + rules[c].peanuts + 1;
                    pretzelMove = i % rules[c].pretzels;

                    /* if the operators have been reversed, ensure the rule is applied correctly*/
                    if (rules[c].reversed == 1) {
                        revTemp = peanutMove;
                        peanutMove = pretzelMove;
                        pretzelMove = revTemp;
                    }

                    if (peanutsLeft - peanutMove >= 0 && pretzelsLeft - pretzelMove >= 0) {
                        if (calc(peanutsLeft - peanutMove, pretzelsLeft - pretzelMove, turn, turnNo + 1) == 1) {
                            if (turnNo == 0) {
                                memo.put(situation, 1);
                                winningMove.peanuts = peanutMove;
                                winningMove.pretzels = pretzelMove;
                                return 1;
                            } else if (turn == 0) {
                                memo.put(situation, 1);
                                return 1;
                            }
                        } else if (turn == 1) {
                            memo.put(situation, 0);
                            return 0;

                        }
                    }
                }
            }



            /* loop  <<, i=rulex*ruley-1, all division by ruleY;*/
            else if (rules[c].peanutsOperator == '>' && rules[c].pretzelsOperator == '>') {

                // if(peanutsLeft-rules[val].peanuts> pretzelsLeft-rules[val].pretzels){
                for (int i = rules[c].peanuts * rules[c].pretzels - 1; i >= 0; i--) {
                    peanutMove = i % rules[c].pretzels + rules[c].peanuts + 1;
                    pretzelMove = i / rules[c].pretzels + rules[c].pretzels + 1;

                    if (rules[c].reversed == 1) {
                        revTemp = peanutMove;
                        peanutMove = pretzelMove;
                        pretzelMove = revTemp;
                    }

                    if (peanutsLeft - peanutMove >= 0 && pretzelsLeft - pretzelMove >= 0) {
                        if (calc(peanutsLeft - peanutMove, pretzelsLeft - pretzelMove, turn, turnNo + 1) == 1) {

                            if (turnNo == 0) {
                                winningMove.peanuts = peanutMove;
                                winningMove.pretzels = pretzelMove;
                                memo.put(situation, 1);
                                return 1;
                            } else if (turn == 0) {
                                memo.put(situation, 1);
                                return 1;
                            }
                        } else if (turn == 1) {
                            memo.put(situation, 0);
                            return 0;
                        }
                    }
                }
            }


            /* loop  >>, i=(totalx-rulex)*(totaly-ruley)-1, all division by ruleY, calc+ is rulesx +1;*/
            else if (rules[c].peanutsOperator == '<' && rules[c].pretzelsOperator == '<') {
                for (int i = (rules[c].peanuts * rules[c].pretzels) - 1; i >= 0; i--) {
                    peanutMove = (i / rules[c].pretzels);
                    pretzelMove = (i % rules[c].pretzels);

                    if (peanutsLeft - peanutMove >= 0 && pretzelsLeft - pretzelMove >= 0) {
                        if (calc(peanutsLeft - peanutMove, pretzelsLeft - pretzelMove, turn, turnNo + 1) == 1) {
                            if (turnNo == 0) {
                                winningMove.peanuts = peanutMove;
                                winningMove.pretzels = pretzelMove;
                                memo.put(situation, 1);
                                return 1;
                            } else if (turn == 0) {
                                memo.put(situation, 1);
                                return 1;
                            }
                        } else if (turn == 1) {
                            memo.put(situation, 0);
                            return 0;
                        }

                    }
                }
            }

            // IF RULE X IS < VALUE AND RULE Y = VALUE
            else if (rules[c].peanutsOperator == '<' && rules[c].pretzelsOperator == '=') {
                for (int i = rules[c].peanuts - 1; i > 0; i--) {
                    pretzelMove = rules[c].pretzels;
                    peanutMove = i;

                    if (rules[c].reversed == 1) {
                        revTemp = peanutMove;
                        peanutMove = pretzelMove;
                        pretzelMove = revTemp;
                    }

                    if (peanutsLeft - i >= 0 && pretzelsLeft - pretzelMove >= 0) {
                        if (calc(peanutsLeft - i, pretzelsLeft - pretzelMove, turn, turnNo + 1) == 1) {
                            if (turnNo == 0) {
                                winningMove.peanuts = peanutMove;
                                winningMove.pretzels = pretzelMove;
                                memo.put(situation, 1);
                                return 1;

                            } else if (turn == 0) {
                                memo.put(situation, 1);
                                return 1;
                            }
                        } else if (turn == 1) {
                            memo.put(situation, 0);
                            return 0;
                        }

                    }
                }
            }

            //if rule x > value and rule y = value
            else if (rules[c].peanutsOperator == '>' && rules[c].pretzelsOperator == '=') {


                for (int i = (peanutsLeft - (rules[c].peanuts + 1)); i >= 0; i--) {
                    pretzelMove = rules[c].pretzels;;
                    peanutMove = i + rules[c].peanuts + 1;;

                    if (rules[c].reversed == 1) {
                        revTemp = peanutMove;
                        peanutMove = pretzelMove;
                        pretzelMove = revTemp;
                    }

                    if (peanutsLeft - peanutMove >= 0 && pretzelsLeft - pretzelMove >= 0) {
                        if (calc(peanutsLeft - peanutMove, pretzelsLeft - pretzelMove, turn, turnNo + 1) == 1) {
                            if (turnNo == 0) {
                                winningMove.peanuts = peanutMove;
                                winningMove.pretzels = pretzelMove;
                                memo.put(situation, 1);
                                return 1;
                            } else if (turn == 0) {
                                memo.put(situation, 1);
                                return 1;
                            }
                        } else if (turn == 1) {
                            memo.put(situation, 0);
                            return 0;
                        }
                    }

                }
            } else if (rules[c].peanutsOperator == '=' && rules[c].pretzelsOperator == '=') {

                peanutMove = rules[c].peanuts;
                pretzelMove = rules[c].pretzels;

                if (rules[c].reversed == 1) {
                    revTemp = peanutMove;
                    peanutMove = pretzelMove;
                    pretzelMove = revTemp;
                }

                if (peanutsLeft - peanutMove >= 0 && pretzelsLeft - pretzelMove >= 0) {
                    if (calc(peanutsLeft - peanutMove, pretzelsLeft - pretzelMove, turn, turnNo + 1) == 1) {
                        if (turnNo == 0) {

                            winningMove.peanuts = peanutMove;
                            winningMove.pretzels = pretzelMove;
                            memo.put(situation, 1);
                            return 1;
                        } else if (turn == 0) {
                            memo.put(situation, 1);
                            return 1;
                        }
                    } else if (turn == 1) {
                        memo.put(situation, 0);
                        return 0;
                    }


                }
            }
        }
        /* it is always permissable to take one of either group,
           hence the below being in no loop.*/
        if (peanutsLeft >= 1) {
            if (calc(peanutsLeft - 1, pretzelsLeft, turn, turnNo + 1) == 1) {
                if (turnNo == 0) {
                    winningMove.peanuts = 1;
                    winningMove.pretzels = 0;
                    memo.put(situation, 1);
                    return 1;
                } else if (turn == 0) {
                    memo.put(situation, 1);
                    return 1;
                }
            } else if (turn == 1) {
                memo.put(situation, 0);
                return 0;
            }
        }
        if (pretzelsLeft >= 1) {
            if (calc(peanutsLeft, pretzelsLeft - 1, turn, turnNo + 1) == 1) {
                if (turnNo == 0) {
                    winningMove.peanuts = 0;
                    winningMove.pretzels = 1;
                    memo.put(situation, 1);
                    return 1;
                } else if (turn == 0) {
                    memo.put(situation, 1);
                    return 1;
                }
            } else if (turn == 1) {
                memo.put(situation, 0);
                return 0;
            }
        }
        if (turn == 1) {
            memo.put(situation, 1);
            return 1;
        }
        memo.put(situation, 0);
        return 0;
    }
}
