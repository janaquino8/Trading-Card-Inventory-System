package src.model.holders.binder;

import src.model.card.Card;
import src.model.holders.Holder;
import src.model.holders.NameSorter;

import java.util.*;

/**
 * Binder
 * Represents a binder
 */
public abstract class Binder extends Holder {
    /**
     * Binder
     * @param name name of the created binder
     * Constructor to construct a Binder object given its name
     */
    public Binder(String name) {
        super(name, 20);
        MAX_COUNT = 20;
    }

    /**
     * sort
     * Sorts cards in alphabetical order of name
     */
    public void sort() {
        Arrays.sort(this.getCards(), new NameSorter());
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
