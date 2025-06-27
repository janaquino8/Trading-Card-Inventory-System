package src.model;

import java.util.*;

/**
 * Collection
 * Represents the collection of the user
 */
public class Collection {
    private ArrayList<Card> cards;

    /**
     * Collector
     * Constructor to construct a Collection object
     */
    public Collection() {
        cards = new ArrayList<Card>();
    }

    /**
     * findCard
     * @param name name of the card to be found
     * @return index of the card in cards, -1 if not fount
     * Finds a card in cards given a name
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
     * findCard
     * @param cardNo card number of the card to be found
     * @return index of the card in cards, -1 if not found
     * Finds a card in cards given a card number
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
     * addCard
     * @param name name of the created card
     * @param rarity integer representing the rarity of the created card
     * @param baseValue base value of the created card
     * Creates a common/uncommon card and adds it to the collection
     */
    public void addCard(String name, int rarity, double baseValue) {
        this.getCards().add(new Card(name, rarity, baseValue, this.getCards().size()));
    }

    /**
     * addCard
     * @param name name of the created card
     * @param rarity integer representing the rarity of the created card
     * @param variant integer representing the variant of the created card
     * @param baseValue base value of the created card
     * Creates a rare/legendary card and adds it to the collection
     */
    public void addCard(String name, int rarity, int variant, double baseValue) {
        this.getCards().add(new Card(name, rarity, variant, baseValue, this.getCards().size()));
    }

    /**
     * returnCards
     * @param cards the cards from the deleted binder/deck
     * Returns the cards from a deleted binder/deck back into to the collection
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
     * countTotalCards
     * @return the total number of cards (accounting for copies) in the collection
     * Gets the number of cards (and copies) currently in the collection
     */
    public int countTotalCards() {
        int ctr = 0;

        for (Card c : this.getCards()) {
            ctr += c.getCollectionCount();
        }

        return ctr;
    }

    /**
     * sort
     * Sorts cards in alphabetical order of name
     */
    public void sort() {
        this.getCards().sort(new NameSorter());
    }

    /**
     * getCard
     * @param index index of the card to get
     * @return card at specified index
     * Getter for a card given an index
     */
    public Card getCard(int index) {
        if (index >= 0 && index < this.getCards().size()) {
            return this.cards.get(index);
        }
        return null;
    }

    /**
     * getCards
     * @return cards
     * Getter for cards
     */
    public ArrayList<Card> getCards() {
        return this.cards;
    }
}
