package etude08;
import java.util.*;

public class Circle {
    public final static double MARGIN_OF_ERROR = 0.01;
    double x;
    double y;
    double r;
    ArrayList<Point> members;

    public Circle(double x, double y, double r) {
        this.x = x;
        this.y = y;
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

    public boolean containsPoint(Point point) {
        return members.contains(point);
    }

    public String toString() {
        return "x: " + x + " y: " + y + " r: " + r;
    }
}
