package comparing.students;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import comparing.students.mycomparing.MyComparing;

public class Comparing {
   public static void main(String[] args) {
      Student[] students = Datasource.loadDataAsArray();

      System.out.println(students[0].equals(students[1]));

      MyComparing.print(students);
      System.out.println("Sort by number");
      Arrays.sort(students);
      MyComparing.print(students);

      List<Student> students1 = Datasource.loadDataAsList();
      MyComparing.print(students1);
      System.out.println("Sort by number");
      Collections.sort(students1);
      MyComparing.print(students);

      //pouziti Comparator interface
      System.out.println("Sort by firstname");
      Arrays.sort(students, new ComparatorByFirstName());
      MyComparing.print(students);

      System.out.println("Sort by lastname");
      Arrays.sort(students, new Comparator<Student>() { //anonymni trida implementujici Comparator interface
         @Override
         public int compare(Student o1, Student o2) {
            Collator col = Collator.getInstance(new Locale("cs","CZ"));
            return col.compare(o1.getLastName(), o2.getLastName());
         }
      });
      MyComparing.print(students);

      System.out.println("Sort by average grade");
      Arrays.sort(students, (Student o1, Student o2) -> o1.averageGrade().compareTo(o2.averageGrade()));
      MyComparing.print(students);
   }
}
