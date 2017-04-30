package etude08;
import java.util.*;

public class Circle {
    double x;
    double y;
    double r;
    ArrayList<Point> members;

    public Circle(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void addMembers(Point[] candidates) {
        members = new ArrayList<Point>();
        for(Point candidate : candidates) {
            if(Math.hypot(x - candidate.X, y - candidate.Y) < r) {
                members.add(candidate);
            }
        }
    }

    public void setR(double r) {
        this.r = r;
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
