package Cermak_ALG2_sem_Company.app;

import Cermak_ALG2_sem_Company.app.options.NoopOption;
import Cermak_ALG2_sem_Company.utils.AppLogger;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class OptionsParser {
   private AppLogger logger;

   public OptionsParser(AppLogger logger) {
      this.logger = logger;
   }

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
