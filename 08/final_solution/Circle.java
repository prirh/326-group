package etude08;
import java.util.*;

public class Circle {
    double r;
    ArrayList<Point> members;
    Point center;

    public Circle(double x, double y, double r) {
        this(new Point(x, y), r);
    }

    public Circle(Point center, double r) {
        this.center = center;
        this.r = r;
    }

    public int numberOfMembers(Point[] candidates) {
        int members = 0;
        for(Point candidate : candidates) {
            if(center.distance(candidate) < r - 0.00001) {
                members++;
            }
        }
        return members;
    }

    public static double round(double toBeRounded, int dp) {
        double multiplier = Math.pow(10, dp);
        return Math.round(toBeRounded * multiplier) / multiplier;
    }

    public String toString() {
        return "x: " + center.X + " y: " + center.Y + " r: " + r;
    }

}
