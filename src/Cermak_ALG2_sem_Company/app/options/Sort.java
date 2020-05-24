package Cermak_ALG2_sem_Company.app.options;

import Cermak_ALG2_sem_Company.app.*;

import java.security.InvalidParameterException;
import java.util.Collections;

/**
 * Option used to sort employees
 */
public class Sort extends Option {
   private String flag;

   @Override
   public void applyParams(String[] params) {
      if (!areValidParams(params)) {
         throw new InvalidParameterException("Invalid parameters");
      }
      this.flag = params[0];
   }

   @Override
   public OptionData applyOnCompany(Company company) {
      StringBuilder builder = new StringBuilder();
      switch (flag) {
         case "--age":
            builder.append("Sorted by age:\n");
            company.getEmployees().sort(new SortByAge());
            break;
         case "--id":
            builder.append("Sorted by id:\n");
            company.getEmployees().sort(new SortById());
            break;
         default: // --first_name
            builder.append("Sorted by first name:\n");
            Collections.sort(company.getEmployees());
      }

      List list = new List();
      builder.append(list.applyOnCompany(company).getMessage());

      return new OptionData(builder.toString(), false);
   }

   /**
    * Validation function for user params
    * @param params Params passed along the option
    * @return Validation state
    */
   private Boolean areValidParams(String[] params) {
      if (params.length != 1) {
         return false;
      }
      return params[0].equals("--age") || params[0].equals("--first_name") || params[0].equals("--id");
   }
}
