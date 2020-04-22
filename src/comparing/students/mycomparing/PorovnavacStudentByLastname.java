package comparing.students.mycomparing;

import comparing.students.Student;

import java.text.Collator;
import java.util.Locale;

public class PorovnavacStudentByLastname implements ComparatorInterface {
   @Override
   public boolean bigger(Object o1, Object o2) {
      Collator col = Collator.getInstance(new Locale("cs", "CZ"));
      return col.compare(((Student) o1).getLastName(), ((Student) o2).getLastName()) > 0;
   }
}