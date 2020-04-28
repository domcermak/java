package elevens;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
   private ArrayList<Card> cards;

   public static Deck initFull() {
      return new Deck();
   }

   public Deck() {
      this.cards = Card.allCards();
      Collections.shuffle(cards);
   }

   public boolean isEmpty() {
      return this.cards.isEmpty();
   }

   public ArrayList<Card> pick(Integer count) throws IllegalAccessException, IllegalArgumentException {
      if (count < 1) {
         throw new IllegalArgumentException("you cannot pick " + count + " card");
      }

      ArrayList<Card> picked = new ArrayList<>();

      for(int i = 0; i < count; i++) {
         picked.add(pick());
      }

      return picked;
   }

   public Card pick() throws IllegalAccessException {
      if (isEmpty()) {
         throw new IllegalAccessException("You cannot pick a card, the deck is empty");
      }

      Card card = cards.get(0);
      cards.remove(0);

      return card;
   }
}
