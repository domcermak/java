package shapes;

import java.util.ArrayList;

public class Main {
   public static void main(String[] args) {
      ArrayList<Shape> list = new ArrayList<>();
      Long totalArea = new Long(0);

      list.add(new Circle(3.));
      list.add(new Triangle(4., 5., 3.));
      list.add(new Rectangle(3., 2.));
      list.add(new Square(6.));

      for (Shape shape : list) {
         totalArea += Math.round(shape.area());
      }

      System.out.println(Math.round(list.get(0).area()) == 28);
      System.out.println(list.get(1).area() == 6.);
      System.out.println(list.get(2).area() == 6.);
      System.out.println(list.get(3).area() == 36.);
      System.out.println(totalArea == 28 + 6 + 6 + 36);
   }
}
