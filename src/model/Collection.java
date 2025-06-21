package src.model;

import java.util.*;

public class Collection {
    private ArrayList<Card> cards;

    public Collection() {
        cards = new ArrayList<Card>();
    }

    public void addCard(String name, int rarity, double baseValue) {
        this.getCards().add(new Card(name, rarity, baseValue, this.getCards().size()));
    }

    public void addCard(String name, int rarity, int variant, double baseValue) {
        this.getCards().add(new Card(name, rarity, variant, baseValue, this.getCards().size()));
    }

    public Card getCard(int index) {
        if (index >= 0 && index < this.getCards().size()) {
            return this.cards.get(index);
        }
        return null;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }
}
