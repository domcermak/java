package comparing.fruits;

import comparing.students.mycomparing.MyComparing;

public class AppleApp {
   public static void main(String[] args) {
      Apple[] apples = {new Apple("Gala", 4), new Apple("Jonatan", 2), new Apple("Idared", 3)};
      MyComparing.print(apples);
      System.out.println("Sorting by sweetness");
      MyComparing.sort(apples);
      MyComparing.print(apples);
   }

}