package Cermak_ALG2_sem_Company.app;

import Cermak_ALG2_sem_Company.app.options.NoopOption;
import Cermak_ALG2_sem_Company.utils.AppLogger;

import java.security.InvalidParameterException;
import java.util.Arrays;

/**
 * Options parser class
 */
public class OptionsParser {
   /**
    * App log context
    */
   private AppLogger logger;

   /**
    * Constructor
    */
   public OptionsParser(AppLogger logger) {
      this.logger = logger;
   }

   /**
    * Method parsing input from user
    *
    * @param line Input line
    * @return An option selected by user
    */
   public Option parse(String line) {
      String[] params = line.split(" +");
      if (params[0].isEmpty()) {
         logger.debug("parse", "empty line");
         return new NoopOption();
      }
      String rawOptionName = params[0];
      char firstLetter = Character.toUpperCase(params[0].charAt(0));
      String optionName = Option.PACKAGE + "." + firstLetter + params[0].substring(1);

      logger.debug("option_name", rawOptionName);
      params = Arrays.copyOfRange(params, 1, params.length);
      logger.debug("params", Arrays.toString(params));
      try {
         Class optionClass = Class.forName(optionName);
         Option option = (Option) optionClass.newInstance();

         option.setLogger(logger.with("option", rawOptionName));
         option.applyParams(params);
         return option;
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
         logger.error("invalid_option", rawOptionName);
         throw new RuntimeException("Option `" + rawOptionName + "` is invalid. Try <help>");
      } catch (InvalidParameterException e) {
         logger.error("invalid_parameters", Arrays.toString(params));
         throw new RuntimeException("Invalid parameters for option `" + rawOptionName + "`. Try <help>");
      }
   }
}
