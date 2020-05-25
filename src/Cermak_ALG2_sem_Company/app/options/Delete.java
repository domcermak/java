package Cermak_ALG2_sem_Company.app.options;

import Cermak_ALG2_sem_Company.app.Company;
import Cermak_ALG2_sem_Company.app.Option;
import Cermak_ALG2_sem_Company.app.OptionData;
import Cermak_ALG2_sem_Company.app.Employee;

import java.security.InvalidParameterException;
import java.util.Arrays;

/**
 * Class to delete user by id
 */
public class Delete extends Option {
   private Integer id;

   @Override
   public void applyParams(String[] params) {
      if (!areValidParams(params)) {
         throw new InvalidParameterException("Invalid parameters");
      }

      this.id = Integer.parseInt(params[0]);
   }

   @Override
   public OptionData applyOnCompany(Company company) {
      for (Employee employee : company.getEmployees()) {
         if (employee.getId().equals(id)) {
            company.getEmployees().remove(employee);
            logger.info("deleted", id.toString());

            return new OptionData("Employee deleted", false);
         }
      }
      logger.error("not_found", id.toString());

      return new OptionData("Employee with id " + id + " was not found", false);
   }

   /**
    * Validation function for user params
    *
    * @param params Params passed along the option
    * @return Validation state
    */
   private Boolean areValidParams(String[] params) {
      if (params.length != 1) {
         return false;
      }

      try {
         Integer.parseInt(params[0]);
         return true;
      } catch (Exception e) {
         logger.error("invalid_params", Arrays.toString(params));
         return false;
      }
   }
}
