package Cermak_ALG2_sem_Company.app;

public class OptionData {
   private String message;
   private Boolean quit;

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
