package comparing.students;

import java.util.Arrays;
import java.util.List;

public class Datasource {
   private static Student[] data = {
         new Student("Alice", "Mala", 345, 5, 5, 1, 1, 4),
         new Student("Bob", "Velky", 123, 1, 1, 1, 2),
         new Student("Cyril", "Stredny", 567, 3, 3, 3, 1)
   };

   public static Student[] loadDataAsArray() {
      return Arrays.copyOf(data, data.length);
   }

   public static List<Student> loadDataAsList() {
      return Arrays.asList(data);
   }
}