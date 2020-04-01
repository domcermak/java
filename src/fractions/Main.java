package fractions;

public class Main {
   public static void main(String[] args) {
      Fraction a = new Fraction("3/4");
      Fraction b = new Fraction("-2/-4");
      Fraction c = new Fraction("2/-4");
      Fraction d = new Fraction("-2/4");
      Fraction e = new Fraction("0/-4");
      Fraction f = new Fraction("16 4");
      Fraction g = new Fraction(16, 4);
      try {
         new Fraction("22/0");
         System.out.println("exception not raised");
      } catch (IllegalArgumentException exception) {
         System.out.println("true");
      }
      try {
         new Fraction(22, 0);
         System.out.println("exception not raised");
      } catch (IllegalArgumentException exception) {
         System.out.println("true");
      }

      System.out.println(a.toString().equals("3/4"));
      System.out.println(b.toString().equals("1/2"));
      System.out.println(c.toString().equals("-1/2"));
      System.out.println(d.toString().equals("-1/2"));
      System.out.println(e.toString().equals("0/1"));
      System.out.println(f.toString().equals("4/1"));
      System.out.println(g.toString().equals("4/1"));


      System.out.println(
            a.plus(b).toString().equals("5/4")
      );
      System.out.println(
            c.minus(d).toString().equals("0/1")
      );
      System.out.println(
            a.multiplyBy(d).toString().equals("-3/8")
      );
      System.out.println(
            a.divideBy(d).toString().equals("-3/2")
      );
      try {
         a.divideBy(e);
         System.out.println("exception not raised");
      } catch (IllegalArgumentException exception) {
         System.out.println(exception.getMessage().equals("Division by zero"));
      }

      System.out.println(
            FractionsCalculator.add(a,b).toString().equals("5/4")
      );
      System.out.println(
            FractionsCalculator.subtract(c,d).toString().equals("0/1")
      );
      System.out.println(
            FractionsCalculator.multiply(a,d).toString().equals("-3/8")
      );
      System.out.println(
            FractionsCalculator.divide(a,d).toString().equals("-3/2")
      );
      try {
         FractionsCalculator.divide(a,e);
         System.out.println("exception not raised");
      } catch (IllegalArgumentException exception) {
         System.out.println(exception.getMessage().equals("Division by zero"));
      }

      System.out.println("Done");
   }
}