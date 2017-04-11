package etude08;

import java.util.*;

public class Point {
    final double X;
    final double Y;
    ArrayList<Double> distances;
    ArrayList<Point> neighbours;

    public Point(double x, double y) {
        X = x;
        Y = y;
        distances = new ArrayList<Double>();
        neighbours = new ArrayList<Point>();
    }

    public void addNeighbour(Point neighbour) {
        distances.add(Math.hypot(X - neighbour.X, Y - neighbour.Y));
        neighbours.add(neighbour);
    }

    public double furthestNeighbourDistance() {
        Collections.sort(distances);
        return distances.get(11);
    }

    public Point furthestNeighbour() {
        return neighbours.get(11);
    }



    public String toString() {
        return X + " " + Y;
    }
}
