package src.model.holders.deck;

import src.model.holders.Holder;

/**
 * Deck
 * Represents a deck
 */
public abstract class Deck extends Holder {
    /**
     * Deck
     * @param name name of the created deck
     * Constructor to construct a Deck object given its name
     */
    public Deck(String name) {
        super(name, 10);
        MAX_COUNT = 10;
    }
}
