package src.model;

import java.util.*;

public class Collection {
    private ArrayList<Card> cards;

    public Collection() {
        cards = new ArrayList<Card>();
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }
}
