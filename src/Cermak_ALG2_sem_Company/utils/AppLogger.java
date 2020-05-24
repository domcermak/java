package Cermak_ALG2_sem_Company.utils;

/**
 * AppLogger interface
 *
 * Used for logging in the app
 */
public interface AppLogger {
   /**
    * Logs data with `info` level
    * @param values Pairs of values to be logged
    */
   public void info(String... values);

   /**
    * Logs data with `debug` level
    * @param values Pairs of values to be logged
    */
   public void debug(String... values);

   /**
    * Logs data with `error` level
    * @param values Pairs of values to be logged
    */
   public void error(String... values);

   /**
    * Creates new logger with extended scope
    */
   public AppLogger with(String key, String value);
}
