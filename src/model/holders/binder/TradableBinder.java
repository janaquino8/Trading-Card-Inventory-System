package src.model.holders.binder;

import src.model.card.Card;

/**
 * Represents a tradable binder in the program, which also serves as a non-curated binder.
 *
 * <p>This type of binder can execute trades between one of its cards and a card that is outside the collection.
 */
public class TradableBinder extends Binder {
    /**
     * Constructs a new non-curated/tradable binder with the specified name.
     * @param name name of the binder to be created
     */
    public TradableBinder(String name) {
        super(name);
        this.ID = 1;
    }

    /**
     * Executes a trade between a card in the binder and a card outside the collection.
     * @param outgoingCardIndex the index of the card to be removed from the binder
     * @param incomingCard card to be added to the binder
     */
    public void trade(int outgoingCardIndex, Card incomingCard) {
        if (this.getCard(outgoingCardIndex) != incomingCard) {
            this.setCard(outgoingCardIndex, incomingCard);
        }
    }
}
