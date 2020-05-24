package Cermak_ALG2_sem_Company.utils;

public interface AppLogger {
   public void info(String... values);

   public void debug(String... values);

   public void error(String... values);

   public AppLogger with(String key, String value);
}
