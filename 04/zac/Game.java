
package etude04;
import java.util.*;
/**
 * Game.java
 * COSC326 Etude 4
 * Peanuts and Pretzels
 *
 * @author Rhianne Price, Zac Gardner, 
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
        }

    }

    public Game(String game) {
<<<<<<< HEAD:04/zac/Game.java
<<<<<<< HEAD:04/zac/Game.java

=======
>>>>>>> bug testing has been done, multiple inputs owrking, more testing and comments:zacs changes/04/rhianne/Game.java
        int rulesCount = game.split(System.getProperty("line.separator")).length-1;
=======
        int rulesCount=game.split(System.getProperty("line.separator"))
            .length-1;
>>>>>>> alot of comment and refactoring:zacs changes/04/rhianne/Game.java
        Scanner scanner = new Scanner(game);

        PEANUTS = scanner.nextInt();
        PRETZELS = scanner.nextInt();

        rules = new Move[rulesCount];
<<<<<<< HEAD:04/zac/Game.java

        /* It is always permissable to take exactly one of either. */

        rules[0] = new Move("=1 =0");
        rules[1] = new Move("=0 =1");

=======
        
>>>>>>> alot of comment and refactoring:zacs changes/04/rhianne/Game.java
        scanner.nextLine();
        for(int i = 0; i < rulesCount; i++) {
            rules[i] = new Move(scanner.nextLine().trim());
        }
        result[0] =0;
        result[1] =0;
        preCalc(PEANUTS, PRETZELS, rules);
            System.out.println(result[0] + " " + result[1]);
    }

    private Move bestMove(int peanutsLeft, int pretzelsLeft) {
        return YOU_LOSE;
    }

    /**
     * preCalc preforms and need modifications is inputted rules
     * to allow them to function correctly in the calc method.
     *@arg x2 represents the total number of pretzels
     *@arg y2 represents the total number of peanuts
     *@arg rules contains the entire list of rules for this one input
     **/
    public static void preCalc(int x2, int y2, Move[] rules){

        for(int val = 0; val < rules.length; val++){
<<<<<<< HEAD:04/zac/Game.java

            if(rules[val].peanutsOperator=='<' && rules[val].pretzelsOperator=='>'){
=======
            
            if(rules[val].peanutsOperator=='<'
               && rules[val].pretzelsOperator=='>'){
>>>>>>> alot of comment and refactoring:zacs changes/04/rhianne/Game.java
                char temp1 = rules[val].peanutsOperator;
                int temp2 = rules[val].peanuts;
                rules[val].peanutsOperator = rules[val].pretzelsOperator;
                rules[val].pretzelsOperator = temp1;
                rules[val].peanuts = rules[val].pretzels;
                rules[val].pretzels = temp2;
            }
            if(rules[val].peanutsOperator=='='
               && rules[val].pretzelsOperator!='='){
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

    /**
     * calc preforms various if statemnets to attempt
     * every possible answer until the first correct one is found.
     *@arg x2 represents the total number of pretzels at that instance
     *@arg y2 represents the total number of peanuts at that instance
     *@arg rules contains the entire list of rules for this one input
     *@arg turn keeps track of whose turn it is
     *@arg turnNo keeps track of the number of turns that have passed.
     **/
    public static int calc(int x2, int y2, Move[] rules,  int turn, int turnNo){
        // @param turn is always 1 to begin with, we start on the players turn.
        // int test2= test;
        int altx;
        int alty;
        if(x2==0 &&y2==0){
            return 1 - turn;
        }

        else if(turn ==1){
            turn =0;
        }
        else{
            turn =1;
        }
<<<<<<< HEAD:04/zac/Game.java
        for(int val = 0; val < rules.length; val++){
<<<<<<< HEAD:04/zac/Game.java

                    if(x2-((i/rules[val].pretzels)+rules[val].peanuts+1)>=0 && y2-i%rules[val].pretzels>=0){;
                        if(calc(x2-((i/rules[val].pretzels)+rules[val].peanuts+1),y2-i%rules[val].pretzels,rules,turn)==1){
                            result[0] = (i/rules[val].pretzels)+rules[val].peanuts+1;
                            result[1] = i%rules[val].pretzels;
                            return 1-turn;
=======
=======
        for(int c = 0; c < rules.length; c++){
>>>>>>> alot of comment and refactoring:zacs changes/04/rhianne/Game.java
            /* loop  ><, i=(totalx-rulex)*ruley-1, all division by ruleY, calc+ is rulesx +1;*/
            
            if(rules[c].peanutsOperator =='>'
               && rules[c].pretzelsOperator=='<'){
                
                for(int i=((x2-rules[c].peanuts)*rules[c].pretzels)-1; i>=0;i--){
                    altx = (i/rules[c].pretzels)+rules[c].peanuts+1;
                    alty=i%rules[c].pretzels;
                    
                    if(x2-altx>=0 && y2-alty>=0){
                        if(calc(x2-altx,y2-alty,rules,turn,turnNo+1)==1){
                            if(turnNo==0){
                                result[0] =altx;
                                result[1] =alty;
                                return 1;
                            }
<<<<<<< HEAD:04/zac/Game.java
>>>>>>> bug testing has been done, multiple inputs owrking, more testing and comments:zacs changes/04/rhianne/Game.java
                        }

                        else if(turn ==1){
                            return 0;

=======
                        
                        
                            else if(turn ==1){
                                return 0;
                            
                            }
>>>>>>> alot of comment and refactoring:zacs changes/04/rhianne/Game.java
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
            else if(rules[c].peanutsOperator=='>'
                    &&rules[c].pretzelsOperator=='>'){
               
                // if(x2-rules[val].peanuts> y2-rules[val].pretzels){
                for(int i=rules[c].peanuts*rules[c].pretzels-1; i>=0;i--){
                    altx=i%rules[c].pretzels +rules[c].peanuts+1;
                    alty= i/rules[c].pretzels + rules[c].pretzels+1;
                    
                    if(x2-altx>=0 && y2-alty>=0){
                        if(calc(x2-altx,y2-alty,rules,turn,turnNo+1)==1){
                           
                            if(turnNo==0){
                                result[0] = altx;
                                result[1] = alty;
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
=======
            else if(rules[c].peanutsOperator=='<'
                    && rules[c].pretzelsOperator=='<'){
                for(int i=(rules[c].peanuts*rules[c].pretzels)-1; i>=0;i--){
                    altx= (i/rules[c].pretzels);
                    alty= (i%rules[c].pretzels);
                    
                    if(x2-altx >=0 && y2-alty>=0){
                        if(calc(x2-altx,y2-alty,rules,turn,turnNo+1)==1){
                            if(turnNo==0){
                                result[0] = altx;
                                result[1] = alty;
                                return 1;
>>>>>>> alot of comment and refactoring:zacs changes/04/rhianne/Game.java
                            }
                        }
                        else if(turn ==1){
                            return 0;
                        }
                       
                    }
                }
            }
            
            // IF RULE X IS < VALUE AND RULE Y = VALUE
            else if(rules[c].peanutsOperator=='<'
                    && rules[c].pretzelsOperator=='='){
                
                for(int i=rules[c].peanuts-1; i<0;i--){
                    alty =rules[c].pretzels;
                    if(x2-i >=0 &&alty>=0){
                        if(calc(x2-i, y2-alty,rules,turn,turnNo+1) ==1){
                            if(turnNo==0){
                                result[0] = i;
                                result[1] = alty;
                                return 1;
                                
                            }
                        }else if(turn ==1){
                            return 0;
                        }
                        
                    }
                }
            }
            
            //if rule x > value and rule y = value
            else if(rules[c].peanutsOperator=='>'
                    && rules[c].pretzelsOperator=='='){

                
                for(int i=(x2 - (rules[c].peanuts+1)); i<=0;i--){
                    if(x2-(i+rules[c].peanuts+1)>=0 && y2-rules[c].pretzels>=0){
                        if(calc(x2-(i+rules[c].peanuts+1), y2-rules[c].pretzels,rules,turn,turnNo+1) ==1){
                            if(turnNo==0){
                                result[0] = i+rules[c].peanuts+1;
                                result[1] = rules[c].pretzels;
                            }
                            if(turn==0){
                                return 1;
                            }
                        }
                        else if(turn==1){
                            return 0;
                        }
                    }
                    
                }
            }
           
<<<<<<< HEAD:04/zac/Game.java
            else if(rules[val].peanutsOperator=='=' && rules[val].pretzelsOperator =='='){
>>>>>>> bug testing has been done, multiple inputs owrking, more testing and comments:zacs changes/04/rhianne/Game.java
                if(x2-rules[val].peanuts>=0 && y2-rules[val].pretzels>=0){
                    if(calc(x2-rules[val].peanuts, y2-rules[val].pretzels,rules,turn,test+1)==1){
                        if(test==0){
=======
            else if(rules[c].peanutsOperator=='='
                    && rules[c].pretzelsOperator =='='){
                if(x2-rules[c].peanuts>=0 && y2-rules[c].pretzels>=0){
                    if(calc(x2-rules[c].peanuts, y2-rules[c].pretzels,rules,turn,turnNo+1)==1){
                        if(turnNo==0){
>>>>>>> alot of comment and refactoring:zacs changes/04/rhianne/Game.java
                            
                            result[0]=rules[c].peanuts;
                            result[1] = rules[c].pretzels;
                            return 1;
                        }
                    }
                    else if(turn==1){
                        return 0;
                    }
                    

                }
            }
        }
        /* it is always permissbale to take one of either group,
           hence the below being in no loop.*/
        if(x2>=1){
            if(calc(x2-1,y2,rules,turn,turnNo+1)==1){
                if(turnNo==0){
                    result[0] =1;
                    result[1]=0;
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
            if(calc(x2,y2-1,rules,turn,turnNo+1)==1){
                if(turnNo==0){
                    result[0] =0;
                    result[1]=1;
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
