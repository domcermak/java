package shapes;

public class Circle extends Shape {
   private Double radius;

   public static Circle InitWithRadius(Double radius) {
      return new Circle(radius);
   }

   public static Circle InitWithDiameter(Double diameter) {
      return InitWithRadius(diameter / 2);
   }

   @Override
   public Double area() {
      return Math.PI * radius * radius;
   }

   private Circle(Double radius) {
      validatedPositiveValue(radius, "radius");
      this.radius = radius;
   }
}
