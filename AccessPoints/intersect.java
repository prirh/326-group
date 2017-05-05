import java.lang.*;

public class Intersect{

  public Circle object = new Circle();
  public Point object = new Point();
  public Point intersections[1];
  public double x1, y1, x2, y2;

  public static void main(String[]args){

  }


  public intersections (Circle c1, Circle c2, double dist){
    double val1, val2, test;
    if(((c1.r + c2.r) >= dist) && (dist >= Math.abs(c1.r - c2.r))){
      // Two circles intersect or tangent

      // Area according to Heron's formula
      double a1 = dist + c1.r + c2.r;
      double a2 = dist + c1.r - c2.r;
      double a3 = dist - c1.r + c2.r;
      double a4 = -dist + c1.r + c2.r;

      double area = Math.sqrt(a1*a2*a3*a4)/4;


      // Calculating x axis intersection values
      val1 = (c1.x + c2.x) / 2 + (c2.x - c1.x) * ((c1.r * c1.r) - (c2.r * c2.r))
      / (2 * dist * dist);
      val2 = 2 * (c1.y - c2.y) * area / (dist * dist);

      x1 = val1 + val2;
      x2 = val1 - val2;



      // Calculating y axis intersection values
      val1 = (c1.y + c2.y) / 2 + (c2.y - c1.y) * ((c1.r * c1.r) - (c2.r * c2.r))
      / (2 * dist * dist);
      val2 = 2 * (c1.x - c2.x) * area / (dist * dist);

      y1 = val1 - val2;
      y2 = val1 + val2;

      // Intersection points are (x1, y1) and (x2, y2)

      // Because for every x we have two values of y, and the same thing for y,
      // we have to verify that the intersection points as chose are on the
      // circle otherwise we have to swap between the points
      test = Math.abs((x1 - c1.x) * (x1 - c1.x) + (y1 - c1.y) * (y1 - c1.y)
      - c1.r * c1.r);
      if(test > 0.0000001) {
        // point is not on the circle, swap between y1 and y2
        // the value of 0.0000001 is arbitrary chose, smaller values are also OK
        // do not use the value 0 because of computer rounding problems

        double tmp = y1;
        y1 = y2;
        y2 = tmp;
      }
      Point intersect1 = new Point();
      Point intersect2 = new Point();
      intersect1.x = x1;
      intersect1.y = y1;
      intersect2.x = x2;
      intersect2.y = y2;

      intersections.add(intersect1);
      intersections.add(intersect2);

      return intersections; // return array of 2 intersections

    } else {
      return intersections;
      // return empty array of points
    }
    
  }

}
