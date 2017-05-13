package etude08;

import java.util.*;

public class Map {
    private static final int K = 12;
    private int k_;
    private Point[] points;
    private Set<Circle> potentialCircles;
    private final int NUMBER_OF_POINTS;
    private TreeSet<Double> radiiToTry;
    private TreeSet<Double> bestRadii;


    public Map(ArrayList<String> points) {
        Scanner scanner;
        NUMBER_OF_POINTS = points.size();
        this.points = new Point[NUMBER_OF_POINTS];
        this.potentialCircles = new TreeSet<Circle>(new Comparator<Circle>(){
            @Override
            public int compare(Circle c1, Circle c2) {
                if(c1.r > c2.r) return 1;
                if(c1.r < c2.r) return -1;
                else return 0;
            }
        });

        this.radiiToTry = new TreeSet<Double>();
        this.bestRadii = new TreeSet<Double>();

        k_ = K - 2;
        int i = 0;
        for(String input : points) {
            scanner = new Scanner(input);
            this.points[i++] = new Point(scanner.nextDouble(), scanner.nextDouble());
        }

    }

    public Circle getCircle(Point p1, Point p2, Point p3) {
        if(p2.Y == p1.Y && p3.Y == p2.Y) {
            double d12 = p1.distance(p2);
            double d13 = p1.distance(p3);
            double d32 = p3.distance(p2);

            double max = Math.max(p1.X, Math.max(p2.X, p3.X));
            return new Circle(max / 2, k_ + 1);

        }

        if(p2.X == p1.X && p3.X == p2.X) {
            double d12 = p1.distance(p2);
            double d13 = p1.distance(p3);
            double d32 = p3.distance(p2);

            double max = Math.max(p1.Y, Math.max(p2.Y, p3.Y));
            return new Circle(max / 2, k_ + 1);
        }

        double s12 = (p2.Y - p1.Y) / (p2.X - p1.X);
        double s23 = (p3.Y - p2.Y) / (p3.X - p2.X);

        double x = ((s12 * s23) * (p1.Y - p3.Y) + s23 * (p1.X + p2.X) - s12 * (p2.X + p3.X)) / (2 * (s23 - s12));
        double y = -(1 / s12) * (x - (p1.X + p2.X) / 2) + (p1.Y + p2.Y) / 2;

        Point centre = new Point(x, y);
        double r = centre.distance(p1);
        return new Circle(centre, r);
    }

    public double getMaxRadius() {
        for(int a = 0; a < NUMBER_OF_POINTS - 2; a++) {
            for(int b = a + 1; b < NUMBER_OF_POINTS - 1; b++) {
                for(int c = b + 1; c < NUMBER_OF_POINTS; c++) {
                    Circle potential = getCircle(points[a], points[b], points[c]);
                    if(potential.numberOfMembers(points) == potential.k_) {
                        potentialCircles.add(potential);
                        // radiiToTry.add(potential.round(potential.r, 10));
                        radiiToTry.add(potential.round(potential.r, 10));
                    }
                }
            }
        }
        return radiiToTry.first();



        //   for(Point point : points) {
        //       for(double r : radiiToTry) {
        //           Circle check = new Circle(point, r);
        //           double depth = check.depth(points);
        //           System.out.println(r + " " + depth);
        //           if(depth == K) {
        //               bestRadii.add(r);
        //           }
        //       }
        //   }

        //    return 0;

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
