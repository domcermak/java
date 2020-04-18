package shapes;

public abstract class Shape implements Comparable<Shape> {
   public abstract Double area();

   protected void validatedPositiveValue(Double value, String name) throws IllegalArgumentException {
      if (value <= 0) {
         throw new IllegalArgumentException(name + " must have positive value");
      }
   }

   @Override
   public int compareTo(Shape o) {
      return -this.area().compareTo(o.area());
   }
}
