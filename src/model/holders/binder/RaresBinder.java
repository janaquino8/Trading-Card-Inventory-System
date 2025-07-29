package src.model.holders.binder;

import src.model.card.Card;

public class RaresBinder extends SellableBinder {
    public RaresBinder(String name) {
        super(name);
    }

    @Override
    public boolean addCard(Card card) {
        if (card.getRarity().getName().equals("Rare") || card.getRarity().getName().equals("Legendary")) {
            return super.addCard(card);
        }
        return false;
    }

    public double getValueWithHandlingFee() {
        return this.getValue() * 1.1;
    }
}
