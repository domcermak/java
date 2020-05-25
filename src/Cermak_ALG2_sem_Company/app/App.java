package Cermak_ALG2_sem_Company.app;

import Cermak_ALG2_sem_Company.utils.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

/**
 * App class
 */
public class App {
   /**
    * Logger
    */
   private AppLogger logger;
   /**
    * Loaded company
    */
   private Company company;
   /**
    * State if app is still running
    */
   private Boolean running;

   /**
    * Constructor
    * <p>
    * It loads company data and log configuration from files
    *
    * @throws IOException
    */
   public App() throws IOException {
      ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
      LoggerConfig config = LoggerConfig.InitFromFile("src/Cermak_ALG2_sem_Company/data/logger.config");

      this.logger = Logger.Init(config);
      this.company = mapper.readValue(
            new File("src/Cermak_ALG2_sem_Company/data/company.yaml"),
            Company.class
      );
      this.running = true;

      logEmployees(logger.with("company_name", company.getName()));
   }

   /**
    * Method processing user input
    *
    * @param line Input by user
    * @return Data returned by selected option
    */
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

   /**
    * @return State if the app is still running
    */
   public Boolean isRunning() {
      return running;
   }

   /**
    * Log employees
    *
    * @param ctx Log context
    */
   private void logEmployees(AppLogger ctx) {
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
