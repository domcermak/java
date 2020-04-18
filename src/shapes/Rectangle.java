package shapes;

public class Rectangle extends Shape {
   private Double a, b;

   public Rectangle(Double a, Double b) {
      validatedPositiveValue(a, "a");
      validatedPositiveValue(b, "b");

      this.a = a;
      this.b = b;
   }

   @Override
   public Double area() {
      return a * b;
   }
}
