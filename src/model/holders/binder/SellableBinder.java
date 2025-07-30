package src.model.holders.binder;

import src.model.card.Card;

public abstract class SellableBinder extends Binder {

    public SellableBinder(String name) {
        super(name);
    }

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
