package Cermak_ALG2_sem_Company.app;

public abstract class Option {
   public final static String PACKAGE = "Cermak_ALG2_sem_Company.app.options";

   public abstract void applyParams(String[] params);

   public abstract OptionData applyOnCompany(Company company);
}
