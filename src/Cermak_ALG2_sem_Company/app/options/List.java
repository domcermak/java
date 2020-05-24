package Cermak_ALG2_sem_Company.app.options;

import Cermak_ALG2_sem_Company.app.Company;
import Cermak_ALG2_sem_Company.app.Option;
import Cermak_ALG2_sem_Company.app.OptionData;
import Cermak_ALG2_sem_Company.app.Employee;

public class List extends Option {
   @Override
   public void applyParams(String[] params) {
   }

   @Override
   public OptionData applyOnCompany(Company company) {
      StringBuilder builder = new StringBuilder();

      builder.append("Company name: \"");
      builder.append(company.getName());
      builder.append("\"\n");
      builder.append("Employees:\n");
      for (Employee employee : company.getEmployees()) {
         builder.append("- id: ");
         builder.append(employee.getId());
         builder.append("\n  first_name: \"");
         builder.append(employee.getFirstName());
         builder.append("\"\n  surname: \"");
         builder.append(employee.getSurname());
         builder.append("\"\n  sex: \"");
         builder.append(employee.getSex());
         builder.append("\"\n  age: ");
         builder.append(employee.Age());
         builder.append("\n  position: \"");
         builder.append(employee.getPosition());
         builder.append("\"\n");
      }

      return new OptionData(builder.toString(), false);
   }
}
