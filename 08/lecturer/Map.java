package etude08;

import java.util.*;

public class Map {
    Point[] points;
    TreeSet<Circle> potentialCircles;
    final int NUMBER_OF_POINTS;

    public Map(ArrayList<String> points) {
        Scanner scanner;
        NUMBER_OF_POINTS = points.size();
        this.points = new Point[NUMBER_OF_POINTS];
        this.potentialCircles = new TreeSet<Circle>(new Comparator<Circle>(){
            @Override
            public int compare(Point c1, Point c2) {
                if(c1.r > c2.r) return 1;
                if(c1.r < c2.r) return -1;
                else return 0;
            }
        });

        int i = 0;
        for(String input : points) {
            scanner = new Scanner(input);
            this.points[i++] = new Point(scanner.nextDouble(), scanner.nextDouble());

        }
    }

    public Circle getCircle(Point p1, Point p2, Point p3) {
      if(p2.Y == p1.Y && p3.Y == p2.Y) {
        
      }
      if(p2.X == p1.X && p3.X == p2.X) return null;

      double s12 = (p2.Y - p1.Y) / (p2.X - p1.X);
      double s23 = (p3.Y - p2.Y) / (p3.X - p2.X);

      double x = ((s12 * s23) * (p1.Y - p3.Y) + s23 * (p1.X + p2.X) - s12 * (p2.X + p3.X)) / (2 * (s23 - s12));
      double y = -(1 / s12) * (x - (p1.X + p2.X) / 2) + (p1.Y + p2.Y) / 2;

      Point centre = new Point(x, y);

      double r = distance(centre, p1);

      return new Circle(centre, r);
    }

    public double getMaxRadius() {
      for(int a = 0; a < NUMBER_OF_POINTS - 2; a++) {
        for(int b = a + 1; b < NUMBER_OF_POINTS - 1; b++) {
          for(int c = b + 1; c < NUMBER_OF_POINTS; c++) {
            Circle potential = getCircle(points[a], points[b], points[c]);
            potential.addMembers(points);
            if(potential.numberOfMembers() == 9) potentialCircles.add(getCircle(points[a], points[b], points[c]));
          }
        }
      }

      int mid = NUMBER_OF_POINTS / 2;

      if()
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

        if(numberOfPoints < 14) {

        }

        Map map = new Map(coords);

        double solution = map.

    }
}
