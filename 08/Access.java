/*******************************************************************************
Authors: Caleb Mitchell, Rhianne Price, Erina Jeffery, Zac Gardner
Etude 8: Access Points
*******************************************************************************/
package etude08;

import java.util.*;

public class Access{
    public static void main(String[]args) {
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        int numberOfPoints = 0;
        public static final ArrayList<String> coords = new ArrayList<String>();
        while(scan.hasNextLine()){
            coords.add(scan.nextLine());
            numberOfPoints++;
        }
        if(numberOfPoints < 12) {
            System.out.println("As big as you like");
        }
        Map map = new Map(coords);

        Circle test = map.centre;

        test.setR(map.getMaxRange() / 2);
        test.addMembers(map.points);

        System.out.println("Circle: " + test.toString());
        System.out.println("encloses " + test.numberOfMembers() + " points");

        System.out.println("checking...");
        map.check(test);

        // test.setR(map.centre.r + 0.000001);
        // test.addMembers(map.points);
        // System.out.println("it now encloses " + test.numberOfMembers() + " points");
    }

    /* getX takes a string of 2 coordinates in format "east north"
    and returns the east (or x) value */
    public static double getX(String lineIn) {
        String[] lineArray = lineIn.split(" ");
        return Double.parseDouble(lineArray[0]);
    }

    /* getY takes a string of 2 coordinates in format "east north"
    and returns the north (or y) value */
    public static double getY(String lineIn) {
        String[] lineArray = lineIn.split(" ");
        return Double.parseDouble(lineArray[1]);
    }

}
