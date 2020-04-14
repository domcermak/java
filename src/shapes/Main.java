package shapes;

import java.util.Scanner;

public class Main {
   private static Scanner sc = new Scanner(System.in);

   public static void main(String[] args) {
      while (true) {
         selectShapeMessage();
         Integer selectedOption;
         while ((selectedOption = sc.nextInt()) < 1 || selectedOption > 6) {
            System.out.format("Selected option %d is not permitted%n");
            selectShapeMessage();
         }
         Shape shape = fetchShape(selectedOption);
         if (shape == null) return;

         System.out.format("Area of selected shape is %.2f cm2%n", shape.area());
         System.out.println("Let's do it again");
      }
   }

   private static void selectShapeMessage() {
      System.out.println("Select one of following shapes for area calculation:");
      System.out.println("1.  Circle (initialized using radius)");
      System.out.println("2.  Circle (initialized using diameter)");
      System.out.println("3.  Triangle");
      System.out.println("4.  Square");
      System.out.println("5.  Rectangle");
      System.out.println("Or hit 6 to Quit");
   }

   private static Shape fetchShape(Integer selectedOption) {
      while (true) {
         try {
            switch (selectedOption) {
               case 1:
                  System.out.println("Type radius value:");
                  return Circle.InitWithRadius(sc.nextDouble());
               case 2:
                  System.out.println("Type diameter value:");
                  return Circle.InitWithDiameter(sc.nextDouble());
               case 3:
                  System.out.println("Type a, b, c values:");
                  return new Triangle(sc.nextDouble(), sc.nextDouble(), sc.nextDouble());
               case 4:
                  System.out.println("Type a value:");
                  return new Square(sc.nextDouble());
               case 5:
                  System.out.println("Type a, b values:");
                  return new Rectangle(sc.nextDouble(), sc.nextDouble());
               default:
                  System.out.println("Quiting...");
                  return null;
            }
         } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Try it again");
         }
      }
   }
}
