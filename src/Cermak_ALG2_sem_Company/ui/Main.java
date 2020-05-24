package Cermak_ALG2_sem_Company.ui;

import Cermak_ALG2_sem_Company.app.App;
import Cermak_ALG2_sem_Company.app.OptionData;

import java.util.Scanner;

public class Main {
   private static Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) {
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
}
