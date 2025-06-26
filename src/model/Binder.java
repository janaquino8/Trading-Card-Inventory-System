package src.model;

import java.util.*;

public class Binder {
    private String name;
    private Card[] cards;
    public static final int MAX_COUNT = 20;

    public Binder() {
        cards = new Card[MAX_COUNT];
    }

    public Binder(String name) {
        this();
        this.setName(name);
    }

    public void addCard(Card card) {
        int index = 0;

        while (this.getCard(index) != null) {
            index++;
        }

        this.setCard(index, card);
    }

    public void removeCard(int index) {
        int i = index + 1;
        while (i < MAX_COUNT && this.getCard(i) != null) {
            this.setCard(i - 1, this.getCard(i));
            i++;
        }
        this.setCard(i - 1, null);
    }

    public boolean isEmpty() {
        return this.cards[0] == null;
    }

    public boolean isFull() {
        return this.cards[MAX_COUNT - 1] != null;
    }

    public int findCard(String name) {
        for (int i = 0; i < MAX_COUNT && this.getCard(i) != null; i++) {
            if (this.getCard(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void sort() {
        Arrays.sort(this.getCards(), new NameSorter());
    }

    public void trade(int outgoingCardIndex, Card incomingCard) {
        this.setCard(outgoingCardIndex, incomingCard);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card getCard(int index) {
        if (index >= 0 && index < MAX_COUNT) {
            return this.cards[index];
        }
        return null;
    }

    public void setCard(int index, Card card) {
        if (index >= 0 && index < MAX_COUNT) {
            this.cards[index] = card;
        }
    }

    public Card[] getCards() {
        return this.cards;
    }
}
