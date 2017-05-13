package etude08;

import java.util.*;

public class Map {
    private static final int K = 12;
    private static final int DP = 2;
    private Point[] points;
    private final int NUMBER_OF_POINTS;


    public Map(ArrayList<String> points) {
        Scanner scanner;
        NUMBER_OF_POINTS = points.size();
        this.points = new Point[NUMBER_OF_POINTS];
        int i = 0;
        for(String input : points) {
            scanner = new Scanner(input);
            this.points[i++] = new Point(scanner.nextDouble(), scanner.nextDouble());
        }
    }

    public double getMaxRadius() {
        double maxR = 0;
        for(int i = 0; i < NUMBER_OF_POINTS; i++) {
            for(int j = i + 1; j < NUMBER_OF_POINTS; j++) {
                double d = this.points[i].distance(this.points[j]);
                maxR = d > maxR ? d : maxR;
            }
        }
        double r = maxR;
        while(r > 0) {
            int maxDepth = 0;
            for(Point point : points) {
                Circle check = new Circle(point, r);
                double depth = check.depth(points);
                if(depth > maxDepth) {
                    maxDepth = (int) depth;
                }
            }
            if(maxDepth == K - 1) return round(r, DP);
            r -= Math.pow(10, -DP);
        }
        return 0;
    }

    public static double round(double toBeRounded, int dp) {
        double multiplier = Math.pow(10, dp);
        return Math.round(toBeRounded * multiplier) / multiplier;
    }

    public static void main(String[]args) {
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        int numberOfPoints = 0;
        ArrayList<String> coords = new ArrayList<String>();
        while(scan.hasNextLine()){
            coords.add(scan.nextLine());
            numberOfPoints++;
        }
        if(numberOfPoints < 12) {
            System.out.println("As big as you like");
            return;
        }

        Map map = new Map(coords);
        System.out.println(map.getMaxRadius());
    }
}
