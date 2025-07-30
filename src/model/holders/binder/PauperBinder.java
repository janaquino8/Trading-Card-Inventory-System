package src.model.holders.binder;

import src.model.card.Card;

public class PauperBinder extends SellableBinder {

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
