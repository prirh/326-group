package etude08;

import java.util.*;

public class Prove {
  private int k = 11;
  private Point[] points;
  private final int NUMBER_OF_POINTS;
  private TreeSet<Double> radiiToTry;

  public Prove(ArrayList<String> points) {
    Scanner scanner;
    NUMBER_OF_POINTS = points.size();
    this.points = new Point[NUMBER_OF_POINTS];
    int i = 0;
    for(String input : points) {
      scanner = new Scanner(input);
      this.points[i++] = new Point(scanner.nextDouble(), scanner.nextDouble());
    }
  }

  public static void main(String[]args) {
    Scanner scan = new Scanner(System.in);
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



    Prove map = new Prove(coords);

    for(Point point : map.points) {
        System.out.println(point);
    }
    Circle model = new Circle(1125, 566.987298, 1000);
    System.out.println(model.numberOfMembers(map.points));
  }
}
