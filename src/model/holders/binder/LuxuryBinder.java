package src.model.holders.binder;

import src.model.card.Card;

public class LuxuryBinder extends SellableBinder {

    private double value;

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

    public boolean setNewValue(double newValue) {
        if (newValue >= this.getValue()) {
            this.value = newValue;
            return true;
        }
        return false;
    }

    public double getValueWithHandlingFee() {
        return this.value * 1.1;
    }

    @Override
    public void setCards(Card[] cards) {
        super.setCards(cards);
        value = getValue();
    }

    public double getCurrentValue() {
        return this.value;
    }
}
