package etude08;

import java.util.*;

public class Map {
    Point[] points;
    ArrayList<Double> distances;
    final int NUMBER_OF_POINTS;
    Circle centre;
    HashMap<double, Point[]> distancesAndPoints;

    public Map(ArrayList<String> points) {
        Scanner scanner;
        NUMBER_OF_POINTS = points.size();
        distances = new ArrayList<Double>();
        this.points = new Point[NUMBER_OF_POINTS];

        for(int i = 0; i < NUMBER_OF_POINTS; i++) {
            scanner = new Scanner(points.get(i));
            this.points[i] = new Point(scanner.nextDouble(), scanner.nextDouble());
        }
        centre = findCentre();
        centre.addDistances(this.points);
        centre.setR();
        centre.addMembers(this.points);

        for(int i = 0; i < NUMBER_OF_POINTS; i++) {
            for(int j = 0; j < NUMBER_OF_POINTS; j++) {
                this.points[i].addNeighbour(this.points[j]);
            }
            distances.add(this.points[i].eleventhNeighbourDistance());
        }
    }

    public void check(Circle circle) {
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
                Circle test = new Circle(x, y);
                test.setR(circle.r);
                test.addMembers(points);
                if(test.numberOfMembers() > 11) {
                    pass = false;
                    System.out.println("Radius too big!");
                    System.out.println("Place the circle at " + x + " " + y + " and it encloses " + test.numberOfMembers() + " points");
                    break outer;
                }
                System.out.println("x: " + x + " y: " + y + " encloses: " + test.numberOfMembers());
            }
        }
        if(pass) System.out.println("yup that's fine");
    }

    public Circle findCentre() {
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
        return new Circle((maxX + minX)/2, (maxY + minY)/2);
    }

    public String neighbours() {
        Point firstPoint = points[distances.lastIndexOf(getMaxRange())];
        return "(" + firstPoint.toString() + ") (" + firstPoint.furthestNeighbour().toString() + ")";
    }

    public double getMaxRange(){
        return Collections.max(distances);
    }

    public static void main(String[]args) {
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        int numberOfPoints = 0;
        public static final ArrayList<String> coords = new ArrayList<String>();
        while(scan.hasNextLine()){
            coords.add(scan.nextLine());
            numberOfPoints++;
        }
        if(numberOfPoints < 12) {
            System.out.println("As big as you like");
        }
        Map map = new Map(coords);

        Circle test = map.centre;

        test.setR(map.getMaxRange() / 2);
        test.addMembers(map.points);

        System.out.println("Circle: " + test.toString());
        System.out.println("encloses " + test.numberOfMembers() + " points");

        System.out.println("checking...");
        map.check(test);

        // test.setR(map.centre.r + 0.000001);
        // test.addMembers(map.points);
        // System.out.println("it now encloses " + test.numberOfMembers() + " points");
    }
}
