package src.model;

import java.util.*;

public class Collector {
    private Collection collection;
    private ArrayList<Binder> binders;
    private ArrayList<Deck> decks;

    public Collector() {
        collection = new Collection();
        binders = new ArrayList<Binder>();
        decks = new ArrayList<Deck>();
    }

    public void createBinder(String name) {
        this.getBinders().add(new Binder(name));
    }

    public void deleteBinder(int index) {
        this.getCollection().returnCards(this.getBinder(index).getCards());
        this.getBinders().remove(index);
    }

    public int findBinder(String name) {
        for (int i = 0; i < this.getBinders().size(); i++) {
            if (this.getBinder(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void createDeck(String name) {
        this.getDecks().add(new Deck(name));
    }

    public void deleteDeck(int index) {
        this.getCollection().returnCards(this.getDeck(index).getCards());
        this.getDecks().remove(index);
    }

    public int findDeck(String name) {
        for (int i = 0; i < this.getDecks().size(); i++) {
            if (this.getDeck(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Collection getCollection() {
        return this.collection;
    }

    public Binder getBinder(int index) {
        if (index >= 0 && index < this.binders.size()) {
            return this.binders.get(index);
        }
        return null;
    }

    public ArrayList<Binder> getBinders() {
        return this.binders;
    }

    public Deck getDeck(int index) {
        if (index >= 0 && index < this.decks.size()) {
            return this.decks.get(index);
        }
        return null;
    }

    public ArrayList<Deck> getDecks() {
        return this.decks;
    }
}
