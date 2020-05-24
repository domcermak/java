package Cermak_ALG2_sem_Company.app;

import Cermak_ALG2_sem_Company.utils.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;


public class App {
   private AppLogger logger;
   private Company company;
   private Boolean running;

   public App() throws IOException {
      ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
      LoggerConfig config = mapper.readValue(
            new File("src/Cermak_ALG2_sem_Company/data/logger.config.yaml"),
            LoggerConfig.class
      );

      this.logger = Logger.Init(config);
      this.company = mapper.readValue(
            new File("src/Cermak_ALG2_sem_Company/data/company.yaml"),
            Company.class
      );
      this.running = true;

      logEmployees(logger.with("company_name", company.getName()));
   }

   public OptionData process(String line) {
      logger.info("processing", line);

      Option option = (new OptionsParser(logger.with("parser", "OptionsParser"))).parse(line);
      OptionData data = option.applyOnCompany(company);
      if (data.quitProgram()) {
         logger.info("exit", "true");
         running = false;
      }

      return data;
   }

   public Boolean isRunning() {
      return running;
   }

   private void logEmployees(AppLogger ctx) throws IOException {
      for (Employee employee : company.getEmployees()) {
         ctx.debug(
               "first_name", employee.getFirstName(),
               "surname", employee.getSurname(),
               "sex", employee.getSex(),
               "birth_date", employee.birthDate().toString(),
               "position", employee.getPosition()
         );
      }
   }
}
