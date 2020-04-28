package elevens;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
   private static Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) {
      Board board;

      try {
         board = Board.init();
      } catch (Exception e) {
         System.out.println(e.getMessage());
         return;
      }

      ArrayList<Integer> options;
      while (!board.isEmpty()) {
         try {
            System.out.print(board.display());
            options = Board.loadSelectedCardsFromConsole(scanner);
            board.processSelectedCards(options);
         } catch (Exception e) {
            System.out.print(e.getMessage());
         }
      }

      System.out.print(Board.greetings());
   }
}
