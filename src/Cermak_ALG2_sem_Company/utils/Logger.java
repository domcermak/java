package Cermak_ALG2_sem_Company.utils;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Logger implementing AppLogger interface
 */
public class Logger implements AppLogger {
   /**
    * Destination of logged data
    */
   private File logFile;

   /**
    * Current log level
    */
   private String logLevel;

   /**
    * Current log extensions
    */
   private ArrayList<String> withExtensions;

   /**
    * Array of permitted log levels
    */
   public final String[] PERMITTED_LOG_LEVELS = {"debug", "info", "error"};

   /**
    * Constructor
    * @param config Configuration for Logger
    * @return An instance of Logger
    */
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

   @Override
   public void error(String... values) {
      log("error", values);
   }

   @Override
   public Logger with(String key, String value) {
      Logger newLogger = new Logger(this.logLevel, this.logFile.getPath());

      newLogger.withExtensions.addAll(this.withExtensions);
      newLogger.withExtensions.add(key + "=\"" + value + "\" ");

      return newLogger;
   }

   /**
    * Constructor
    * @param level Log level
    * @param logFilepath Destination filepath
    * @throws IllegalArgumentException
    */
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

   /**
    * Validates that destination is not a directory
    * @param file destination file
    */
   private void validateLogFile(File file) {
      if (file.exists() && file.isDirectory()) {
         throw new IllegalArgumentException(file.getPath() + " is a directory");
      }
   }

   /**
    * Validates log level
    * @param level Log level
    */
   private void validateLogLevel(String level) {
      for (String permittedLevel : PERMITTED_LOG_LEVELS) {
         if (permittedLevel.equals(level)) {
            return;
         }
      }

      throw new InvalidLogLevelException(level + " level is not permitted");
   }

   /**
    * Writes log data in destination file
    * @param line Line of data
    * @throws IOException
    */
   private void writeInLogFile(String line) throws IOException {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
         writer.write(line);
      }
   }

   /**
    * Check if data can be written into log file depending on log level
    * @param level Log level
    * @return Conclusion if log is allowed
    */
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

   /**
    * Creates a log message
    * @param level Log level
    * @param values Pairs to be logged
    * @return Built message
    */
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

   /**
    * Build extensions to be logged
    * @return Extensions in a string
    */
   private String withExtensions() {
      StringBuilder builder = new StringBuilder();

      for (String extension : this.withExtensions) {
         builder.append(extension);
      }

      return builder.toString();
   }

   /**
    * Created prependers to log like level and current time
    * @param level Log level
    * @return Build message
    */
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
}
