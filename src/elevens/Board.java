package elevens;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {
   private ArrayList<Card> displayed;
   private Deck deck;

   public static ArrayList<Integer> loadSelectedCardsFromConsole(Scanner scanner) {
      Integer count = scanner.nextInt();

      if (count < 2 || count > 3) {
         StringBuilder builder = new StringBuilder("Invalid count of selected cards: ");

         builder.append(count);
         builder.append("\n");

         throw new IllegalArgumentException(builder.toString());
      }

      ArrayList<Integer> selectedCardNumbers = new ArrayList<>();
      for (int i = 0; i < count; i++) {
         selectedCardNumbers.add(scanner.nextInt());
      }

      return selectedCardNumbers;
   }

   public static String greetings() {
      StringBuilder builder = new StringBuilder("****************** Elevens ******************\n");

      builder.append("\nYou won!\n");
      builder.append("*********************************************\n");

      return builder.toString();
   }

   public boolean isEmpty() {
      return displayed.isEmpty();
   }

   public static Board init() throws IllegalAccessException {
      return new Board();
   }

   public String display() {
      StringBuilder builder = new StringBuilder("****************** Elevens ******************\n");

      for (Integer i = 1; i <= displayed.size(); i++) {
         builder.append(i);
         builder.append(". ");
         builder.append(displayed.get(i - 1).toString());
         builder.append("\n");
      }
      builder.append("*********************************************\n");
      builder.append("Pick 2 or 3 cards to make 11 points.\n");
      builder.append("Type count of selected cards and then their numbers:\n");

      return builder.toString();
   }

   public void processSelectedCards(ArrayList<Integer> cardNumbers) throws IllegalAccessException {
      for (Integer n : cardNumbers) {
         if (n < 1 || n > displayed.size()) {
            throw new IllegalArgumentException("Invalid card number: " + n);
         }
      }

      ArrayList<Card> selectedCards = new ArrayList<>();
      for (Integer i : cardNumbers) {
         selectedCards.add(displayed.get(i - 1));
      }

      if (Card.canCombine(selectedCards)) {
         for (Card card : selectedCards) {
            displayed.remove(card);
         }
         try {
            displayed.addAll(deck.pick(selectedCards.size()));
         } catch (Exception ignored) {}
      } else {
         throw new IllegalAccessException("Cannot combine cards: " + selectedCards.toString() + "\n");
      }
   }

   private Board() throws IllegalAccessException {
      this.displayed = new ArrayList<>();
      this.deck = Deck.initFull();
      this.displayed = deck.pick(7);
   }
}
