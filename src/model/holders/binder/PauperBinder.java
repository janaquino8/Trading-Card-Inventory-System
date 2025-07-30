package src.model.holders.binder;

import src.model.card.Card;

/**
 * Represents a pauper binder in the program.
 *
 * <p>This type of binder can be sold and can store only cards of common and uncommon rarities.
 */
public class PauperBinder extends SellableBinder {
    /**
     * Constructs a new pauper binder with the specified name.
     * @param name name of the binder to be created
     */
    public PauperBinder(String name) {
        super(name);
        this.ID = 3;
    }

    @Override
    public boolean addCard(Card card) {
        if (card.getRarity().getName().equals("Common") || card.getRarity().getName().equals("Uncommon")) {
            return super.addCard(card);
        }
        return false;
    }
}
