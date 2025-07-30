package src.model.holders.deck;

import src.model.card.Card;
import src.model.holders.Holder;

/**
 * Deck representation in the program, which also serves as a normal/non-sellable deck.
 *
 * <p>A deck is a type of holder that can store up to 10 cards at a time, excluding duplicates.
 */
public class Deck extends Holder {
    /**
     * Constructor to construct a Deck object given its name
     * @param name name of the created deck
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
