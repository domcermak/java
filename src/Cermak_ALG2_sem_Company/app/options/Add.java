package Cermak_ALG2_sem_Company.app.options;

import Cermak_ALG2_sem_Company.app.Company;
import Cermak_ALG2_sem_Company.app.Option;
import Cermak_ALG2_sem_Company.app.OptionData;
import Cermak_ALG2_sem_Company.app.Employee;

import java.security.InvalidParameterException;
import java.util.Random;

/**
 * Class for creating new employee
 */
public class Add extends Option {
   private String firstName, surname, position, birthDate, sex;

   @Override
   public void applyParams(String[] params) {
      if (!areValidParams(params)) {
         throw new InvalidParameterException("Invalid parameters");
      }
      this.firstName = params[0];
      this.surname = params[1];
      this.sex = params[2];
      this.birthDate = params[3];
      this.position = params[4];
   }

   @Override
   public OptionData applyOnCompany(Company company) {
      Random rand = new Random();
      Employee newEmployee = new Employee();
      Integer newId = rand.nextInt();

      try {
         newEmployee.setId(newId);
         newEmployee.setFirstName(firstName);
         newEmployee.setSurname(surname);
         newEmployee.setSex(sex);
         newEmployee.setBirthDate(birthDate);
         newEmployee.setPosition(position);
      } catch (Exception e) {
         // noop
      }

      company.getEmployees().add(newEmployee);

      return new OptionData("New employee with id " + newId + " was created", false);
   }

   /**
    * Validation function for user params
    * @param params Params passed along the option
    * @return Validation state
    */
   private Boolean areValidParams(String[] params) {
      if (params.length != 5) {
         return false;
      }

      try {
         Employee tmp = new Employee();

         tmp.setFirstName(params[0]);
         tmp.setSurname(params[1]);
         tmp.setSex(params[2]);
         tmp.setBirthDate(params[3]);
         tmp.setPosition(params[4]);

         return true;
      } catch (Exception e) {
         return false;
      }
   }
}
