package reflection.api;

import java.io.Serializable;

 public class Rectangle extends Polygon  implements Serializable,Comparable{

//
    protected int x;
   //public static final double yc=0.0;
    public int y;
/*    private  int SCALEj = 2;
    public  final int SCALE2 = 2;
    protected final int SCALE23= 2;
    static int rt;
    private long gd;*/
    public static void PRINT_SOMETHING() {
        System.out.println("this is a static method");
    }
    public static void PRI3NT_SOMETHING() {
        System.out.println("this is a static method");
    }
    protected static void Pw3RI3NT_SOMETHING() {
        System.out.println("this is a static method");
    }

    protected Rectangle() {
        x = -1;
        y = -1;
 }
    public Rectangle(int x, int y,int z) {
        this.x = x;
        this.y = y;
        updateParent();
    }
    public Rectangle(int x, int y) {
        this.x = x;
        this.y = y;
        updateParent();
    }

    private void updateParent() {
        addPoint(0, 0);
        addPoint(x, 0);
        addPoint(0, y);
        addPoint(x, y);
    }
    public int calcArea() {
        return x * y;
    }

    public int calcPerimeter() {
        return twice(x) + twice(y);
    }

    public int twice(int num) {
        return 2 * num;
    }

    @Override
    public int compareTo(Object o) {

        return this.calcArea() - ((Rectangle)o).calcArea();
    }
    public static int getP() {
        return 1;
    }
    private static int get7P() {
        return 7;
    }
    protected static int gewt7P() {
        return 75;
    }
}

