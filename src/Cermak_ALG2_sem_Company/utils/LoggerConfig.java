package Cermak_ALG2_sem_Company.utils;

/**
 * Container class to keep log configuration
 */
public class LoggerConfig {
   /**
    * Configuration level and filepath
    */
   private String level, filepath;

   public String getLevel() {
      return level;
   }

   public String getFilepath() {
      return filepath;
   }

   public void setLevel(String level) {
      this.level = level;
   }

   public void setFilepath(String filepath) {
      this.filepath = filepath;
   }
}
