
import java.util.*;
import java.lang.Math;

/**
 *This program will take up to 25 inputs numbers, followed by a final input number
 *and a character. The program ill take the 25 numbers and try all equations
 *consisting of addition and multiplication and try to form the final input from it.
 *the program executes and checks these formed equations in a method determined by
 *the input character, left to right maths or normal maths.
 *
 **/
public class Arith{
    
    /**
     * main method which preforms all the needed tasks in this program as specified
     *above in the class description.
     *@arg args, console args if supplied, we do not use them in this example.
     **/
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        ArrayList<Long> inputs = new ArrayList<Long>();
        ArrayList<Character> used = new ArrayList<Character>();
        ArrayList<Long> Results = new ArrayList<Long>();
        long total=0;
        int found =0;
        String eq;
        String output="";
        String input;
        ArrayList<String> ss = new ArrayList<String>();
        ArrayList<Long> halfTot = new ArrayList<Long>();
        String currPattern="";
        
        //reading in inputs and keeping the porgram in a loop until finished
        //taking inputs.
        while(s.hasNextLine()){
            input = s.nextLine();
            String[] splittedIn = input.split(" ");
            input = s.nextLine();
            inputs.clear();
            for(String x: splittedIn){
                //System.out.println(x);
                inputs.add(Long.valueOf(x));
            }
            
            /*gets all values ready to maths to be done, including
              splitting the method char from the final number*/
            total = inputs.get(0);
            String[] splittedRes = input.split(" ");
            Double preMax =  new Double(Math.pow(2,inputs.size()-1));
            int max = preMax.intValue();
            Double preHalf = new Double(Math.pow(2,4));
            int Half = preHalf.intValue();
            found =0;
            
            
            // if Left to right maths, do the following.
            if(splittedRes[1].charAt(0) == 'L'){
                
                /*outer loop determines the combination being attempted.
                  this is determined in a fashion similar to binary via if statements*/
                for(int i =0; i<max&& found ==0;i++){
                    total = inputs.get(0);
                    output = "L "+total;
                    currPattern="";
                    if(i<preHalf){
                        /* inner loop goes through each gap in numbers and gives it an operation*/
                        for(int x=0; x<(inputs.size()-1)/2;x++){
                            String temp =ss.get(x);
                            Double preInfo =  new Double(Math.pow(2,x));
                            int info = preInfo.intValue();
                            /// if statements determining operation.
                            if(x==0 && i%2==0){
                                currPattern+="+";
                                output +=" + " +inputs.get(x+1);   
                                total+= inputs.get(x+1);
                            }
                            else if((i/info)%2==0){
                                currPattern+="+";
                                output +=" + " +inputs.get(x+1);
                                total+= inputs.get(x+1);
                            }
                            else if((i/info)%2==1){
                                currPattern+="*";
                                output +=" * " +inputs.get(x+1);
                                total*= inputs.get(x+1);
                            }
                            else{
                                currPattern+="*";
                                output +=" * " +inputs.get(x+1);
                                total*= inputs.get(x+1);
                            }
                            // System.out.println(output);
                        }
                        ss.add(currPattern);
                        halfTot.add(total);
                    }else{
                        
                        
                    }
                    /* if total is found output the equation if not print impossible.*/
                    if(total == Integer.valueOf(splittedRes[0])){
                        System.out.println(output);
                                found =1;
                    }
                }
                if(found ==0){
                    System.out.println(" L impossible");
                }
            }
            
            
            else if(splittedRes[1].charAt(0) == '?'){
		ArrayList<Long> parts = new ArrayList<Long>();
               	
                String temp = "";
                
                
                /*outter loop determines the combination being attempted in
                  a binary fashion via if statements*/
                for(int y=0;y<5;y++){
                    parts.addAll(inputs.subList(y*5,(y*5)+5));
                    for(int i =0; i<Half &&found==0 ;i++){
                        total = inputs.get(y*5);
                        used.clear();
                        output ="N " + total;
                        /* inner loop goes through each gap and gives it an
                           operation*/
                        for(int x=0; x<parts.size()-1;x++){
                            temp = ss.get(x);
                            Double preInfo =  new Double(Math.pow(2,x));
                            int info = preInfo.intValue();
                            /* rather tahn prefrom the operations dirrectly, a list
                               of operations in order is made to be read from at
                               the time of execution*/
                            if(x==0 && i%2==0){
                                temp+="+";
                                used.add('+');
                            }
                            else if((i/info)%2==0){
                                temp += "+";
                                used.add('+');
                            }
                            else if((i/info)%2==1){
                                temp+="*";
                                used.add('*');
                            }
                            else{
                                temp+="*";
                                used.add('*');
                            }
                            
                        }
                        /*inputs are saved as the execution rewrites them and
                          then they can restored for the next combination to
                          be tried*/
                        Results.clear();
                        Results.addAll(inputs);
                        /*the execution of the equation, starts by multiplying
                          first and removing the multiplied numbers and opertaion,
                          replacing it with the result in order to do normal maths*/
                        while(used.contains('*')){
                            parts.set(used.indexOf('*'),parts.get(used.indexOf('*')) * parts.get(used.indexOf('*')+1));
                            parts.remove(used.indexOf('*')+1);
                            used.remove(used.indexOf('*'));
                        }
                        total = parts.get(0);
                        for(int x=y*5; x<(y*5)+used.size();x++){
                            total += parts.get(x+1);
                        }
                        /* if total is found print the equation, if not print impossible*/
                        halfTot.add(total);
			temp+=1;
			ss.add(temp);
                        parts.clear();
                        inputs.clear();
                        inputs.addAll(Results);
                    }
		}
		int q =0;
		for(int i=0;i<max;i++){
                    used.clear();
		    q =0;
                    output ="N " + total;
                    
                    /* inner loop goes through each gap and gives it an
                       operation*/
                    for(int x=0; x<inputs.size()-1;x++){
			q++;
                        temp = "";
                        Double preInfo =  new Double(Math.pow(2,x));
                        int info = preInfo.intValue();
                        /* rather tahn prefrom the operations dirrectly, a list
                           of operations in order is made to be read from at
                           the time of execution*/
                        if(x==0 && i%2==0){
                            temp+="+";
			    if((i+1)%5==0&& i!=0){
                            	used.add('+');
				q--;
			    }
                        }
                        else if((i/info)%2==0){
                            temp += "+";
                            if((i+1)%5==0&& i!=0){
                            	used.add('+');
				q--;
			    }
                        }
                        else if((i/info)%2==1){
                            temp+="*";
                            if((i+1)%5==0&& i!=0){
                            	used.add('*');
				q--;
			    }
                        }
                        else{
                            temp+="*";
                            if((i+1)%5==0&& i!=0){
                            	used.add('*');
				q--;
			    }
                        }
			if(q!=0 && q%4==0){
                            temp = temp+q/4;
                            parts.add(halfTot.get(ss.indexOf(temp)));
                            ss.remove(temp);
			}
                        
                    }
                    /*inputs are saved as the execution rewrites them and
                      then they can restored for the next combination to
                      be tried*/
                    Results.clear();
                    Results.addAll(inputs);
                    /*the execution of the equation, starts by multiplying
                      first and removing the multiplied numbers and opertaion,
                      replacing it with the result in order to do normal maths*/
                    while(used.contains('*')){
                        parts.set(used.indexOf('*'),parts.get(used.indexOf('*')) * parts.get(used.indexOf('*')+1));
                        parts.remove(used.indexOf('*')+1);
                        used.remove(used.indexOf('*'));
                    }
                    total = parts.get(0);
                    for(int x=0; x<used.size();x++){
                        total += parts.get(x+1);
                    }
                    /* if total is found print the equation, if not print impossible*/
                    
                    inputs.clear();
                    inputs.addAll(Results);
                    System.out.println(total);
                }
                
                
            }	
            
            
            
            //if normal maths do the following.
            else{
                /*outter loop determines the combination being attempted in
                  a binary fashion via if statements*/
                for(int i =0; i<max &&found==0 ;i++){
                    total = inputs.get(0);
                    used.clear();
                    output ="N " + total;
                    /* inner loop goes through each gap and gives it an
                       operation*/
                    for(int x=0; x<inputs.size()-1;x++){
                        
                        Double preInfo =  new Double(Math.pow(2,x));
                        int info = preInfo.intValue();
                        /* rather tahn prefrom the operations dirrectly, a list
                           of operations in order is made to be read from at
                           the time of execution*/
                        if(x==0 && i%2==0){
                            output +=" + " +inputs.get(x+1);
                            used.add('+');
                        }
                        else if((i/info)%2==0){
                            output +=" + " +inputs.get(x+1);
                            used.add('+');
                        }
                        else if((i/info)%2==1){
                            output +=" * " +inputs.get(x+1);
                            used.add('*');
                        }
                        else{
                            output +=" * " +inputs.get(x+1);
                            used.add('*');
                        }
                        
                    }
                    /*inputs are saved as the execution rewrites them and
                      then they can restored for the next combination to
                      be tried*/
                    Results.clear();
                    Results.addAll(inputs);
                    /*the execution of the equation, starts by multiplying
                      first and removing the multiplied numbers and opertaion,
                      replacing it with the result in order to do normal maths*/
                    while(used.contains('*')){
                        inputs.set(used.indexOf('*'), inputs.get(used.indexOf('*')) * inputs.get(used.indexOf('*')+1));
                        inputs.remove(used.indexOf('*')+1);
                        used.remove(used.indexOf('*'));
                    }
                    total = inputs.get(0);
                    for(int x=0; x<used.size();x++){
                        total += inputs.get(x+1);
                    }
                    /* if total is found print the equation, if not print impossible*/
                    if(total == Integer.valueOf(splittedRes[0])){
                        System.out.println(output);
                        found=1;
                    }
                    inputs.clear();
                    inputs.addAll(Results);
                }
                if(found==0){
                    System.out.println(" N impossible");
                }
            }
        }
        
    }
}

