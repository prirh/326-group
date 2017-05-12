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
        int rulesCount=game.split(System.getProperty("line.separator"))
            .length-1;
        Scanner scanner = new Scanner(game);
        
        PEANUTS = scanner.nextInt();
        PRETZELS = scanner.nextInt();
        
        rules = new Move[rulesCount];
        if(scanner.hasNext()){
            
            scanner.nextLine();
            for(int i = 0; i < rulesCount; i++) {
                rules[i] = new Move(scanner.nextLine().trim());
            }
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
                        if(calc(x2-altx,y2-alty,rules,turn,turnNo+1)==1){
                            if(turnNo==0){
                                result[0] =altx;
                                result[1] =alty;
                                return 1;
                            }else if(turn ==0){
                                return 1;
                            }
                        }
                        
                        
                        else if(turn ==1){
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
                        if(calc(x2-altx,y2-alty,rules,turn,turnNo+1)==1){
                           
                            if(turnNo==0){
                                result[0] = altx;
                                result[1] = alty;
                                return 1;
                            }
                            else if(turn ==0){
                                return 1;
                            }
                        }
                        else if(turn ==1){
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
                        if(calc(x2-altx,y2-alty,rules,turn,turnNo+1)==1){
                            if(turnNo==0){
                                result[0] = altx;
                                result[1] = alty;
                                return 1;
                            }
                            else if(turn ==0){
                                return 1;
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
                for(int i=rules[c].peanuts-1; i>0;i--){
                    alty =rules[c].pretzels;
                    altx =i;

                    if(rules[c].reversed ==1){
                        revTemp =altx;
                            altx = alty;
                            alty = revTemp;
                    }
                    
                    if(x2-i >=0 &&y2-alty>=0){
                        if(calc(x2-i, y2-alty,rules,turn,turnNo+1) ==1){
                            if(turnNo==0){
                                result[0] = altx;
                                result[1] = alty;
                                return 1;
                                
                            }
                            else if(turn ==0){
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

                
                for(int i=(x2 - (rules[c].peanuts+1)); i>=0;i--){
                    alty = rules[c].pretzels;;
                    altx =i+rules[c].peanuts+1;;

                    if(rules[c].reversed ==1){
                        revTemp =altx;
                            altx = alty;
                            alty = revTemp;
                    }
                    
                    if(x2-altx>=0 && y2-alty>=0){
                        if(calc(x2-altx, y2-alty,rules,turn,turnNo+1) ==1){
                            if(turnNo==0){
                                result[0] = altx;
                                result[1] = alty;
                                return 1;
                            }
                            else if(turn ==0){
                                return 1;
                            }
                        }
                        else if(turn==1){
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
                    if(calc(x2-altx, y2-alty,rules,turn,turnNo+1)==1){
                        if(turnNo==0){
                            
                            result[0]=altx;
                            result[1] = alty;
                            return 1;
                        }else if(turn ==0){
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
                else if(turn ==0){
                    return 1;
                }
            }
            else if(turn ==1){
                return 0;
            }
        }
        if(y2>=1){
            if(calc(x2,y2-1,rules,turn,turnNo+1)==1){
                if(turnNo==0){
                    result[0] =0;
                    result[1]=1;
                    return 1;
                }else if(turn ==0){
                    return 1;
                }
            }
            else if(turn ==1){
                return 0;
            }
        }
        if(turn ==1){
            return 1;
        }
        return 0;
    }
}
