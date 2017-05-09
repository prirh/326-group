package etude08;

import java.util.*;

public class Point {
    final double X;
    final double Y;
    ArrayList<Double> distances;
    ArrayList<Point> neighbours;
    boolean entry;

    public Point(double x, double y) {
        X = x;
        Y = y;
        distances = new ArrayList<Double>();
        neighbours = new ArrayList<Point>();
    }

    public Point(double x, double y, boolean entry) {
        X = x;
        Y = y;
        this.entry = entry;
        distances = new ArrayList<Double>();
        neighbours = new ArrayList<Point>();
    }

    public double distance(Point otherPoint) {
        return Math.hypot(X - otherPoint.X, Y - otherPoint.Y);
    }

    public void addNeighbour(Point neighbour) {
        if(!this.equals(neighbour)) neighbours.add(neighbour);
    }

    public double furthestNeighbourDistance() {
        Collections.sort(distances);
        return distances.get(distances.size() - 1);
    }

    public double eleventhNeighbourDistance() {
        Collections.sort(distances);
        return distances.get(11);
    }

    public Point furthestNeighbour() {
        return neighbours.get(11);
    }

    public String toString() {
        return X + " " + Y + " " + entry;
    }
}
