package src.model;

/**
 * Deck
 * Represents a deck
 */
public class Deck {
    private String name;
    private Card[] cards;
    public static final int MAX_COUNT = 10;

    /**
     * Deck
     * Constructor to construct a Deck object
     */
    public Deck() {
        cards = new Card[MAX_COUNT];
    }

    /**
     * Deck
     * @param name name of the created deck
     * Constructor to construct a Deck object given its name
     */
    public Deck(String name) {
        this();
        this.setName(name);
    }

    /**
     * addCard
     * @param card card to be added to the deck
     * Adds a card to the deck
     */
    public void addCard(Card card) {
        int index = 0;

        while (this.getCard(index) != null) {
            index++;
        }

        if (index < MAX_COUNT) {
            this.setCard(index, card);
        }
    }

    /**
     * removeCard
     * @param index index of the card to be removed from the deck
     * Removes a card form the deck
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
     * isEmpty
     * @return true if deck is empty, false otherwise
     * Checks if the deck is empty (i.e., 0 cards)
     */
    public boolean isEmpty() {
        return this.cards[0] == null;
    }

    /**
     * isFull
     * @return true if deck is full, false otherwise
     * Checks if the deck is full (i.e., 20 cards)
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
