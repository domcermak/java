package Cermak_ALG2_sem_Company.app.options;

import Cermak_ALG2_sem_Company.app.Company;
import Cermak_ALG2_sem_Company.app.Option;
import Cermak_ALG2_sem_Company.app.OptionData;
import Cermak_ALG2_sem_Company.ui.Options;

/**
 * Option to show help text
 */
public class Help extends Option {
   @Override
   public void applyParams(String[] params) {
   }

   @Override
   public OptionData applyOnCompany(Company company) {
      return new OptionData(Options.helpText(), false);
   }
}
