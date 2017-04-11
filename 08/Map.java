package etude08;

import java.util.*;

public class Map {
    Point[] points;
    ArrayList<Double> distances;
    final int NUMBER_OF_POINTS;

    public Map(ArrayList<String> points) {
        Scanner scanner;
        NUMBER_OF_POINTS = points.size();
        distances = new ArrayList<Double>();
        this.points = new Point[NUMBER_OF_POINTS];

        for(int i = 0; i < NUMBER_OF_POINTS; i++) {
            scanner = new Scanner(points.get(i));
            this.points[i] = new Point(scanner.nextDouble(), scanner.nextDouble());
        }

        for(int i = 0; i < NUMBER_OF_POINTS; i++) {
            for(int j = 0; j < NUMBER_OF_POINTS; j++) {
                this.points[i].addNeighbour(this.points[j]);
            }
            distances.add(this.points[i].furthestNeighbourDistance());
        }
    }

    public String neighbours() {
        Point firstPoint = points[distances.lastIndexOf(getMaxRange())];
        return "(" + firstPoint.toString() + ") (" + firstPoint.furthestNeighbour().toString() + ")";
    }

    public double getMaxRange(){
        return Collections.max(distances);
    }
}
