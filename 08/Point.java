package etude08;

import java.util.*;

public class Point {
    final double X;
    final double Y;
    ArrayList<Double> distances;

    public Point(double x, double y) {
        X = x;
        Y = y;
        distances = new ArrayList<Double>();
    }

    public void addNeighbour(Point neighbour) {
        distances.add(Math.hypot(X - neighbour.X, Y - neighbour.Y));
    }

    public double furthestNeighbour() {
        Collections.sort(distances);
        return distances.get(11);
    }

    public String toString() {
        return X + " " + Y;
    }
}
