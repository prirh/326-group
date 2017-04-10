package etude08;

import java.util.*;

public class Map {
    Point[] points;
    double[] distances;
    final int NUMBER_OF_POINTS;

    public Map(ArrayList<String> points) {
        Scanner scanner;
        NUMBER_OF_POINTS = points.size();
        distances = new double[NUMBER_OF_POINTS];
        this.points = new Point[NUMBER_OF_POINTS];

        for(int i = 0; i < NUMBER_OF_POINTS; i++) {
            scanner = new Scanner(points.get(i));
            this.points[i] = new Point(scanner.nextDouble(), scanner.nextDouble());
        }

        for(int i = 0; i < NUMBER_OF_POINTS; i++) {
            for(int j = 0; j < NUMBER_OF_POINTS; j++) {
                this.points[i].addNeighbour(this.points[j]);
            }
            distances[i] = (this.points[i].furthestNeighbour());
        }
    }

    public double getMaxRange(){
        return Collections.min(Arrays.asList(distances));
    }
}
