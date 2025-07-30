package src.model.holders.binder;

import src.model.card.Card;

/**
 * Represents a luxury binder in the program.
 *
 * <p>This type of binder can be sold and can store only cards of non-normal variants. The sale price of this
 * binder includes a handling fee corresponding to 10% of the original binder value. The user may also decide to
 * manually set the price of the binder as long as it is greater than or equal to its orgiginal value.
 */
public class LuxuryBinder extends SellableBinder {
    private double value;

    /**
     * Constructs a new luxury binder with the specified name.
     * @param name name of the binder to be created
     */
    public LuxuryBinder(String name) {
        super(name);
        this.value = 0;
        this.ID = 5;
    }

    @Override
    public boolean addCard(Card card) {
        if (card.getVariant().getName().equals("Normal")) {
            return false;
        }
        if (super.addCard(card)) {
            this.value = getValue();
            return true;
        }
        return false;
    }

    @Override
    public void removeCard(int index) {
        super.removeCard(index);
        this.value = getValue();
    }

    /**
     * Sets the new value of the binder to the value set by the user
     * @param newValue the value set by the user
     * @return true if the new value is successfully set, false otherwise
     */
    public boolean setNewValue(double newValue) {
        if (newValue >= this.getValue()) {
            this.value = newValue;
            return true;
        }
        return false;
    }

    /**
     * Gets the current value of the binder with a 10% handling fee applied.
     * @return the current value of the deck with the applied handling fee
     */
    public double getValueWithHandlingFee() {
        return this.value * 1.1;
    }

    @Override
    public void setCards(Card[] cards) {
        super.setCards(cards);
        value = getValue();
    }

    /**
     * Gets the current value of the binder.
     * @return the current value of the binder
     */
    public double getCurrentValue() {
        return this.value;
    }
}
