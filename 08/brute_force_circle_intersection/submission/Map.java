package etude08;

import java.util.*;

public class Map {
    private static final int K = 12;
    private int dp = 4;
    private Point[] points;
    private final int NUMBER_OF_POINTS;
    private ArrayList<Double> radiiToTry;
    ArrayList<Point> deepestCircles;
    HashMap<String, Double> distances;

    public Map(ArrayList<String> points) {
        Scanner scanner;
        NUMBER_OF_POINTS = points.size();
        this.points = new Point[NUMBER_OF_POINTS];
        int i = 0;
        for(String input : points) {
            scanner = new Scanner(input);
            this.points[i] = new Point(scanner.nextDouble(), scanner.nextDouble(), i++);
        }
        radiiToTry = new ArrayList<Double>();
        deepestCircles = new ArrayList<Point>();

        distances = new HashMap<String, Double>();

        for(i = 0; i < NUMBER_OF_POINTS; i++) {
            this.points[i].addNeighbour(i, 0);
            for(int j = i + 1; j < NUMBER_OF_POINTS; j++) {
                double d = this.points[i].distance(this.points[j]);
                distances.put(i + "," + j, d);
                this.points[i].addNeighbour(j, d);
                this.points[j].addNeighbour(i, d);
            }
        }
    }

    public double getMaxRadius() {
        double maxD = Collections.max(distances.values());
        if(maxD == 0) return 0;
        if(maxD > 500) dp = 2;

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

        double r = maxD * 0.6;

        while(r > 0) {
            radiiToTry.add(r);
            r -= Math.pow(10, -dp);
        }

        double start = findDepth(0, radiiToTry.size() - 1, K);
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
            if(maxDepth <= K - 1) return round(r, dp);
            r -= Math.pow(10, -dp);
        }
        return 0;
    }

    public double findDepth(int start, int end, int depth) {
        int mid = (start + end) / 2;
        if(start == end) return  radiiToTry.get(mid);
        double maxDepth = 0;
        for(Point point : deepestCircles) {
            Circle check = new Circle(point, radiiToTry.get(mid));
            double d = check.depth(points);
            if(d > maxDepth) {
                maxDepth = (int) d;
            }
        }

        if(maxDepth == depth) return radiiToTry.get(mid);
        if(maxDepth < depth) return findDepth(start, mid - 1, depth);
        return findDepth(mid + 1, end, depth);
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
        System.out.println(map.round(map.getMaxRadius(), 2));
    }


}
