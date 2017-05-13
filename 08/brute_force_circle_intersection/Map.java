package etude08;

import java.util.*;

public class Map {
    private static final int K = 12;
    private final int DP = 4;
    private Point[] points;
    private final int NUMBER_OF_POINTS;
    private ArrayList<Double> radiiToTry;
    ArrayList<Point> deepestCircles;


    public Map(ArrayList<String> points) {
        Scanner scanner;
        NUMBER_OF_POINTS = points.size();
        this.points = new Point[NUMBER_OF_POINTS];
        int i = 0;
        for(String input : points) {
            scanner = new Scanner(input);
            this.points[i++] = new Point(scanner.nextDouble(), scanner.nextDouble());
        }

        radiiToTry = new ArrayList<Double>();
        deepestCircles = new ArrayList<Point>();
    }

    public double getMaxRadius() {
        double maxD = 0;
        for(int i = 0; i < NUMBER_OF_POINTS; i++) {
            for(int j = i + 1; j < NUMBER_OF_POINTS; j++) {
                double d = this.points[i].distance(this.points[j]);
                maxD = d > maxD ? d : maxD;
            }
        }

        int maxDepth = 0;
        for(Point point : points) {
            Circle check = new Circle(point, maxD * 0.6);
            double depth = check.depth(points);
            if(depth >= maxDepth) {
                maxDepth = (int) depth;
            }
        }

        for(Point point : points) {
            Circle check = new Circle(point, maxD * 0.6);
            double depth = check.depth(points);
            if(depth >= maxDepth) {
                deepestCircles.add(point);
            }
        }

        // System.out.println(deepestCircles);

        double r = maxD * 0.6;

        while(r > 0) {
            radiiToTry.add(r);
            r -= Math.pow(10, -DP);
        }

        double start = findDepth(0, radiiToTry.size() - 1, K);

        // System.out.println("starting with: " + start);
        r = start;

        while(r > 0) {
            maxDepth = 0;
            for(Point point : deepestCircles) {
                Circle check = new Circle(point, r);
                double depth = check.depth(points);
                if(depth > maxDepth) {
                    maxDepth = (int) depth;
                }
            }
            if(maxDepth <= K - 1) return round(r, DP);
            r -= Math.pow(10, -DP);
        }
        return 0;
    }

    public double findDepth(int start, int end, int depth) {
        if(start == end) return 0;
        int mid = (start + end) / 2;
        double maxDepth = 0;
        for(Point point : deepestCircles) {
            Circle check = new Circle(point, radiiToTry.get(mid));
            double d = check.depth(points);
            if(d > maxDepth) {
                maxDepth = (int) d;
            }
        }
        if(maxDepth == depth) return radiiToTry.get(mid);
        if(maxDepth < depth) return findDepth(start, mid, depth);
        return findDepth(mid, end, depth);
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
