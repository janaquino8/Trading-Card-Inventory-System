package src.model;

import java.util.*;

public class Collection {
    private ArrayList<Card> cards;

    public Collection() {
        cards = new ArrayList<Card>();
    }

    public int findCard(String name) {
        for (int i = 0; i < this.getCards().size(); i++) {
            if (this.getCard(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public int findCard(int cardNo) {
        for (int i = 0; i < this.getCards().size(); i++) {
            if (this.getCard(i).getCardNo() == cardNo) {
                return i;
            }
        }
        return -1;
    }

    public void addCard(String name, int rarity, double baseValue) {
        this.getCards().add(new Card(name, rarity, baseValue, this.getCards().size()));
    }

    public void addCard(String name, int rarity, int variant, double baseValue) {
        this.getCards().add(new Card(name, rarity, variant, baseValue, this.getCards().size()));
    }

    public void returnCards(Card[] cards) {
        for (Card c : cards) {
            int index = this.getCards().indexOf(c);
            this.getCard(this.getCards().indexOf(c)).incrementCollectionCount();
        }
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
