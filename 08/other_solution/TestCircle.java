package etude08;
import java.util.*;

public class TestCircle {
    double x;
    double y;
    double r;
    ArrayList<Point> members;

    public TestCircle(double x, double y, double r) {
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
