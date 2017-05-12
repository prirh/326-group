package etude08;
import java.util.*;

public class Circle {
    public final static double MARGIN_OF_ERROR = 0.01;
    double r;
    ArrayList<Point> members;
    Point center;


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
    }

    public void addMembers(Point[] candidates) {
        members = new ArrayList<Point>();
        for(Point candidate : candidates) {
            if(Math.hypot(x - candidate.X, y - candidate.Y) < r) {
                members.add(candidate);
            }
        }
    }

    public int numberOfMembers() {
        return members.size();
    }

    public String toString() {
        return "x: " + center.X + " y: " + center.Y + " r: " + r;
    }
}
