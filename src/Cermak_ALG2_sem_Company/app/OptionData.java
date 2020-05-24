package Cermak_ALG2_sem_Company.app;

/**
 * Container class for data emitted by an option
 */
public class OptionData {
   /**
    * A message
    */
   private String message;

   /**
    * An exit signal status
    */
   private Boolean quit;

   /**
    * Static method to create empty option data
    *
    * @return Empty option data
    */
   public static OptionData empty() {
      return new OptionData(null, false);
   }

   public OptionData(String message, Boolean quit) {
      this.message = message;
      this.quit = quit;
   }

   public String getMessage() {
      return message;
   }

   public Boolean skipPrint() {
      return message == null || message.isEmpty();
   }

   public Boolean quitProgram() {
      return quit;
   }
}
