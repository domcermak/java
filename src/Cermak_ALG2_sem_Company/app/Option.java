package Cermak_ALG2_sem_Company.app;

/**
 * Abstract base class for options
 */
public abstract class Option {
   /**
    * A Constant with options package name
    */
   public final static String PACKAGE = "Cermak_ALG2_sem_Company.app.options";

   /**
    * API method to apply params to an option
    * @param params Params to be applied
    */
   public abstract void applyParams(String[] params);

   /**
    * API method for option to work with company
    * @param company A company
    * @return Data created by the operation
    */
   public abstract OptionData applyOnCompany(Company company);
}
