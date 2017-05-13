package etude08;
import java.util.*;

public class Circle {
    double x;
    double y;
    double r;
    ArrayList<Point> members;
    ArrayList<Double> distances;

    public Circle(double x, double y) {
        this.x = x;
        this.y = y;
        distances = new ArrayList<Double>();
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

    public void setR() {
        r = furthestNeighbourDistance();
    }

    public void addDistances(Point[] neighbours) {
        for(Point neighbour : neighbours) {
            distances.add(Math.hypot(x - neighbour.X, y - neighbour.Y));
        }
    }

    public boolean containsPoint(Point point) {
        return members.contains(point);
    }

    public double furthestNeighbourDistance() {
        Collections.sort(distances);
        return distances.get(11);
    }

    public String toString() {
        return "x: " + x + " y: " + y + " r: " + r;
    }
}
