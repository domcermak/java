package comparing.students;

import comparing.students.mycomparing.CompareInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Student implements CompareInterface, Comparable<Student> {
   private String firstName;
   private String lastName;
   private int studentNumber;
   private ArrayList<Integer> grades;

   public Student(String firstName, String lastName, int studentNumber, Integer... grades) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.studentNumber = studentNumber;
      this.grades = new ArrayList<>();
      this.addGrades(grades);
   }

   public void addGrade(Integer grade) {
      if (grade < 1 || grade > 5) {
         throw new IllegalArgumentException("Invalid grade " + grade);
      }
      grades.add(grade);
   }

   public void addGrades(Integer[] grades) {
      for (Integer grade : grades) {
         addGrade(grade);
      }
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public int getStudentNumber() {
      return studentNumber;
   }

   public Double averageGrade() {
      Double sum = 0.;

      for (Integer grade : grades) {
         sum += grade;
      }

      return sum / grades.size();
   }

   @Override
   public String toString() {
      return String.format("%10s%10s%10d", firstName, lastName, studentNumber) + " " + Arrays.toString(grades.toArray());
   }

   public boolean isBigger(Student student) {
      return this.studentNumber > student.studentNumber;
   }

   //potrebne pro MyComparing
   @Override
   public boolean isBigger(CompareInterface o) {
      return this.studentNumber > ((Student) o).studentNumber;
   }

   //potrebne pro Comparing
   @Override
   public int compareTo(Student o) {
      return this.studentNumber - o.studentNumber;
   }

   //pri zmene equals zmenit i hashCode
   @Override
   public int hashCode() {
      int hash = 3;
      hash = 97 * hash + Objects.hashCode(this.firstName);
      hash = 97 * hash + Objects.hashCode(this.lastName);
      hash = 97 * hash + this.studentNumber;
      return hash;
   }

   //default in Object
   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      final Student other = (Student) obj;
      if (this.studentNumber != other.studentNumber) {
         return false;
      }
      if (!Objects.equals(this.firstName, other.firstName)) {
         return false;
      }
      if (!Objects.equals(this.lastName, other.lastName)) {
         return false;
      }
      return Arrays.equals(this.grades.toArray(), other.grades.toArray());
   }
}