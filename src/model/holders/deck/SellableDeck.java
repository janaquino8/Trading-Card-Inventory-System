package src.model.holders.deck;

import src.model.card.Card;

public class SellableDeck extends Deck {
    public SellableDeck(String name) {
        super(name);
        this.ID = 2;
    }

    public double getValue() {
        double value = 0;

        for (Card c : cards) {
            if (c != null) {
                value += c.getFinalValue();
            }
        }

        return value;
    }
}
