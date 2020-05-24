package Cermak_ALG2_sem_Company.utils;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Logger implements AppLogger {
   private File logFile;
   private String logLevel;
   private ArrayList<String> withExtensions;
   public final String[] PERMITTED_LOG_LEVELS = {"debug", "info", "error"};

   public static Logger Init(String level, String logFilepath) {
      return new Logger(level, logFilepath);
   }

   public static Logger Init(LoggerConfig config) {
      return new Logger(config.getLevel(), config.getFilepath());
   }

   @Override
   public void info(String... values) {
      log("info", values);
   }

   @Override
   public void debug(String... values) {
      log("debug", values);
   }

   public void error(String... values) {
      log("error", values);
   }

   @Override
   public Logger with(String key, String value) {
      Logger newLogger = Logger.Init(this.logLevel, this.logFile.getPath());

      newLogger.withExtensions.addAll(this.withExtensions);
      newLogger.withExtensions.add(key + "=\"" + value + "\" ");

      return newLogger;
   }

   private Logger(String level, String logFilepath) throws IllegalArgumentException {
      File file = new File(logFilepath);

      validateLogFile(file);
      validateLogLevel(level);

      this.logFile = file;
      this.logLevel = level;
      this.withExtensions = new ArrayList<>();
   }

   private void log(String level, String[] values) {
      validateLogLevel(level);
      if (isAllowedToLog(level)) {
         String message = createLogMessage(level, values);
         try {
            writeInLogFile(message);
         } catch (Exception e) {
            // noop
         }
      }
   }

   private void validateLogFile(File file) {
      if (file.exists() && file.isDirectory()) {
         throw new IllegalArgumentException(file.getPath() + " is a directory");
      }
   }

   private void validateLogLevel(String level) {
      for (String permittedLevel : PERMITTED_LOG_LEVELS) {
         if (permittedLevel.equals(level)) {
            return;
         }
      }

      throw new InvalidLogLevelException(level + " level is not permitted");
   }

   private void writeInLogFile(String line) throws IOException {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
         writer.write(line);
      }
   }

   private Boolean isAllowedToLog(String level) {
      switch (level) {
         case "debug":
            return this.logLevel.equals(level);
         case "info":
            return this.logLevel.equals(level) || this.logLevel.equals("debug");
         default: // error
            return true;
      }
   }

   private String createLogMessage(String level, String[] values) {
      StringBuilder builder = new StringBuilder();

      builder.append(staticLogPrependers(level));
      builder.append(withExtensions());

      for (Integer i = 0; i < values.length; i++) {
         builder.append(values[i]);
         if (i % 2 == 0) {
            builder.append("=\"");
         } else {
            builder.append("\" ");
         }
      }
      builder.append("\n");

      return builder.toString();
   }

   private String withExtensions() {
      StringBuilder builder = new StringBuilder();

      for (String extension : this.withExtensions) {
         builder.append(extension);
      }

      return builder.toString();
   }

   private String staticLogPrependers(String level) {
      StringBuilder builder = new StringBuilder();
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      LocalDateTime now = LocalDateTime.now();

      builder.append("log_level=\"");
      builder.append(level);
      builder.append("\" time=\"");
      builder.append(dtf.format(now));
      builder.append("\" ");

      return builder.toString();
   }

   static class InvalidLogLevelException extends IllegalArgumentException {
      public InvalidLogLevelException(String message) {
         super(message);
      }
   }
}
