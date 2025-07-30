package src.model.holders.binder;

import src.model.card.Card;

public class CollectorBinder extends TradableBinder {

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
