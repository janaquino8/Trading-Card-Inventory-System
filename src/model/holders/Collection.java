package src.model.holders;

import src.model.card.Card;

import java.util.*;

/**
 * Represents the collection of the user.
 */
public class Collection {
    private ArrayList<Card> cards;

    /**
     * Constructor to construct a Collection object.
     */
    public Collection() {
        cards = new ArrayList<>();
    }

    /**
     * Creates a card without a given variant and adds it to the collection.
     * @param name name of the created card
     * @param rarity integer representing the rarity of the created card
     * @param baseValue base value of the created card
     */
    public void addCard(String name, int rarity, double baseValue) {
        this.getCards().add(new Card(name, rarity, baseValue, this.getCards().size()));
    }

    /**
     * Creates a card given its variant and adds it to the collection.
     * @param name name of the created card
     * @param rarity integer representing the rarity of the created card
     * @param variant integer representing the variant of the created card
     * @param baseValue base value of the created card
     */
    public void addCard(String name, int rarity, int variant, double baseValue) {
        this.getCards().add(new Card(name, rarity, variant, baseValue, this.getCards().size()));
    }

    /**
     * Finds a card in cards given a name.
     * @param name name of the card to be found
     * @return index of the card in cards, -1 if not found
     */
    public int findCard(String name) {
        for (int i = 0; i < this.getCards().size(); i++) {
            if (this.getCard(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds a card in cards given a card number.
     * @param cardNo card number of the card to be found
     * @return index of the card in cards, -1 if not found
     */
    public int findCard(int cardNo) {
        for (int i = 0; i < this.getCards().size(); i++) {
            if (this.getCard(i).getCardNo() == cardNo) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the cards from a deleted binder/deck back into to the collection.
     * @param cards the cards from the deleted binder/deck
     */
    public void returnCards(Card[] cards) {
        for (Card c : cards) {
            if (c == null) {
                return;
            }
            this.getCard(this.getCards().indexOf(c)).incrementCollectionCount();
        }
    }

    /**
     * Sorts cards in alphabetical order of name.
     */
    public void sort() {
        this.getCards().sort(new NameSorter());
    }

    /**
     * Sells a copy of a card in the collection.
     * @param index collection index of the card to be sold
     * @return value of the card. 0.0 if invalid
     */
    public double sellCard(int index) {
        if (this.getCard(index).getCollectionCount() > 0) {
            this.getCard(index).decrementCollectionCount();
            return this.getCard(index).getFinalValue();
        }
        return 0.0;
    }

    /**
     * Getter for a card given an index.
     * @param index index of the card to get
     * @return card at specified index
     */
    public Card getCard(int index) {
        if (index >= 0 && index < this.getCards().size()) {
            return this.cards.get(index);
        }
        return null;
    }

    /**
     * Getter for cards.
     * @return cards
     */
    public ArrayList<Card> getCards() {
        return this.cards;
    }
}
