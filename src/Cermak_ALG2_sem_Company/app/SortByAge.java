package Cermak_ALG2_sem_Company.app;

import java.util.Comparator;

public class SortByAge implements Comparator<Employee> {
   @Override
   public int compare(Employee o1, Employee o2) {
      return o1.Age().compareTo(o2.Age());
   }
}
