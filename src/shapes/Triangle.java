package shapes;

public class Triangle extends Shape {
   private Double a, b, c;

   public Triangle(Double a, Double b, Double c) {
      validateTriangle(a, b, c);
      this.a = a;
      this.b = b;
      this.c = c;
   }

   @Override
   public Double area() {
      Double s = (a + b + c) / 2;

      return Math.sqrt(s * (s - a) * (s - b) * (s - c));
   }

   private void validateTriangle(Double a, Double b, Double c) {
      validatedPositiveValue(a, "a");
      validatedPositiveValue(b, "b");
      validatedPositiveValue(c, "c");

      Double max = a > b ? a : b;
      max = max > c ? max : c;

      if (max > (a + b + c - max)) {
         throw new IllegalArgumentException("Cannot construct a triangle");
      }
   }
}
