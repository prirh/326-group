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

    public static void main(String[] args) {
        Point[] points = new Point[5];
        points[0] = new Point(1.5, 2.5);
        points[1] = new Point(-1.4, 2.6);
        points[2] = new Point(-3, -4);
        points[3] = new Point(3.5, -1.8);
        points[4] = new Point(0.2, 0.5);

        Circle test = new Circle(points[4], points[1].distance(points[4]));
        test.addIntersections(points);
        System.out.println(test.depth());

    }

    public Circle(double x, double y, double r) {
        this(new Point(x, y), r);
    }
    public Circle(Point center, double r) {
        this.center = center;
        this.r = r;
        this.left = new Point(center.X - r, center.Y);
        topIntersections = new ArrayList<Point>();
        bottomIntersections = new ArrayList<Point>();
        intersectingCircles = new ArrayList<Point>();
    }

    public void addMembers(Point[] candidates) {
        members = new ArrayList<Point>();
        for(Point candidate : candidates) {
            if(Math.hypot(center.X - candidate.X, center.Y - candidate.Y) < r) {
                members.add(candidate);
            }
        }
    }

    public double depth() {
        double currentDepth = leftDepth();
        double maxDepth = currentDepth;
        Collections.sort(topIntersections, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                if(p1.X >= p2.X) return -1;
                else return 1;
            }
        });

        Collections.sort(bottomIntersections, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                if(p1.X >= p2.X) return -1;
                else return 1;
            }
        });

        for(Point intersection : topIntersections) {
            if(intersection.entry) currentDepth++;
            else currentDepth--;
            if(currentDepth > maxDepth) maxDepth = currentDepth;
        }
        return maxDepth;
    }

    private double leftDepth() {
        double depth = 1;
        for(Point point : intersectingCircles) {
            if(point.distance(left) < r) {
                depth++;
            }
        }
        return depth;
    }

    public void addIntersections(Point[] map) {
        for(Point point : map) {
            if(point.distance(center) < r) {
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
        Point[] intersections = new Point[2];
        double a = Math.sqrt((4 * Math.pow(r, 2)) / point.distance(center) - 1);
        double xSum = (center.X + point.X) / 2;
        double ySum = (center.Y + point.Y) / 2;
        double xDiff = a * (center.X - point.X);
        double yDiff = a * (point.Y - center.Y);

        double x1 = xSum + yDiff;
        double y1 = ySum + xDiff;

        double x2 = xSum - yDiff;
        double y2 = ySum - xDiff;

        if(point.Y >= center.Y) {
            intersections[0] = new Point(x2, y2, x1 > x2);
            intersections[1] = new Point(x1, y1, x1 < x2);
        } else {
            intersections[0] = new Point(x2, y2, x1 < x2);
            intersections[1] = new Point(x1, y1, x1 > x2);
        }
        return intersections;
    }

    public String toString() {
        return "x: " + center.X + " y: " + center.Y + " r: " + r;
    }
}
