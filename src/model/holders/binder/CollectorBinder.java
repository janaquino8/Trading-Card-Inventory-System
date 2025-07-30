package src.model.holders.binder;

import src.model.card.Card;

/**
 * Represents a collector binder in the program.
 *
 * <p>This type of binder can execute trades and can store only cards of non-normal variants.
 */
public class CollectorBinder extends TradableBinder {
    /**
     * Constructs a new collector binder with the specified name.
     * @param name name of the binder to be created
     */
    public CollectorBinder(String name) {
        super(name);
        this.ID = 2;
    }

    @Override
    public boolean addCard(Card card) {
        if (!card.getVariant().getName().equals("Normal")) {
            return super.addCard(card);
        }
        return false;
    }
}
