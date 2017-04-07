package etude08;

import java.util.*;

public class Map {
    ArrayList<Point> points;
    ArrayList<Double> distances;
    final int NUMBER_OF_POINTS;

    public Map(ArrayList<String> points) {
        Scanner scanner;
        NUMBER_OF_POINTS = points.length;
        distances = new ArrayList<Double>();

        for(int i = 0; i < NUMBER_OF_POINTS; i++) {
            scanner = new Scanner(points[i]);
            this.points.add(new Point(scanner.nextDouble(), scanner.nextDouble()));
        }

        for(i = 0; i < NUMBER_OF_POINTS; i++) {
            for(int j = 0; j < numberOfPoints; j++) {
                this.points[i].addNeighbour(this.points[j]);
            }
            distances.add(this.points[i].furthestNeighbour());
        }
    }

    public double getMaxRange(){
        return Collections.min(distances);
    }

    public static void main(String[] args) {
        
    }
}
