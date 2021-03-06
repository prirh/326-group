
import java.util.*;
import java.lang.Math;

/**
 *This program will take up to 25 inputs numbers, followed by a final input number
 *and a character. The program ill take the 25 numbers and try all equations
 *consisting of addition and multiplication and try to form the final input from it.
 *the program executes and checks these formed equations in a method determined by
 *the input character, left to right maths or normal maths.
 *@author Zac Gardner, Caleb Mitchell, Rhianne Price, Erina Jeffery
 **/
public class Arith2{
    static int test=0;
    static long[] nums2;
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
        
        //takes the input of numbers and answer

        while(s.hasNextLine()){
            input = s.nextLine();
            String[] splittedIn = input.split(" ");
            input = s.nextLine();
            inputs.clear();
            for(String x: splittedIn){

                inputs.add(Long.valueOf(x));
            }
            
           
            total = inputs.get(0);
            String[] splittedRes = input.split(" ");
            Double preMax =  new Double(Math.pow(2,inputs.size()-1));
            int max = preMax.intValue();
            Double preHalf = new Double(Math.pow(2,4));
            int Half = preHalf.intValue();
            found =0;
            
            
         
            // all the setup for the recursive solving below
            if(splittedRes[1].charAt(0) == 'N'){
                System.out.println("here3");
                long[] nums = new long[inputs.size()];
                nums2 = new long[inputs.size()];
                for(int i=0;i<inputs.size();i++){
                    nums[i] = inputs.get(i);
                    nums2[i] = inputs.get(i);
                }

                Arrays.sort(nums2);
                    
                if(treeSolv(nums,nums.length-1,nums[0],nums[0],0,0,
                            Integer.valueOf(splittedRes[0]),"")==0){
                    System.out.println("Impossible N");
                }
            }
 	    else if(splittedRes[1].charAt(0) == 'L'){
                System.out.println("here3");
                long[] nums = new long[inputs.size()];
                nums2 = new long[inputs.size()];
                for(int i=0;i<inputs.size();i++){
                    nums[i] = inputs.get(i);
                    nums2[i] = inputs.get(i);
                }

                Arrays.sort(nums2);
                    
                if(treeSolvL(nums,nums.length-1,nums[0],
                            Integer.valueOf(splittedRes[0]),"")==0){
                    System.out.println("Impossible L");
                }
            }

          
            
                    
        }
    }

    /**
     * treeSolv recursivly solvers the normal equations of the input numbers
     *to see if they can reach the answer give, shorcuts are taken where
     *if all input numbers are positive, any further adition or multiplication
     *will only make the total larger, meaning if the total is currently above
     *the answer, it should go back and try a different combination.
     *@arg nums a list of all the input numbers
     *@arg i the current gap in numbers it is up to.
     *@arg prenNum the previous number in the order
     *@arg prePlus the previous total from the last plus operation
     *@arg preTimes the previous total from the last times operation
     *@arg total the current total being worked with
     *@arg answer the answer that is trying to be reached
     *@arg pattern the operations that have been used so far
     **/
    public static int treeSolv(long[] nums,int i, long preNum, 
                               long prePlus, long preTimes, long total,
                               long answer,String pattern){
   
        if(nums2[0]>=1 && i==0 && answer >total && pattern.indexOf('+')==1){
           
	    System.out.println("Impossible N");
            System.exit(0);
        }
        if(total ==answer && i ==0){
            
            System.out.print("N");
	    for(int x=0;x<nums.length-1;x++){
                System.out.print(" "+nums[x]+" "+pattern.charAt(x));
            }
            System.out.println(" "+nums[nums.length-1]);
            return 1;
        }   
        else if(i==0){
           
            return 0;
        }
        long num =nums[nums.length-i];
        if(nums2[0] >=1 && total >answer){
            return 0;
        }
        
        
       
        String pat = pattern+ "+";
        String pat2 = pattern +"*";
        if(preTimes ==0){
            
            if(treeSolv(nums,i-1,0,prePlus-preNum,(num*preNum),(num*preNum)+(prePlus-preNum),answer,pat2)==1){
                return 1;
            }
        }
        else{
            if(treeSolv(nums,i-1,1,0,(num*preTimes)+(prePlus),(preTimes*num)+(prePlus),answer,pat2)==1){
                return 1;
            }
        }
        if(treeSolv(nums,i-1,num,prePlus+num+preTimes,0,prePlus+num+preTimes,answer,pat)==1){
            return 1; 
        }
        
        return 0;
    }

    /**
     * treeSolv recursivly solvers the left to right equations of
     *the input numbers to see if they can reach the answer
     *give, shorcuts are taken where if all input numbers are
     *positive, any further adition or multiplication
     *will only make the total larger, meaning if the total is currently above
     *the answer, it should go back and try a different combination.
     *@arg nums a list of all the input numbers
     *@arg i the current gap in numbers it is up to.
     *@arg total the current total being worked with
     *@arg answer the answer that is trying to be reached
     *@arg pattern the operations that have been used so far
     **/
    public static int treeSolvL(long[] nums,int i, long total, long answer,String pattern){
        
        if(nums2[0]>=1 && i==0 && answer >total && pattern.indexOf('+')==1){
           
            System.out.println("Impossible L");
            System.exit(0);
        }
        if(total ==answer && i ==0){
            
            System.out.print("L");
            for(int x=0;x<nums.length-1;x++){
                System.out.print(" "+nums[x]+" "+pattern.charAt(x));
            }
            System.out.println(" "+nums[nums.length-1]);
            return 1;
        }   
        else if(i==0){
            
            return 0;
        }
        long num =nums[nums.length-i];
        if(nums2[0] >=1 && total >answer){
            
            return 0;
            
           
        }
        
        
        
        String pat = pattern+ "+";
        String pat2 = pattern +"*";
            
            if(treeSolvL(nums,i-1,total*num,answer,pat2)==1){
                return 1;
            }
        
        if(treeSolvL(nums,i-1,total+num,answer,pat)==1){
            return 1; 
        }
        
        return 0;
    }

}

