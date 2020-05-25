package Cermak_ALG2_sem_Company.app.options;

import Cermak_ALG2_sem_Company.app.Company;
import Cermak_ALG2_sem_Company.app.Option;
import Cermak_ALG2_sem_Company.app.OptionData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

/**
 * Option to export current state of the company to the file
 */
public class Export extends Option {
   @Override
   public void applyParams(String[] params) {}

   @Override
   public OptionData applyOnCompany(Company company) {
      ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
      String filepath = "src/Cermak_ALG2_sem_Company/data/export.yaml";
      try {
         mapper.writeValue(
               new File(filepath),
               company
         );
      } catch (IOException e) {
         logger.error("export", "unable");
         return new OptionData("Unable to export: " + e.getMessage(),false);
      }
      logger.debug("export", "exported");
      return new OptionData("Company exported to " + filepath,false);
   }
}
