package reflection.api;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

 public class Polygon implements Cloneable{

   Set<Point> points;
  /*  private final int SCALE = 2;
     final int SCALE233 = 2;
     static int yi=20;*/
    public Polygon() {
        points = new HashSet<>();
    }

    public int getTotalPoints() {
        return points.size();
    }
    protected void addPoint(int x, int y) {
        points.add(new Point(x, y));
    }
    public static void PRINT_SOMETHING() {
        System.out.println("this is a static method");
    }

}

