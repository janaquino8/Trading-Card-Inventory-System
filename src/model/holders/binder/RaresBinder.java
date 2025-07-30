package src.model.holders.binder;

import src.model.card.Card;

/**
 * Represents a rares binder in the program.
 *
 * <p>This type of binder can be sold and can store only cards of rare and legendary rarities. The sale price of this
 * binder includes a handling fee corresponding to 10% of the original binder value.
 */
public class RaresBinder extends SellableBinder {
    /**
     * Constructs a new rares binder with the specified name.
     * @param name name of the binder to be created
     */
    public RaresBinder(String name) {
        super(name);
        this.ID = 4;
    }

    @Override
    public boolean addCard(Card card) {
        if (card.getRarity().getName().equals("Rare") || card.getRarity().getName().equals("Legendary")) {
            return super.addCard(card);
        }
        return false;
    }

    /**
     * Gets the value of the deck with a 10% handling fee applied.
     * @return the value of the deck with the applied handling fee
     */
    public double getValueWithHandlingFee() {
        return this.getValue() * 1.1;
    }
}
