package elevens;

import java.util.ArrayList;
import java.util.Arrays;

public class Card {
   private String symbol, value;
   private Integer valuePoints;

   public static ArrayList<Card> allCards() {
      ArrayList<Card> list = new ArrayList<>();

      for (String symbol : symbols()) {
         for (String value : values()) {
            list.add(new Card(symbol, value));
         }
      }

      return list;
   }

   public static boolean canCombine(ArrayList<Card> cards) {
      if (cards.size() < 2 || cards.size() > 3) {
         return false;
      }

      if (cards.size() == 2) {
         return cards.get(0).valuePoints + cards.get(1).valuePoints == 11;
      }

      for (Card card : cards) {
         if (!royalValues().contains(card.value)) {
            return false;
         }
      }

      return true;
   }

   public static ArrayList<String> symbols() {
      ArrayList<String> list = new ArrayList<>();
      String[] array = {"spades", "hearts", "diamonds", "clubs"};

      list.addAll(Arrays.asList(array));


      return list;
   }

   public static ArrayList<String> values() {
      ArrayList<String> list = new ArrayList<>();
      String[] array = {
            "A", "2", "3", "4", "5", "6", "7", "8", "9",
      };

      list.addAll(Arrays.asList(array));
      list.addAll(royalValues());

      return list;
   }

   private static ArrayList<String> royalValues() {
      ArrayList<String> list = new ArrayList<>();
      String[] array = {"J", "Q", "K"};

      list.addAll(Arrays.asList(array));

      return list;
   }

   public Card(String symbol, String value) {
      if (!symbols().contains(symbol) || !values().contains(value)) {
         throw new IllegalArgumentException("Invalid card " + value + " of " + symbol);
      }

      this.symbol = symbol;
      this.value = value;
      this.valuePoints = calcValuePointsFrom(value);
   }

   @Override
   public String toString() {
      return value + " of " + symbol;
   }

   private Integer calcValuePointsFrom(String value) {
      if (royalValues().contains(value)) {
         return 10;
      }
      return values().indexOf(value) + 1;
   }
}
