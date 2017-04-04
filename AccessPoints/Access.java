/*******************************************************************************
Authors: Caleb Mitchell, Rhianne Price, Erina Jeffery, Zac Gardner
Etude 8: Access Points
*******************************************************************************/

import java.util.*;



public class Access{

    public static final ArrayList<String> coords = new ArrayList<String>();
    
    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            if(line.charAt(0) != 'A'){
                coords.add(line);
            }
        }
    }
    
    /* getX takes a string of 2 coordinates in format "east north"
       and returns the east (or x) value */
    public static double getX(String lineIn){
        String[] lineArray = lineIn.split(" ");
        return Double.parseDouble(lineArray[0]);
    }
    
    /* getY takes a string of 2 coordinates in format "east north"
       and returns the north (or y) value */
    public static double getY(String lineIn){
        String[] lineArray = lineIn.split(" ");
        return Double.parseDouble(lineArray[1]);
    }
    
}
