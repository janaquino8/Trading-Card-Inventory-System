package src.model;

import java.util.*;

/**
 * Binder
 * Represents a binder
 */
public class Binder {
    private String name;
    private Card[] cards;
    public static final int MAX_COUNT = 20;

    /**
     * Binder
     * Constructor to construct a Binder object
     */
    public Binder() {
        cards = new Card[MAX_COUNT];
    }

    /**
     * Binder
     * @param name name of the created binder
     * Constructor to construct a Binder object given its name
     */
    public Binder(String name) {
        this();
        this.setName(name);
    }

    /**
     * addCard
     * @param card card to be added to the binder
     * Adds a card to the binder
     */
    public void addCard(Card card) {
        int index = 0;

        while (this.getCard(index) != null) {
            index++;
        }

        this.setCard(index, card);
    }

    /**
     * removeCard
     * @param index index of the card to be removed from the binder
     * Removes a card form the binder
     */
    public void removeCard(int index) {
        int i = index + 1;
        while (i < MAX_COUNT && this.getCard(i) != null) {
            this.setCard(i - 1, this.getCard(i));
            i++;
        }
        this.setCard(i - 1, null);
    }

    /**
     * isEmpty
     * @return true if binder is empty, false otherwise
     * Checks if the binder is empty (i.e., 0 cards)
     */
    public boolean isEmpty() {
        return this.cards[0] == null;
    }

    /**
     * isFull
     * @return true if binder is full, false otherwise
     * Checks if the binder is full (i.e., 20 cards)
     */
    public boolean isFull() {
        return this.cards[MAX_COUNT - 1] != null;
    }

    /**
     * findCard
     * @param name name of the card to be found
     * @return index of the card in cards, -1 if not found
     * Finds a card in cards given its name
     */
    public int findCard(String name) {
        for (int i = 0; i < MAX_COUNT && this.getCard(i) != null; i++) {
            if (this.getCard(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
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
        this.setCard(outgoingCardIndex, incomingCard);
    }

    /**
     * getName
     * @return name of the binder
     * Getter for name
     */
    public String getName() {
        return this.name;
    }

    /**
     * setName
     * @param name new name of the binder
     * Setter for name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getCard
     * @param index index of the card to get
     * @return card at specified index
     * Getter for a card given an index
     */
    public Card getCard(int index) {
        if (index >= 0 && index < MAX_COUNT) {
            return this.cards[index];
        }
        return null;
    }

    /**
     * setCard
     * @param index index of the card to set
     * @param card new card
     * Setter for card given an index
     */
    public void setCard(int index, Card card) {
        if (index >= 0 && index < MAX_COUNT) {
            this.cards[index] = card;
        }
    }

    /**
     * getCards
     * @return cards
     * Getter for cards
     */
    public Card[] getCards() {
        return this.cards;
    }
}
