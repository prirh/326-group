package etude08;

import java.util.*;

public class Map {
    ArrayList<Point> points;
    ArrayList<Double> distances;
    final int NUMBER_OF_POINTS;

    public Map(ArrayList<String> points) {
        Scanner scanner;
        NUMBER_OF_POINTS = points.size();
        distances = new ArrayList<Double>();
        this.points = new ArrayList<Point>();

        for(int i = 0; i < NUMBER_OF_POINTS; i++) {
            scanner = new Scanner(points.get(i));
            this.points.add(new Point(scanner.nextDouble(), scanner.nextDouble()));
        }

        for(int i = 0; i < NUMBER_OF_POINTS; i++) {
            for(int j = 0; j < NUMBER_OF_POINTS; j++) {
                this.points.get(i).addNeighbour(this.points.get(j));
            }
            distances.add(this.points.get(i).furthestNeighbour());
        }
    }

    public double getMaxRange(){
        return Collections.min(distances);
    }
}
