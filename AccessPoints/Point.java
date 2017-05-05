package etude08;

import java.util.*;

public class Point {
    final double X;
    final double Y;
    ArrayList<double> distances;

    public Point(double x, double y) {
        X = x;
        Y = y;
        distances = new ArrayList<double>();
    }

    public void addNeighbour(Point neighbour) {
        distances.add(Math.hypot(X - neighbour.X, Y - neighbour.Y));
    }

    public double furthestNeighbour() {
        return Collections.sort(distances).get(12);
    }
}
