package etude08;
import java.util.*;

public class Circle {
    public final static double MARGIN_OF_ERROR = 0.01;
    double r;
    ArrayList<Point> members;
    ArrayList<Point> topIntersections;
    ArrayList<Point> bottomIntersections;
    ArrayList<Point> intersectingCircles;
    Point center;
    Point left;
    double overlap;

    public static void main(String[] args) {
        // Point[] points = new Point[4];
        // points[0] = new Point(-1, -1);
        // points[1] = new Point(-1, 1);
        // points[2] = new Point(1, 1);
        // points[3] = new Point(1, -1);
        // System.out.println(points[0].distance(points[2]) / 2);
        //
        // Circle test = new Circle(points[0], points[0].distance(points[2]) / 2);
        // System.out.println(test.depth(points));
        //
        // System.out.println(test.topIntersections);
        // System.out.println(test.bottomIntersections);
        // System.out.println(test.leftDepth());
        //
        //
        // System.out.println("sorted");
        // System.out.println(test.topIntersections);
        // System.out.println(test.bottomIntersections);

    }

    public Circle(double x, double y, double r) {
        this(new Point(x, y), r);
    }

    public Circle(Point center, double r) {
        this.center = center;
        this.r = r;
        this.left = new Point(center.X - r, center.Y);
        overlap = 0;
        topIntersections = new ArrayList<Point>();
        bottomIntersections = new ArrayList<Point>();
        intersectingCircles = new ArrayList<Point>();
    }

    public int numberOfMembers(Point[] candidates) {
        int members = 0;
        for(Point candidate : candidates) {
            if(Math.hypot(center.X - candidate.X, center.Y - candidate.Y) < r) {
                members++;
            }
        }
        return members;
    }

    public void reset() {
        overlap = 0;
        topIntersections = new ArrayList<Point>();
        bottomIntersections = new ArrayList<Point>();
        intersectingCircles = new ArrayList<Point>();
    }

    public double depth(Point[] map) {
        addIntersections(map);
        // System.out.println(intersectingCircles);
        double currentDepth = leftDepth();
        double maxDepth = currentDepth;

        Collections.sort(topIntersections, new Comparator<Point>(){
            @Override
            public int compare(Point p1, Point p2) {
                if(p1.X > p2.X) return 1;
                if(p1.X < p2.X) return -1;
                else if(p1.entry) return -1;
                else return 1;
            }
        });

        Collections.sort(bottomIntersections, new Comparator<Point>(){
            @Override
            public int compare(Point p1, Point p2) {
                if(p1.X < p2.X) return 1;
                if(p1.X > p2.X) return -1;
                else if(p1.entry) return -1;
                else return -1;
            }
        });

        for(Point intersection : topIntersections) {
            if(intersection.entry) currentDepth++;
            else currentDepth--;
            if(currentDepth > maxDepth) maxDepth = currentDepth;
        }

        for(Point intersection : bottomIntersections) {
            if(intersection.entry) currentDepth++;
            else currentDepth--;
            if(currentDepth > maxDepth) maxDepth = currentDepth;
        }
        return maxDepth;
    }

    private double leftDepth() {
        double depth = overlap;
        for(Point point : intersectingCircles) {
            if(point.distance(left) <= r) {
                depth++;
            }
        }
        return depth;
    }

    public void addIntersections(Point[] map) {
        for(Point point : map) {
            if(point.distance(center) < 0.00001) {
                overlap++;
                continue;
            }
            if(point.distance(center) <= 2 * r) {
                intersectingCircles.add(point);
                for(Point intersection : findIntersections(point)) {
                    if(intersection.Y >= center.Y) {
                        topIntersections.add(intersection);
                    } else {
                        bottomIntersections.add(intersection);
                    }
                }
            }
        }
    }

    private Point[] findIntersections(Point point) {
        final double DP = 1000.0;
        Point[] intersections = new Point[2];
        double d = point.distance(center);
        double a = d / 2;
        double h = Math.sqrt(Math.pow(r, 2) - Math.pow(a, 2));

        Point mid = new Point((point.X + center.X) / 2, (point.Y + center.Y) / 2);

        double x1 = Math.round((mid.X + h * (point.Y - center.Y) / d) * DP) / DP;
        double y1 = Math.round((mid.Y - h * (point.X - center.X) / d) * DP) / DP;

        double x2 = Math.round((mid.X - h * (point.Y - center.Y) / d) * DP) / DP;
        double y2 = Math.round((mid.Y + h * (point.X - center.X) / d) * DP) / DP;

        if(point.Y >= center.Y) {
            intersections[0] = new Point(x2, y2, x1 >= x2);
            intersections[1] = new Point(x1, y1, x1 < x2);
        } else {
            intersections[0] = new Point(x2, y2, x1 <= x2);
            intersections[1] = new Point(x1, y1, x1 > x2);
        }
        return intersections;
    }

    public static double round(double toBeRounded, int dp) {
        double multiplier = Math.pow(10, dp);
        return Math.round(toBeRounded * multiplier) / multiplier;
    }

    public String toString() {
        return "x: " + center.X + " y: " + center.Y + " r: " + r;
    }

}
