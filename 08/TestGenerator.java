package etude08;

import java.util.*;

public class TestGenerator {
    public static final Point ORIGIN = new Point(1, 1);
    public static final double NUMBER_OF_POINTS = 12;
    public static final double RADIUS = 20;
    public static Point[] points;

    public static void main(String[] args){
        double slice = 360 / NUMBER_OF_POINTS;
        double t = 0;
        points = new Point[(int) NUMBER_OF_POINTS];

        System.out.println("Access points");
        //
        // for(int i = 0; i < NUMBER_OF_POINTS; i++) {
        //     points[i] = new Point(ORIGIN.X + RADIUS * Math.cos(t), ORIGIN.Y + RADIUS * Math.sin(t));
        //     System.out.println(points[i].toString());
        //     t += slice;
        // }

        Random random = new Random();
        for(int i = 0; i < 100; i ++) {
            System.out.println((2000 * random.nextDouble() - 1000) + " " + (2000 * random.nextDouble() - 1000));
        }
    }
}
