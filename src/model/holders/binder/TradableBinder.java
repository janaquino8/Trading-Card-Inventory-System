package src.model.holders.binder;

import src.model.card.Card;

/**
 * TradableBinder
 * Represents a tradable binder
 */
public abstract class TradableBinder extends Binder {
    /**
     * TradableBinder
     * @param name name of the binder to be created
     * Constructs a new tradable binder with the specified name.
     */
    public TradableBinder(String name) {
        super(name);
    }

    /**
     * trade
     * @param outgoingCardIndex the index of the card to be removed from the binder
     * @param incomingCard card to be added to the binder
     * Executes a trade between a card in the binder and a card outside the collection
     */
    public void trade(int outgoingCardIndex, Card incomingCard) {
        if (this.getCard(outgoingCardIndex) != incomingCard) {
            this.setCard(outgoingCardIndex, incomingCard);
        }
    }

}
