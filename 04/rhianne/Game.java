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
    private static final Move YOU_LOSE = new Move("=1 =1");
    private Move winningMove;

    private final int PEANUTS, PRETZELS;
    private Move[] rules;


    public static void main(String[] args) {
        System.out.println("****************************");
        Scanner scanner = new Scanner(System.in).useDelimiter("\n\n");
        while(scanner.hasNext()) {
            Game game = new Game(scanner.next().trim());
            game.calc(game.PEANUTS, game.PRETZELS, 1);
            System.out.println(game.winningMove.asSolution());
        }

    }

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

    public int calc(int pretzelsLeft, int peanutsLeft, int turn){
        // @param turn is always 1
        System.out.println("peanuts: " + peanutsLeft + " pretzels: " + pretzelsLeft);

        if(peanutsLeft == 0 && pretzelsLeft == 0){
            return 1 - turn;
        }
        else if(turn == 1){
            turn = 0;
        }
        else {
            turn = 1;
        }
        for(int val = 0; val < rules.length; val++){

            /* loop  ><, i=(totalx-rulex)*ruley-1, all division by ruleY, calc+ is rulesx +1;*/

            if(rules[val].peanutsOperator == '>' && rules[val].pretzelsOperator == '<') {
                for(int i = ((peanutsLeft - rules[val].peanuts) * rules[val].pretzels) - 1; i >= 0; i--) {
                    if(peanutsLeft - ((i/rules[val].pretzels) + rules[val].peanuts + 1) >= 0 && pretzelsLeft - i % rules[val].pretzels >= 0) {
                        if(calc(peanutsLeft-((i/rules[val].pretzels) + rules[val].peanuts + 1), pretzelsLeft-i%rules[val].pretzels, turn) == 1) {
                            winningMove = new Move((i/rules[val].pretzels)+ rules[val].peanuts + 1, i % rules[val].pretzels);
                            return 1 - turn;
                        }
                        else if(turn == 1){
                            return 0;

                        }
                    }
                }
            }

            /* loop  <<, i=rulex*ruley-1, all division by ruleY;*/
            if(rules[val].peanutsOperator=='<' && rules[val].pretzelsOperator=='<'){
                for(int i=rules[val].peanuts*rules[val].pretzels-1; i>=0;i--){
                    if(calc(peanutsLeft-(i/rules[val].pretzels),pretzelsLeft-i%rules[val].pretzels,turn)==1){
                        winningMove = new Move(i/rules[val].pretzels, i%rules[val].pretzels);
                        return 1-turn;
                    }
                    else if(turn ==1){
                        return 0;
                    }
                }
            }


            /* loop  >>, i=(totalx-rulex)*(totaly-ruley)-1, all division by ruleY, calc+ is rulesx +1;*/

            /*System.out.println("x2: "+ x2 +" y2: "+ y2);
              for(int i=(x2-rules[val].peanuts)*rules[val].pretzels-1; i>=0;i--){
              if(calc(x2-((i/rules[val].pretzels)+rules[val].peanuts+1),y2-i%rules[val.pretzels]+rules[val].pretzels+1,turn)==1){
              result[0] = (i/rules[val].pretzels)+rules[val].peanuts+1;
              result[1] = i%rules[val].pretzels+rules[val].pretzels+1;
              return 1-turn;
              }
              }*/

            // IF RULE X IS < VALUE AND RULE Y = VALUE
            /* for(int i=rulex; i<=0;i--){
               if(calc(peanutsLeft-i, pretzelsLeft-ruley) ==0{
               result[0] = i
               result[1] = ruley
               return 1-turn;
               }

               }*/

            //if rule x > value and rule y = value
            /* for(int i=(totalx - (rulex+1)); i<=0;i--){
               if(calc(peanutsLeft-i+rulex+1, pretzelsLeft-ruley) ==0{
               result[0] = 1+rulex+1;
               result[1] = i%ruley+rulex+1;
               return 1-turn;
               }

               }*/
           }
            if(peanutsLeft >= 1){
                if(calc(peanutsLeft - 1, pretzelsLeft, turn) == 1){
                    winningMove = new Move(1, 0);
                    return 1 - turn;
                }
            }
            if(pretzelsLeft >= 1){
                if(calc(peanutsLeft, pretzelsLeft - 1, turn) == 1){
                    winningMove = new Move(0, 1);
                    return 1 - turn;
                }
            }
            return 0;
        }
    }
