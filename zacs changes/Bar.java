package etude04;


public class Bar{
    int x =100;
    int y=100;
    int tempx =x;
    int tempy =y;
   static int[] functions;
    static int[] result ={0,0};

    
    public static void main(String[] args){
        calc(3,3,1);
        String test = "<3 <2";
        functions = new int[10];
        String[] input = test.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");

        
        for(int i=0; i<input.length;i++){
            System.out.println(input[i]);
        }
        
        System.out.println(result[0] +" and " + result[1]);

    }
    
    public static int calc(int x2, int y2, Move[] rules,  int turn){
        // @param turn is always 1
        if(x2==0 &&y2==0){
            return 1 - turn;
        }
        else if(x2<0 || y2<0){
            return 0;
        }
        
        else if(turn ==1){
            turn =0;
        }
        else{
            turn =1;
        }
        for(int val = 0; val < rule.length(); val++){
            if(rule[val].peanutsOperator == '>'){
                System.out.println("Yes");
            }
        }
        /* loop  ><, i=(totalx-rulex)*ruley-1, all division by ruleY, calc+ is rulesx +1;*/

        /*System.out.println("x2: "+ x2 +" y2: "+ y2);
          for(int i=(totalx-rulex)*ruley-1; i>=0;i--){
            System.out.println((i/ruley)+rulex+1 + " and: "+ i%ruley + "this:" + i);
            if(calc(x2-((i/ruley)+rulex+1),y2-i%ruley,turn)==1){
                result[0] = (i/ruley)+rulex+1;
                result[1] = i%ruley;
                return 1-turn;
            }
            }*/
        
        /* loop  <<, i=rulex*ruley-1, all division by ruleY;*/
        /* for(int i=rulex*ruley-1; i>=0;i--){
            System.out.println((i/ruley) +  " and: "+ i%ruley + "this:" + i);
            if(calc(x2-((i/ruley),y2-i%ruley,turn)==1){
                result[0] = (i/ruley);
                result[1] = i%ruley;
                return 1-turn;
            }
            }*/

        /* loop  >>, i=(totalx-rulex)*(totaly-ruley)-1, all division by ruleY, calc+ is rulesx +1;*/

        /*System.out.println("x2: "+ x2 +" y2: "+ y2);
          for(int i=(totalx-rulex)*ruley-1; i>=0;i--){
          System.out.println((i/ruley)+(ruley+1) + " and: "+ i%ruley+(rulex+1) + "this:" + i);
          if(calc(x2-((i/ruley)+ruley+1),y2-i%ruley+rulex+1,turn)==1){
          result[0] = (i/ruley)+ruley+1;
          result[1] = i%ruley+rulex+1;
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
        
        if(calc(x2-1,y2,turn)==1){
            result[0] =1;
            result[1]=0;
            return 1-turn;
        }
        else if(calc(x2,y2-1,turn)==1){
            result[0] =0;
            result[1]=1;
            return 1-turn;
        }
            return 0;
    }
}
