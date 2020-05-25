package Cermak_ALG2_sem_Company.ui;

import Cermak_ALG2_sem_Company.app.App;
import Cermak_ALG2_sem_Company.app.OptionData;

import java.io.*;
import java.util.Scanner;

/**
 * Main class
 */
public class Main {
   private static Scanner scanner = new Scanner(System.in);

   /**
    * Entry method
    */
   public static void main(String[] args) {
      //writeLogLevel("debug");

      App app;
      try {
         app = new App();
      } catch (Exception e) {
         System.out.println(e.getMessage());
         return;
      }

      System.out.println(Options.helpText());
      while (app.isRunning()) {
         System.out.print("> ");
         try {
            String line = scanner.nextLine();
            OptionData data = app.process(line);

            if (!data.skipPrint()) {
               System.out.println(data.getMessage());
            }
         } catch (Exception e) {
            System.out.println(e.getMessage());
         }
      }
   }

   private static void writeLogLevel(String level) {
      String filepath = "src/Cermak_ALG2_sem_Company/data/logger.config";
      try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filepath)))) {
         for (char c : level.toCharArray()) {
            out.writeChar(c);
         }
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
