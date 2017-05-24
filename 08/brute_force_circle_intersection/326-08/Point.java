package etude08;

import java.util.*;

public class Point {
    final double X;
    final double Y;
    HashMap<Integer, Double> distances;
    public int LABEL;
    boolean entry;

    public Point(double x, double y, int label) {
        this(x, y);
        this.LABEL = label;
    }

    public Point(double x, double y, boolean entry) {
        this(x, y);
        this.entry = entry;
    }

    public Point(double x, double y) {
        X = x;
        Y = y;
        distances = new HashMap<Integer, Double>();
    }

    public void addNeighbour(int neighbourID, double d) {
        distances.put(neighbourID, d);
    }

    public double distance(Point otherPoint) {
        return Math.hypot(X - otherPoint.X, Y - otherPoint.Y);
    }

    public double getDistanceTo(int neighbourID) {
        return distances.get(neighbourID);
    }

    public String toString() {
        return X + " " + Y;
    }
}
