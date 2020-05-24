package Cermak_ALG2_sem_Company.utils;

/**
 * Custom exception thrown when log level is invalid
 */
public class InvalidLogLevelException extends IllegalArgumentException {
   public InvalidLogLevelException(String message) {
      super(message);
   }
}
