
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
    static int[] result ={0,0};

    private final int PEANUTS, PRETZELS;
    private Move[] rules;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n\n");
        while(scanner.hasNext()) {
            Game game = new Game(scanner.next().trim());
            //System.out.println(game.bestMove(game.PEANUTS, game.PRETZELS).asSolution());
        }

    }

    public Game(String game) {
<<<<<<< HEAD:04/zac/Game.java

=======
>>>>>>> bug testing has been done, multiple inputs owrking, more testing and comments:zacs changes/04/rhianne/Game.java
        int rulesCount = game.split(System.getProperty("line.separator")).length-1;
        Scanner scanner = new Scanner(game);

        PEANUTS = scanner.nextInt();
        PRETZELS = scanner.nextInt();

        rules = new Move[rulesCount];

        /* It is always permissable to take exactly one of either. */

        rules[0] = new Move("=1 =0");
        rules[1] = new Move("=0 =1");

        scanner.nextLine();
        for(int i = 0; i < rulesCount; i++) {
            rules[i] = new Move(scanner.nextLine().trim());
        }
        result[0] =0;
        result[1] =0;
        preCalc(PEANUTS, PRETZELS, rules);
            System.out.println(result[0] + " " + result[1]);
        /*}else{
            System.out.println("0 0");
            }*/
    }

    private Move bestMove(int peanutsLeft, int pretzelsLeft) {
        return YOU_LOSE;
    }

    public static void preCalc(int x2, int y2, Move[] rules){

        for(int val = 0; val < rules.length; val++){

            if(rules[val].peanutsOperator=='<' && rules[val].pretzelsOperator=='>'){
                char temp1 = rules[val].peanutsOperator;
                int temp2 = rules[val].peanuts;
                rules[val].peanutsOperator = rules[val].pretzelsOperator;
                rules[val].pretzelsOperator = temp1;
                rules[val].peanuts = rules[val].pretzels;
                rules[val].pretzels = temp2;
            }
            if(rules[val].peanutsOperator=='=' && rules[val].pretzelsOperator!='='){
                char temp1 = rules[val].peanutsOperator;
                int temp2 = rules[val].peanuts;
                rules[val].peanutsOperator = rules[val].pretzelsOperator;
                rules[val].pretzelsOperator = temp1;
                rules[val].peanuts = rules[val].pretzels;
                rules[val].pretzels = temp2;
            }

        }
        calc(x2,y2,rules,1,0);
    }
    public static int calc(int x2, int y2, Move[] rules,  int turn, int test){
        // @param turn is always 1 to begin with, we start on the players turn.
        // int test2= test;
        int test2 =0;
        if(x2==0 &&y2==0){
            return 1 - turn;
        }

        else if(turn ==1){
            turn =0;
        }
        else{
            turn =1;
        }
        for(int val = 0; val < rules.length; val++){
<<<<<<< HEAD:04/zac/Game.java

                    if(x2-((i/rules[val].pretzels)+rules[val].peanuts+1)>=0 && y2-i%rules[val].pretzels>=0){;
                        if(calc(x2-((i/rules[val].pretzels)+rules[val].peanuts+1),y2-i%rules[val].pretzels,rules,turn)==1){
                            result[0] = (i/rules[val].pretzels)+rules[val].peanuts+1;
                            result[1] = i%rules[val].pretzels;
                            return 1-turn;
=======
            /* loop  ><, i=(totalx-rulex)*ruley-1, all division by ruleY, calc+ is rulesx +1;*/
            
            if(rules[val].peanutsOperator =='>'&& rules[val].pretzelsOperator=='<'){
                for(int i=((x2-rules[val].peanuts)*rules[val].pretzels)-1; i>=0;i--){
                    
                    if(x2-((i/rules[val].pretzels)+rules[val].peanuts+1)>=0 && y2-i%rules[val].pretzels>=0){
                        if(calc(x2-((i/rules[val].pretzels)+rules[val].peanuts+1),y2-i%rules[val].pretzels,rules,turn,test+1)==1){
                            if(test==0){
                                result[0] = (i/rules[val].pretzels)+rules[val].peanuts+1;
                                result[1] = i%rules[val].pretzels;
                            }
                            if(turn ==0){
                                return 1;
                            }
>>>>>>> bug testing has been done, multiple inputs owrking, more testing and comments:zacs changes/04/rhianne/Game.java
                        }

                        else if(turn ==1){
                            return 0;

                        }
                    }
                }
            }
<<<<<<< HEAD:04/zac/Game.java




                    if(calc(x2-(i/rules[val].pretzels),y2-i%rules[val].pretzels,rules,turn)==1){
                        result[0] = (i/rules[val].pretzels);
                        result[1] = i%rules[val].pretzels;
                        return 1-turn;
                    }
                    else if(turn ==1){
                        return 0;
                    }

=======
            
            
            /* loop  <<, i=rulex*ruley-1, all division by ruleY;*/
            else if(rules[val].peanutsOperator=='>' && rules[val].pretzelsOperator=='>'){
               
                // if(x2-rules[val].peanuts> y2-rules[val].pretzels){
                for(int i=rules[val].peanuts*rules[val].pretzels-1; i>=0;i--){
                    if(x2-(i%rules[val].pretzels)>=0 && y2-(i/rules[val].pretzels)>=0){
                        if(calc(x2-(i%rules[val].pretzels +rules[val].peanuts+1),y2-(i/rules[val].pretzels + rules[val].pretzels +1),rules,turn,test+1)==1){
                            System.out.println(test);
                            if(test==0){
                                result[0] = i%rules[val].pretzels +rules[val].peanuts+1;
                                result[1] = i/rules[val].pretzels + rules[val].pretzels+1;
                                return 1;
                            }
                        }
                        else if(turn ==1){
                            return 0;
                        }
                    }
                }
>>>>>>> bug testing has been done, multiple inputs owrking, more testing and comments:zacs changes/04/rhianne/Game.java

            }


            /* loop  >>, i=(totalx-rulex)*(totaly-ruley)-1, all division by ruleY, calc+ is rulesx +1;*/
<<<<<<< HEAD:04/zac/Game.java

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
               if(calc(x2-i, y2-ruley) ==0{
               result[0] = i
               result[1] = ruley
               return 1-turn;
               }

               }*/

            //if rule x > value and rule y = value
            /* for(int i=(totalx - (rulex+1)); i<=0;i--){
               if(calc(x2-i+rulex+1, y2-ruley) ==0{
               result[0] = 1+rulex+1;
               result[1] = i%ruley+rulex+1;
               return 1-turn;
               }

               }*/
        }
            if(x2>=1){
                if(calc(x2-1,y2,rules,turn)==1){
                    result[0] =1;
                    result[1]=0;
                    return 1-turn;
                }
                }

             else if(rules[val].peanutsOperator=='=' && rules[val].pretzelsOperator =='='){
=======
            else if(rules[val].peanutsOperator=='<' && rules[val].pretzelsOperator=='<'){
                for(int i=(rules[val].peanuts*rules[val].pretzels)-1; i>=0;i--){
                    if(x2-(i/rules[val].pretzels) >=0 && y2-(i%rules[val].pretzels)>=0){
                        if(calc(x2-((i/rules[val].pretzels)),y2-((i%rules[val].pretzels)),rules,turn,test+1)==1){
                            if(test==0){
                                result[0] = (i/rules[val].pretzels);
                                result[1] = (i%rules[val].pretzels);
                            }
                            return 1;
                        }
                        else if(turn ==1){
                            return 0;
                        }
                       
                    }
                }
            }
            
            // IF RULE X IS < VALUE AND RULE Y = VALUE
            else if(rules[val].peanutsOperator=='<' && rules[val].pretzelsOperator=='='){
                
                for(int i=rules[val].peanuts-1; i<0;i--){
                    if(x2-i >=0 && rules[val].pretzels>=0){
                        if(calc(x2-i, y2-rules[val].pretzels,rules,turn,test+1) ==1){
                            if(test==0){
                                result[0] = i;
                                result[1] = rules[val].pretzels;
                                
                            }
                            return 1;
                        }else if(turn ==1){
                            return 0;
                        }
                        
                    }
                }
            }
            
            //if rule x > value and rule y = value
            else if(rules[val].peanutsOperator=='>' && rules[val].pretzelsOperator=='='){

                
                for(int i=(x2 - (rules[val].peanuts+1)); i<=0;i--){
                    if(x2-(i+rules[val].peanuts+1)>=0 && y2-rules[val].pretzels>=0){
                        if(calc(x2-(i+rules[val].peanuts+1), y2-rules[val].pretzels,rules,turn,test+1) ==1){
                            if(test==0){
                                result[0] = i+rules[val].peanuts+1;
                                result[1] = rules[val].pretzels;
                                return 1;
                            }
                        }
                        else if(turn==1){
                            return 0;
                        }
                    }
                    
                }
            }
           
            else if(rules[val].peanutsOperator=='=' && rules[val].pretzelsOperator =='='){
>>>>>>> bug testing has been done, multiple inputs owrking, more testing and comments:zacs changes/04/rhianne/Game.java
                if(x2-rules[val].peanuts>=0 && y2-rules[val].pretzels>=0){
                    if(calc(x2-rules[val].peanuts, y2-rules[val].pretzels,rules,turn,test+1)==1){
                        if(test==0){
                            
                            result[0]=rules[val].peanuts;
                            result[1] = rules[val].pretzels;
                            return 1;
                        }
                    }
                    else if(turn==1){
                        return 0;
                    }
                    

                }
            }
        }
        if(x2>=1){
            if(calc(x2-1,y2,rules,turn,test+1)==1){
                if(test==0){
                    result[0] =1;
                    result[1]=0;
                        
                }
                if(turn ==0){
                    return 1;
                }
               
                return 0;
            }
<<<<<<< HEAD:04/zac/Game.java

        return 0;
    }
=======
        }
        if(y2>=1){
            if(calc(x2,y2-1,rules,turn,test+1)==1){
                if(test==0){
                    result[0] =0;
                    result[1]=1;
                        
                }
                if(turn ==0){
                    return 1;
                }
                
                return 0;
            }
        }
        if(turn ==1){
            return 1;
        }
        
           
        
        return 0;
    }
}
>>>>>>> bug testing has been done, multiple inputs owrking, more testing and comments:zacs changes/04/rhianne/Game.java

}
