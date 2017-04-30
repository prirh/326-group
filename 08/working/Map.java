package etude08;

import java.util.*;

public class Map {
    Point[] points;
    final int NUMBER_OF_POINTS;
    TreeMap<String, Double> distances;
    Double min;

    public Map(ArrayList<String> points) {
        Scanner scanner;
        NUMBER_OF_POINTS = points.size();
        this.points = new Point[NUMBER_OF_POINTS];

        for(int i = 0; i < NUMBER_OF_POINTS; i++) {
            scanner = new Scanner(points.get(i));
            this.points[i] = new Point(scanner.nextDouble(), scanner.nextDouble());
        }

        distances = new TreeMap<int[], Double>();
        for(int i = 0; i < NUMBER_OF_POINTS; i++) {
            for(int j = i + 1; j < NUMBER_OF_POINTS; j++) {
                double d = distance(this.points[i], this.points[j]);
                distances.put(i + "," + j, d);
            }
        }
    }

    public double findLargestRadius(int[] pointLabels) {
        double r = setLiesOnCircle(removeDuplicates(pointLabels));
        return r == 0 ? findFurthestPoints(pointLabels) : r;
    }

    private double findFurthestPoints(int[] pointLabels) {
        Double max = 0;
        for(i = 0; i < pointLabels.length; i++){
            for(j = i + 1; i < pointLabels.length; i++){
                Double d = distances.get(i + "," + j);
                max = d > max ? d : max;
            }
        }
        return max;
    }

    public int[] findClosest11() {
        min = distances.get("0,1");
        boolean[] pointsToInclude = new boolean[NUMBER_OF_POINTS];
        for(int i = 0; i < 11; i++) {
            pointsToInclude[i] = true;
        }
        for(int i = 11; i < NUMBER_OF_POINTS; i++) {
            pointsToInclude[i] = false;
        }

        int[] pointLabelsToInclude = new int[11];
        int[] closet11Labels = new int[11];
        int[] c = new int[NUMBER_OF_POINTS];
        Arrays.fill(c, 0);
        int j = 0;
        for(int i = 0; i < points.length; i++) {
            if(pointsToInclude[i]) {
                pointsLabelsToInclude[j] = i;
            }
        }

        if(findLargestRadius < min) {
            min = findLargestRadius(pointsLabelsToInclude);
            closet11Labels = pointsLabelsToInclude.clone();
        }

        int i = 0;
        while(i < NUMBER_OF_POINTS) {
            if(c[i] < i){
                if(i % 2 == 0){
                    swap(A[0], A[i]);
                } else {
                    swap(A[c[i]], A[i]);
                }
                int j = 0;
                for(int a = 0; a < points.length; a++) {
                    if(pointsToInclude[a]) {
                        pointsLabelsToInclude[j] = a;
                    }
                }
                if(findFurthestPoints(pointsLabelsToInclude) < min) {
                    min = findFurthestPoints(pointsLabelsToInclude);
                    closet11Labels = pointsLabelsToInclude.clone();
                }
                c[i]++;
                i = 0;
            } else {
                c[i] = 0;
                i++;
            }
        }
        return closet11Labels;
    }

    private double distance(Point p1, Point p2) {
        return Math.hypot(p1.X - p2.X, p1.Y - p2.Y);
    }

    private int[] removeDuplicates(int[] set) {
        distinctPoints = set.length;
        for(int i = 0; i < set.length; i++) {
            for(int j = i + 1; i < set.length; i++) {
                if(set[i] == -1 || set[j] == -1) continue;
                if(distances.get(set.[i] + "," + set[j]) == 0) {
                    set[i] = -1;
                    distinctPoints--;
                }
            }
        }
        int[] distinctPointLabels = new int[distinctPoints];
        int j = 0;
        for(int i = 0; i < set.length; i++) {
            if(set[i] > -1) distinctPointLabels[j++] = set[i];
        }
    }

    /**
     * Determines whether a set of points lie on a circle.
     * Returns the radius of that the points lie on or 0 is they do not.
     */
    private double setLiesOnCircle(int[] set) {
        if(set.length < 4) return 0;

        Point p1 = points[set[0]];
        Point p2 = points[set[1]];
        Point p3 = points[set[2]];

        double s12 = (p2.Y - p1.Y) / (p2.X - p1.X);
        double s23 = (p3.Y - p2.Y) / (p3.X - p2.X);

        double x = ((s12 * s23) * (p1.Y - p3.Y) + s23 * (p1.X + p2.X) - s12 * (p2.X + p3.X)) / (2 * (s23 - s12));
        double y = -(1 / s12) * (x - (p1.X + p2.X) / 2) + (p1.Y + p2.Y) / 2;

        Point centre = new Point(x, y);

        double r = distance(centre, p1);

        for(int i = 3; i < set.length; i++) {
            if(!pointLiesOnCircle(points[set[i]], centre, r)) return 0;
        }
        return r;
    }

    private boolean pointLiesOnCircle(Point point, Point centre, double r) {
        return Math.pow(point.X - centre.X, 2) + Math.pow(point.Y - centre.Y, 2) == Math.pow(r, 2);
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

        System.out.println(Arrays.toString(map.findClosest11()));

    }
}
