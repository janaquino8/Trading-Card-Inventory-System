package src.model.holders.deck;

import src.model.card.Card;
import src.model.holders.Holder;

/**
 * Deck
 * Represents a deck
 */
public class Deck extends Holder {
    /**
     * Deck
     * @param name name of the created deck
     * Constructor to construct a Deck object given its name
     */
    public Deck(String name) {
        super(name, 10);
        MAX_COUNT = 10;
        this.ID = 1;
    }

    @Override
    public boolean addCard(Card card) {
        if (findCard(card.getName()) == -1) {
            return super.addCard(card);
        }
        return false;
    }
}
