package fractions;

public class Fraction {
   private Integer divident, divisor;

   public Fraction(String string) throws IllegalArgumentException {
      Integer[] parts = parsePartsFromString(string);
      convertToPositiveAndSave(parts[0], parts[1]);
   }

   public Fraction(Integer divident, Integer divisor) throws IllegalArgumentException {
      convertToPositiveAndSave(divident, divisor);
   }

   @Override
   public String toString() {
      return divident + "/" + divisor;
   }

   public Integer getDivident() {
      return divident;
   }

   public Integer getDivisor() {
      return divisor;
   }

   public Fraction plus(Fraction other) {
      Integer lmc = this.divisor * other.divisor / gdc(this.divisor, other.divisor);
      return new Fraction(
            this.divident * (lmc / this.divisor) + other.divident * (lmc / other.divisor),
            lmc
      );
   }

   public Fraction minus(Fraction other) {
      return this.plus(
            new Fraction(
                  -other.divident,
                  other.divisor
            )
      );
   }

   public Fraction multiplyBy(Fraction other) {
      return new Fraction(
            this.divident * other.divident,
            this.divisor * other.divisor
      );
   }

   public Fraction divideBy(Fraction other) {
      return this.multiplyBy(
            new Fraction(
                  other.divisor,
                  other.divident
            )
      );
   }

   private Integer[] parsePartsFromString(String string) throws IllegalArgumentException {
      String[] parts = string.split("/");
      if (parts.length != 2) {
         parts = string.split(" ");
         if (parts.length != 2) {
            throw new IllegalArgumentException(string + " is not a valid fraction");
         }
      }

      Integer[] parsed = {
            Integer.parseInt(parts[0]),
            Integer.parseInt(parts[1])
      };

      return parsed;
   }

   private void convertToPositiveAndSave(Integer divident, Integer divisor) {
      Integer[] parts;
      if (divisor < 0) {
         parts = simplify(-divident, -divisor);
      } else if (divisor == 0) {
         throw new IllegalArgumentException("Division by zero");
      } else {
         parts = simplify(divident, divisor);
      }

      setDivident(parts[0]);
      setDivisor(parts[1]);
   }

   private void setDivident(Integer n) {
      this.divident = n;
   }

   private void setDivisor(Integer n) throws IllegalArgumentException {
      this.divisor = n;
   }

   private Integer[] simplify(Integer divident, Integer divisor) {
      Integer gdc = gdc(divident, divisor);
      Integer[] simplified = {divident / gdc, divisor / gdc};

      return simplified;
   }

   private Integer gdc(Integer a, Integer b) {
      if (a == 0) {
         return b;
      }

      Integer x = Math.abs(a);
      Integer y = Math.abs(b);

      while (!x.equals(y)) {
         if (x > y) {
            x = x - y;
         } else {
            y = y - x;
         }
      }

      return x;
   }
}
