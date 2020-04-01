package shapes;

public class Circle extends Shape {
   private Double radius;

   public Circle(Double radius) {
      validatedPositiveValue(radius, "radius");
      this.radius = radius;
   }

   @Override
   public Double area() {
      return Math.PI * radius * radius;
   }
}
