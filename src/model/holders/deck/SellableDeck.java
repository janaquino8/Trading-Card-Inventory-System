package src.model.holders.deck;

import src.model.card.Card;

/**
 * Represents a sellable deck in the program.
 *
 * <p>This type of deck can be sold for the total value of its cards. After a trade, it, along with its contents,
 * are deleted.
 */
public class SellableDeck extends Deck {
    public SellableDeck(String name) {
        super(name);
        this.ID = 2;
    }

    /**
     * Gets the total value of all of its cards.
     * @return total value of cards
     */
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
