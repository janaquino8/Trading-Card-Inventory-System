package src.model.holders.binder;

import src.model.card.Card;

/**
 * Represents a sellable binder in the program.
 *
 * <p>This type of binder can be sold for the total value of its cards. After a trade, it, along with its contents,
 * are deleted.
 */
public abstract class SellableBinder extends Binder {
    /**
     * Constructs a new sellable binder with the specified name.
     * @param name name of the binder to be created
     */
    public SellableBinder(String name) {
        super(name);
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
