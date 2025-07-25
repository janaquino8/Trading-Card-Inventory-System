package src.model.holders.deck;

import src.model.card.Card;

public class SellableDeck extends Deck {
    public SellableDeck(String name) {
        super(name);
    }

    public double getValue() {
        double value = 0;

        for (Card c : cards) {
            value += c.getFinalValue();
        }

        return value;
    }
}
