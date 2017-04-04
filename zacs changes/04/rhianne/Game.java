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
        System.out.println("****************************");
        Scanner scanner = new Scanner(System.in).useDelimiter("\n\n");
        while(scanner.hasNext()) {
            Game game = new Game(scanner.next().trim());
            //System.out.println(game.bestMove(game.PEANUTS, game.PRETZELS).asSolution());
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
        calc(PEANUTS, PRETZELS, rules, 1);
        System.out.println(result[0] + " " + result[1]);
    }
    
    private Move bestMove(int peanutsLeft, int pretzelsLeft) {
        return YOU_LOSE;
    }
    public static int calc(int x2, int y2, Move[] rules,  int turn){
        System.out.println("x: " + x2 + " y2: " + y2);
        // @param turn is always 1
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
        
            /* loop  ><, i=(totalx-rulex)*ruley-1, all division by ruleY, calc+ is rulesx +1;*/

            if(rules[val].peanutsOperator =='>'&& rules[val].pretzelsOperator=='<'){
                for(int i=((x2-rules[val].peanuts)*rules[val].pretzels)-1; i>=0;i--){
                    if(x2-((i/rules[val].pretzels)+rules[val].peanuts+1)>=0 && y2-i%rules[val].pretzels>=0){;  
                        if(calc(x2-((i/rules[val].pretzels)+rules[val].peanuts+1),y2-i%rules[val].pretzels,rules,turn)==1){
                            result[0] = (i/rules[val].pretzels)+rules[val].peanuts+1;
                            result[1] = i%rules[val].pretzels;
                            return 1-turn;
                        }
                        
                        else if(turn ==1){
                            return 0;
                            
                        }
                    }
                }
            }
            
            
            /* loop  <<, i=rulex*ruley-1, all division by ruleY;*/
            if(rules[val].peanutsOperator=='>' && rules[val].pretzelsOperator=='>'){
               
                // if(x2-rules[val].peanuts> y2-rules[val].pretzels){
                    for(int i=rules[val].peanuts*rules[val].pretzels-1; i>=0;i--){
                        if(x2-(i%rules[val].pretzels)>=0 && y2-(i/rules[val].pretzels)>=0){
                            if(calc(x2-(i%rules[val].pretzels +rules[val].peanuts+1),y2-(i/rules[val].pretzels + rules[val].pretzels +1),rules,turn)==1){
                                result[0] = i%rules[val].pretzels +rules[val].peanuts+1;
                                result[1] = i/rules[val].pretzels + rules[val].pretzels+1; 
                                return 1-turn;
                            }
                            else if(turn ==1){
                                return 0;
                            }
                        }
                    }

                    
            }
            
            
            /* loop  >>, i=(totalx-rulex)*(totaly-ruley)-1, all division by ruleY, calc+ is rulesx +1;*/
            if(rules[val].peanutsOperator=='<' && rules[val].pretzelsOperator=='<'){
                for(int i=(rules[val].peanuts*rules[val].pretzels)-1; i>=0;i--){
                    if(x2-(i/rules[val].pretzels) >=0 && y2-(i%rules[val].pretzels)>=0){
                        
                        System.out.println("xx2 " +((i/rules[val].pretzels)) + " yy " +((i%rules[val].pretzels)));
                        if(calc(x2-((i/rules[val].pretzels)),y2-((i%rules[val].pretzels)),rules,turn)==1){
                            result[0] = (i/rules[val].pretzels);
                            result[1] = (i%rules[val].pretzels);
                            return 1-turn;
                        }
                        else if(turn ==0){
                            return 0;
                        }
                    }
                }
            }
            
            // IF RULE X IS < VALUE AND RULE Y = VALUE
            if(rules[val].peanutsOperator=='<' && rules[val].pretzelsOperator=='='){
                
                for(int i=rules[val].peanuts-1; i<0;i--){
                    if(x2-i >=0 && rules[val].pretzels>=0){
                        if(calc(x2-i, y2-rules[val].pretzels,rules,turn) ==0){
                            result[0] = i;
                            result[1] = rules[val].pretzels;
                            return 1-turn;
                        }
                        else if(turn ==1){
                            return 0;
                        }
                        
                    }
                }
            }
            
            //if rule x > value and rule y = value
            if(rules[val].peanutsOperator=='>' && rules[val].pretzelsOperator=='='){

                
                for(int i=(x2 - (rules[val].peanuts+1)); i<=0;i--){
                    if(x2-(i+rules[val].peanuts+1)>=0 && y2-rules[val].pretzels>=0){
                        if(calc(x2-(i+rules[val].peanuts+1), y2-rules[val].pretzels,rules,turn) ==0){
                            result[0] = i+rules[val].peanuts+1;
                            result[1] = rules[val].pretzels;
                            return 1-turn;
                        }
                        else if(turn==1){
                            return 0;
                        }
                    }
                    
                }
            }
        }
            if(x2>=1){
                if(calc(x2-1,y2,rules,turn)==1){
                    result[0] =1;
                    result[1]=0;
                    return 1-turn;
                }
            }
            if(y2>=1){
                if(calc(x2,y2-1,rules,turn)==1){
                    result[0] =0;
                    result[1]=1;
                    return 1-turn;
                }
            }
            
        
        return 0;
    }
        
}

