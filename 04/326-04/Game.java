package etude04;
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
    private Move[] rules;
    private Move winningMove;
    private Map<String, Integer> memo;

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

        for(int val = 0; val < rules.length; val++){

            if(rules[val].peanutsOperator=='<'
               && rules[val].pretzelsOperator=='>'){
                char temp1 = rules[val].peanutsOperator;
                int temp2 = rules[val].peanuts;
                rules[val].peanutsOperator = rules[val].pretzelsOperator;
                rules[val].pretzelsOperator = temp1;
                rules[val].peanuts = rules[val].pretzels;
                rules[val].pretzels = temp2;
                rules[val].reversed =1;
            }
            if(rules[val].peanutsOperator=='='
               && rules[val].pretzelsOperator!='='){
                char temp1 = rules[val].peanutsOperator;
                int temp2 = rules[val].peanuts;
                rules[val].peanutsOperator = rules[val].pretzelsOperator;
                rules[val].pretzelsOperator = temp1;
                rules[val].peanuts = rules[val].pretzels;
                rules[val].pretzels = temp2;
                rules[val].reversed =1;
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
     * calc preforms various if statemnets to attempt
     * every possible answer until the first correct one is found.
     *@arg x2 represents the total number of pretzels at that instance
     *@arg y2 represents the total number of peanuts at that instance
     *@arg rules contains the entire list of rules for this one input
     *@arg turn keeps track of whose turn it is
     *@arg turnNo keeps track of the number of turns that have passed.
     **/
    public int calc(int x2, int y2,  int turn, int turnNo){
        // @param turn is always 1 to begin with, we start on the players turn.
        // int test2= test;

        String situation = x2 + " " + y2 + " " + turn;
        if(memo.get(situation) != null) return memo.get(situation);

        int altx;
        int alty;
        int revTemp;
        if(x2==0 &&y2==0){
            return 1 - turn;

        }

        else if(turn ==1){
            turn =0;
        }
        else{
            turn =1;
        }
        for(int c = 0; c < rules.length; c++){


            /* loop  ><, i=(totalx-rulex)*ruley-1, all division by ruleY, calc+ is rulesx +1;*/

            if(rules[c].peanutsOperator =='>'
               && rules[c].pretzelsOperator=='<'){

                for(int i=((x2-rules[c].peanuts)*rules[c].pretzels)-1; i>=0;i--){
                    altx = (i/rules[c].pretzels)+rules[c].peanuts+1;
                    alty=i%rules[c].pretzels;

                    /* if the operators have been reversed, ensure the rule is applied correctly*/
                    if(rules[c].reversed ==1){
                        revTemp =altx;
                        altx = alty;
                        alty = revTemp;
                    }

                    if(x2-altx>=0 && y2-alty>=0){
                        if(calc(x2-altx,y2-alty,turn,turnNo+1)==1){
                            if(turnNo==0){
                                memo.put(situation, 1);
                                winningMove.peanuts = altx;
                                winningMove.pretzels = alty;
                                return 1;
                            }else if(turn ==0){
                                memo.put(situation, 1);
                                return 1;
                            }
                        }


                        else if(turn ==1){
                            memo.put(situation, 0);
                            return 0;

                        }
                    }
                }
            }



            /* loop  <<, i=rulex*ruley-1, all division by ruleY;*/
            else if(rules[c].peanutsOperator=='>'
                    &&rules[c].pretzelsOperator=='>'){

                // if(x2-rules[val].peanuts> y2-rules[val].pretzels){
                for(int i=rules[c].peanuts*rules[c].pretzels-1; i>=0;i--){
                    altx=i%rules[c].pretzels +rules[c].peanuts+1;
                    alty= i/rules[c].pretzels + rules[c].pretzels+1;

                    if(rules[c].reversed ==1){
                        revTemp =altx;
                        altx = alty;
                            alty = revTemp;
                    }

                    if(x2-altx>=0 && y2-alty>=0){
                        if(calc(x2-altx,y2-alty,turn,turnNo+1)==1){

                            if(turnNo==0){
                                winningMove.peanuts = altx;
                                winningMove.pretzels = alty;
                                memo.put(situation, 1);
                            }
                            else if(turn ==0){
                                memo.put(situation, 1);
                                return 1;
                            }
                        }
                        else if(turn ==1){
                            memo.put(situation, 0);
                            return 0;
                        }
                    }
                }


            }


            /* loop  >>, i=(totalx-rulex)*(totaly-ruley)-1, all division by ruleY, calc+ is rulesx +1;*/
            else if(rules[c].peanutsOperator=='<'
                    && rules[c].pretzelsOperator=='<'){
                for(int i=(rules[c].peanuts*rules[c].pretzels)-1; i>=0;i--){
                    altx= (i/rules[c].pretzels);
                    alty= (i%rules[c].pretzels);

                    if(x2-altx >=0 && y2-alty>=0){
                        if(calc(x2-altx,y2-alty,turn,turnNo+1)==1){
                            if(turnNo==0){
                                winningMove.peanuts = altx;
                                winningMove.pretzels = alty;
                                memo.put(situation, 1);
                                return 1;
                            }
                            else if(turn ==0){
                                memo.put(situation, 1);
                                return 1;
                            }
                        }
                        else if(turn ==1){
                            memo.put(situation, 0);
                            return 0;
                        }

                    }
                }
            }

            // IF RULE X IS < VALUE AND RULE Y = VALUE
            else if(rules[c].peanutsOperator=='<'
                    && rules[c].pretzelsOperator=='='){
                for(int i=rules[c].peanuts-1; i>=0;i--){
                    alty =rules[c].pretzels;
                    altx =i;

                    if(rules[c].reversed ==1){
                        revTemp =altx;
                            altx = alty;
                            alty = revTemp;
                    }

                    if(x2-i >=0 &&y2-alty>=0){
                        if(calc(x2-altx, y2-alty,turn,turnNo+1) ==1){
                            if(turnNo==0){
                                winningMove.peanuts = altx;
                                winningMove.pretzels = alty;
                                memo.put(situation, 1);
                                return 1;

                            }
                            else if(turn ==0){
                                memo.put(situation, 1);
                                return 1;
                            }
                        }else if(turn ==1){
                            memo.put(situation, 0);
                            return 0;
                        }

                    }
                }
            }

            //if rule x > value and rule y = value
            else if(rules[c].peanutsOperator=='>'
                    && rules[c].pretzelsOperator=='='){


                for(int i=(x2 - (rules[c].peanuts+1)); i>=0;i--){
                    alty = rules[c].pretzels;;
                    altx =i+rules[c].peanuts+1;;

                    if(rules[c].reversed ==1){
                        revTemp =altx;
                            altx = alty;
                            alty = revTemp;
                    }

                    if(x2-altx>=0 && y2-alty>=0){
                        if(calc(x2-altx, y2-alty,turn,turnNo+1) ==1){
                            if(turnNo==0){
                                winningMove.peanuts = altx;
                                winningMove.pretzels = alty;
                                memo.put(situation, 1);
                                return 1;
                            }
                            else if(turn ==0){
                                memo.put(situation, 1);
                                return 1;
                            }
                        }
                        else if(turn==1){
                            memo.put(situation, 0);
                            return 0;
                        }
                    }

                }
            }

            else if(rules[c].peanutsOperator=='='
                    && rules[c].pretzelsOperator =='='){

                altx = rules[c].peanuts;
                alty = rules[c].pretzels;

                if(rules[c].reversed ==1){
                    revTemp =altx;
                        altx = alty;
                        alty = revTemp;
                }

                if(x2-altx>=0 && y2-alty>=0){
                    if(calc(x2-altx, y2-alty,turn,turnNo+1)==1){
                        if(turnNo==0){
                            winningMove.peanuts = altx;
                            winningMove.pretzels = alty;
                            memo.put(situation, 1);
                            return 1;
                        }else if(turn ==0){
                            memo.put(situation, 1);
                            return 1;
                        }
                    }
                    else if(turn==1){
                        memo.put(situation, 0);
                        return 0;
                    }


                }
            }
        }
        /* it is always permissbale to take one of either group,
           hence the below being in no loop.*/
        if(x2>=1){
            if(calc(x2-1,y2,turn,turnNo+1)==1){
                if(turnNo==0){
                    winningMove.peanuts = 1;
                    winningMove.pretzels = 0;
                    memo.put(situation, 1);
                    return 1;
                }
                else if(turn ==0){
                    memo.put(situation, 1);
                    return 1;
                }
            }
            else if(turn ==1){
                memo.put(situation, 0);
                return 0;
            }
        }
        if(y2>=1){
            if(calc(x2,y2-1,turn,turnNo+1)==1){
                if(turnNo==0){
                    winningMove.peanuts = 0;
                    winningMove.pretzels = 1;
                    memo.put(situation, 1);
                    return 1;
                }else if(turn ==0){
                    memo.put(situation, 1);
                    return 1;
                }
            }
            else if(turn ==1){
                memo.put(situation, 0);
                return 0;
            }
        }
        if(turn ==1){
            memo.put(situation, 1);
            return 1;
        }
        memo.put(situation, 0);
        return 0;
    }
}
