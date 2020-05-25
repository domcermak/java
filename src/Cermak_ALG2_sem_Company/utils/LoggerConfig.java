package Cermak_ALG2_sem_Company.utils;

import java.io.*;

/**
 * Container class to keep log configuration
 */
public class LoggerConfig {
   /**
    * Configuration level and filepath
    */
   private String level, filepath;

   public static LoggerConfig InitFromFile(String filepath) throws IOException {
      StringBuilder level = new StringBuilder();
      try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(filepath)))) {
         while (true) {
            level.append(in.readChar());
         }
      } catch (EOFException e) {
      }

      return new LoggerConfig(level.toString(), "src/Cermak_ALG2_sem_Company/log/app.log");
   }

   public LoggerConfig() {
   }

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

   private LoggerConfig(String level, String filepath) {
      this.filepath = filepath;
      this.level = level;
   }
}
