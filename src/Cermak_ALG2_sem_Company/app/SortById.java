package Cermak_ALG2_sem_Company.app;

import java.util.Comparator;

/**
 * Comparator to sort employees by id
 */
public class SortById implements Comparator<Employee> {
   @Override
   public int compare(Employee o1, Employee o2) {
      return o1.getId().compareTo(o2.getId());
   }
}
