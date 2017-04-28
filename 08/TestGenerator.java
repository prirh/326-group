package etude08;

import java.util.*;

/**
 * Generates test input
 * Uncomment one case a time.
 */
public class TestGenerator {
    public static Point[] points;

    public static void main(String[] args){
        System.out.println("Access points");
        /* POINTS AROUND A CIRCLE */
        final Point ORIGIN = new Point(-999.8129375642419, -534.3475141777158);
        final double NUMBER_OF_POINTS = 100;
        final double RADIUS = 385.5014923473633;
        double slice = 360 / NUMBER_OF_POINTS;
        double t = 0;
        points = new Point[(int) NUMBER_OF_POINTS];

        for(int i = 0; i < NUMBER_OF_POINTS; i++) {
            points[i] = new Point(ORIGIN.X + RADIUS * Math.cos(t), ORIGIN.Y + RADIUS * Math.sin(t));
            System.out.println(points[i].toString());
            t += slice;
        }

        /* RANDOM POINTS */
        // Random random = new Random();
        // for(int i = 0; i < 100; i ++) {
        //     System.out.println((2000 * random.nextDouble() - 1000) + " " + (2000 * random.nextDouble() - 1000));
        // }

        /* UNIFORM POINTS ALONG A LINE */
        // for(int i = -50; i < 50; i ++) {
        //     System.out.println("0 " + i);
        // }

        /* RANDOM POINTS ALONG A LINE */
        // Random random = new Random();
        // for(int i = -50; i < 50; i ++) {
        //     System.out.println("0 " + (100 * random.nextDouble() - 50));
        // }
    }
}
