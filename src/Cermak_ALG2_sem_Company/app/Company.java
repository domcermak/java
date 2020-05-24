package Cermak_ALG2_sem_Company.app;

import java.util.List;

/**
 * Company class
 *
 * It has structure to be compatible with YAML parser
 */
public class Company {
   /**
    * Company name
    */
   private String name;

   /**
    * List of employees
    */
   private List<Employee> employees;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<Employee> getEmployees() {
      return employees;
   }

   public void setEmployees(List<Employee> employees) {
      this.employees = employees;
   }
}
