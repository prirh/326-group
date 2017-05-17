package etude08;

import java.util.*;

public class Map {
  private int k = 11;
  private Point[] points;
  private Set<Circle> potentialCircles;
  private final int NUMBER_OF_POINTS;
  private TreeSet<Double> radiiToTry;
  private TreeSet<Double> bestRadii;

  public Map(ArrayList<String> points) {
    Scanner scanner;
    NUMBER_OF_POINTS = points.size();
    this.points = new Point[NUMBER_OF_POINTS];
    this.potentialCircles = new TreeSet<Circle>(new Comparator<Circle>(){
      @Override
      public int compare(Circle c1, Circle c2) {
        if(c1.r > c2.r) return 1;
        if(c1.r < c2.r) return -1;
        else return 0;
      }
    });

    this.radiiToTry = new TreeSet<Double>();
    this.bestRadii = new TreeSet<Double>();

    int i = 0;
    for(String input : points) {
      scanner = new Scanner(input);
      this.points[i++] = new Point(scanner.nextDouble(), scanner.nextDouble());
    }
  }

  public Circle getCircle(Point p1, Point p2, Point p3) {
    /* If points are collinear. */
    Circle circle;
    if(p1.X * (p2.Y - p3.Y) + p2.X * (p3.Y - p1.Y) + p3.X * (p1.Y - p2.Y) == 0) {
      double d12 = p1.distance(p2);
      double d13 = p1.distance(p3);
      double d32 = p3.distance(p2);
      double maxDistance = Math.max(d12, Math.max(d13, d32));
      if(maxDistance == d13) {
        circle = new Circle((p1.X + p3.X) / 2, (p1.Y + p3.Y) / 2, d13 / 2);
        circle.setK(k + 1);
        return circle;
      }
      if(maxDistance == d12) {
        circle = new Circle((p1.X + p2.X) / 2, (p1.Y + p2.Y) / 2, d12 / 2);
        circle.setK(k + 1);
        return circle;
      }
      circle = new Circle((p3.X + p2.X) / 2, (p3.Y + p2.Y) / 2, d32 / 2);
      circle.setK(k + 1);
      return circle;
    }

    double s12 = (p2.Y - p1.Y) / (p2.X - p1.X);
    double s23 = (p3.Y - p2.Y) / (p3.X - p2.X);

    double x = ((s12 * s23) * (p1.Y - p3.Y) + s23 * (p1.X + p2.X) - s12 * (p2.X + p3.X)) / (2 * (s23 - s12));
    double y = -(1 / s12) * (x - (p1.X + p2.X) / 2) + (p1.Y + p2.Y) / 2;

    Point centre = new Point(x, y);
    double r = centre.distance(p1);
    circle = new Circle(centre, r);
    circle.setK(k);
    return circle;
  }

  public double getMaxRadius() {
    for(int a = 0; a < NUMBER_OF_POINTS - 2; a++) {
      for(int b = a + 1; b < NUMBER_OF_POINTS - 1; b++) {
        for(int c = b + 1; c < NUMBER_OF_POINTS; c++) {
          Circle potential = getCircle(points[a], points[b], points[c]);
          // System.out.println(potential);
          // System.out.println(potential.numberOfMembers(points));
          if(potential.numberOfMembers(points) == potential.k - 2) {
            potentialCircles.add(potential);
            radiiToTry.add(potential.round(potential.r, 10));
          }
        }
      }
    }
    if(radiiToTry.size() > 0) return radiiToTry.first();
    return 0;
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
          System.out.println("Place the circle at " + x + " " + y + " and it encloses " + test.numberOfMembers() + " points");
          break outer;
        }
        // System.out.println("x: " + x + " y: " + y + " encloses: " + test.numberOfMembers());
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
      return;
    }

    Map map = new Map(coords);
    double r = map.getMaxRadius();
    System.out.println(r);
  }
}
