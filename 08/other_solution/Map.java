package etude08;

import java.util.*;

public class Map {
    Point[] points;
    final int NUMBER_OF_POINTS;
    TreeMap<Double, String> distances;
    Double min;

    public Map(ArrayList<String> points) {
        Scanner scanner;
        NUMBER_OF_POINTS = points.size();
        this.points = new Point[NUMBER_OF_POINTS];

        int i = 0;
        for(String input : points) {
            scanner = new Scanner(input);
            this.points[i++] = new Point(scanner.nextDouble(), scanner.nextDouble());

        }
        distances = new TreeMap<Double, String>();

        for(i = 0; i < NUMBER_OF_POINTS; i++) {
            for(int j = i + 1; j < NUMBER_OF_POINTS; j++) {
                // System.out.println("Computing distances...");
                double d = this.points[i].distance(this.points[j]);
                distances.put(d, i + "," + j);
            }
        }
    }

    public double maxRadius() {
      double rMin = Collections.max(distances.keySet()) / 2;
      System.out.println("furthest points: " +rMin);
      for(Point point : points) {
        double rMax = rMin;
        for(Point otherPoint : points) {
          if(!point.equals(otherPoint)) {
            double r = point.distance(otherPoint) / 2;
            Circle circle = new Circle(point, r);
            System.out.println("Trying: " + r);
            double depth = circle.depth(points);
            System.out.println("depth: " + depth);

            if(depth == 11.0) {
              // System.out.println("Trying: " + r);
              // System.out.println("depth: " + circle.depth(points));
              rMax = r;
            }
          }
          if(rMax < rMin) rMin = rMax;
        }
        break;
      }
      return rMin;
    }

    public void check(double r) {
        boolean pass = true;

        double maxX = 0;
        double minX = 0;
        double maxY = 0;
        double minY = 0;
        for(Point point : this.points) {
            maxX = maxX < point.X ? point.X : maxX;
            maxY = maxY < point.Y ? point.Y : maxY;

            minX = minX > point.X ? point.X : minX;
            minY = minY > point.Y ? point.Y : minY;
        }

        int maxSteps = 1000;
        double step;

        int xSteps;
        int ySteps;

        double width = Math.abs(maxX - minX);
        double height = Math.abs(maxY - minY);

        if(width >= height) {
            step = width / maxSteps;
            xSteps = maxSteps;
            ySteps = (int) (height / step);
        } else {
            step = height / maxSteps;
            ySteps = maxSteps;
            xSteps = (int) (width / step);
        }

        outer:
        for(int xStep = 0; xStep <= xSteps; xStep++) {
            for(int yStep = 0; yStep <= ySteps; yStep++) {
                double x = minX + (double) xStep * step;
                double y = minY + (double) yStep * step;
                TestCircle test = new TestCircle(x, y, r);
                test.addMembers(points);
                if(test.numberOfMembers() > 11) {
                    pass = false;
                    System.out.println("Radius too big!");
                    System.out.println("Place the circle at " + x + " " + y + " and it encloses " + test.numberOfMembers() + " points:");
                    for(Point point : test.members) {
                        System.out.println(point);
                    }
                    break outer;
                }
                System.out.println("x: " + x + " y: " + y + " r: " + r + " encloses: " + test.numberOfMembers());
            }
        }
        if(pass) System.out.println("yup that's fine");
    }


    public static void main(String[]args) {
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        int numberOfPoints = 0;
        ArrayList<String> coords = new ArrayList<String>();
        while(scan.hasNextLine()){
            coords.add(scan.nextLine());
            numberOfPoints++;
        }
        if(numberOfPoints < 12) {
            System.out.println("As big as you like");
        }

        Map map = new Map(coords);

        double solution = map.maxRadius();
        System.out.println(solution);
        // map.check(solution);

    }
}
