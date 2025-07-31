package src.model.holders;

import src.model.card.Card;

/**
 * Representation of a holder in the collection.
 *
 * <p>A holder possesses a name and stores a finite amount of cards.
 */
public abstract class Holder {
    /**
     * Name of the holder.
     */
    protected String name;

    /**
     * Collection of cards.
     */
    protected Card[] cards;

    /**
     * Represents the type of holder.
     */
    protected int ID;

    /**
     * Maximum number of cards that can be stored in the holder.
     */
    public static int MAX_COUNT;

    /**
     * Creates a new Holder with specified name and card capacity.
     * @param name The holder's display name
     * @param count Maximum number of cards this holder can contain
     */
    public Holder(String name, int count) {
        cards = new Card[count];
        this.setName(name);
    }

    /**
     * Adds a card to the holder.
     * @param card card to be added to the holder
     * @return true if the card is successfully added, false otherwise
     */
    public boolean addCard(Card card) {
        int index = 0;

        while (this.getCard(index) != null) {
            index++;
        }

        if (index < MAX_COUNT) {
            this.setCard(index, card);
            return true;
        }
        return false;
    }

    /**
     * Removes a card form the holder.
     * @param index index of the card to be removed from the holder
     */
    public void removeCard(int index) {
        if (this.isEmpty()) {
            return;
        }

        int i = index + 1;
        while (i < MAX_COUNT && this.getCard(i) != null) {
            this.setCard(i - 1, this.getCard(i));
            i++;
        }
        this.setCard(i - 1, null);
    }

    /**
     * Checks if the holder is empty (i.e., 0 cards).
     * @return true if holder is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.cards[0] == null;
    }

    /**
     * Checks if the holder is full (i.e., has reached the maximum number of cards allowed).
     * @return true if holder is full, false otherwise
     */
    public boolean isFull() {
        return this.cards[MAX_COUNT - 1] != null;
    }

    /**
     * Finds a card in cards given its name.
     * @param name name of the card to be found
     * @return index of the card in cards, -1 if not found
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
     * Getter for name.
     * @return name of the binder
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for name.
     * @param name new name of the binder
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for a card given an index.
     * @param index index of the card to get
     * @return card at specified index
     */
    public Card getCard(int index) {
        if (index >= 0 && index < MAX_COUNT) {
            return this.cards[index];
        }
        return null;
    }

    /**
     * Setter for card given an index.
     * @param index index of the card to set
     * @param card new card
     */
    public void setCard(int index, Card card) {
        if (index >= 0 && index < MAX_COUNT) {
            this.cards[index] = card;
        }
    }

    /**
     * Getter for cards.
     * @return cards
     */
    public Card[] getCards() {
        return this.cards;
    }

    /**
     * Sets the current cards in this holder with the given array.
     * @param cards Array of Card objects to store (can be null or empty)
     */
    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    /**
     * Returns this holder's unique identifier number.
     * @return The holder's ID value
     */
    public int getID() {
        return this.ID;
    }
}
