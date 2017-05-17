package etude08;
import java.util.*;

public class Circle {
    double r;
    ArrayList<Point> members;
    Point center;
    public int k_;

    public Circle(double x, double y, double r) {
        this(new Point(x, y), r);
    }

    public Circle(double r, int k) {
        this(0, 0, r);
        this.k_ = k;
    }

    public Circle(Point center, double r) {
        this.center = center;
        this.r = r;
        k_ = 0;
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

    public static double round(double toBeRounded, int dp) {
        double multiplier = Math.pow(10, dp);
        return Math.round(toBeRounded * multiplier) / multiplier;
    }

    public String toString() {
        return "x: " + center.X + " y: " + center.Y + " r: " + r;
    }

}
