package comparing.students.mycomparing;

import comparing.students.Student;

public class PorovnavacStudentByAverageGrade implements ComparatorInterface {
   @Override
   public boolean bigger(Object o1, Object o2) {
      return ((Student) o1).averageGrade().compareTo(((Student) o2).averageGrade()) > 0;
   }
}
