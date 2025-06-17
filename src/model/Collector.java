package src.model;

import java.util.*;

public class Collector {
    private Collection collection;
    private ArrayList<Binder> binder;
    private ArrayList<Deck> deck;

    public Collector() {
        collection = new Collection();
        binder = new ArrayList<Binder>();
        deck = new ArrayList<Deck>();
    }

    public Collection getCollection() {
        return this.collection;
    }

    public ArrayList<Binder> getBinder() {
        return this.binder;
    }

    public ArrayList<Deck> getDeck() {
        return this.deck;
    }
}
