package comparing.students.mycomparing;

import comparing.students.Student;

public class PorovnavacStudentByFirstname implements ComparatorInterface {
   @Override
   public boolean bigger(Object o1, Object o2) {
      return ((Student) o1).getFirstName().compareTo(((Student) o2).getFirstName()) > 0;
   }
}