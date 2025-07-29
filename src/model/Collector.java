package src.model;

import src.model.card.Card;
import src.model.holders.Collection;
import src.model.holders.Holder;
import src.model.holders.binder.*;
import src.model.holders.deck.*;
import java.util.*;

/**
 * Collector
 * Represents the user as the collector using the program
 */
public class Collector {
    private Collection collection;
    private ArrayList<Binder> binders;
    private ArrayList<Deck> decks;
    private double money;

    /**
     * Collector
     * Constructor to construct a Collector object
     */
    public Collector() {
        this.collection = new Collection();
        this.binders = new ArrayList<>();
        this.decks = new ArrayList<>();
        this.money = 0;
    }

    private void deleteHolder(ArrayList<? extends Holder> holders, int index) {
        if (index >= 0 && index < holders.size()) {
            if (!holders.get(index).isEmpty()) {
                this.getCollection().returnCards(holders.get(index).getCards());
            }
            holders.remove(index);
        }
    }

    private int findHolder(ArrayList<? extends Holder> holders, String name) {
        for (int i = 0; i < holders.size(); i++) {
            if (holders.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isHoldersEmpty(ArrayList<? extends Holder> holders) {
        for (Holder h : holders) {
            if (!h.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean isHolderFull(ArrayList<? extends Holder> holders) {
        if (holders.isEmpty()) {
            return false;
        }
        for (Holder h : holders) {
            if (!h.isFull()) {
                return false;
            }
        }
        return true;
    }

    /**
     * createBinder
     * @param name name of the created binder
     * Creates a binder
     */
    public void createBinder(String name, int type) {
        switch (type) {
            case 1 -> this.getBinders().add(new TradableBinder(name));
            case 2 -> this.getBinders().add(new PauperBinder(name));
            case 3 -> this.getBinders().add(new RaresBinder(name));
            case 4 -> this.getBinders().add(new LuxuryBinder(name));
            case 5 -> this.getBinders().add(new CollectorBinder(name));
        }
    }

    /**
     * deleteBinder
     * @param index integer of the binder in binders to be deleted
     * Deletes a binder
     */
    public void deleteBinder(int index) {
        deleteHolder(this.getBinders(), index);
    }

    /**
     * findBinder
     * @param name name of the binder to be found
     * @return index of the binder in binders, -1 if not found
     * Finds a binder in binders given a name
     */
    public int findBinder(String name) {
        return findHolder(this.getBinders(), name);
    }

    /**
     * createDeck
     * @param name name of the created deck
     * Creates a deck
     */
    public void createDeck(String name, int type) {
        switch (type) {
            case 1 -> this.getDecks().add(new Deck(name));
            case 2 -> this.getDecks().add(new SellableDeck(name));
        }
    }

    /**
     * deleteDeck
     * @param index integer of the deck in decks to be deleted
     * Deletes a deck
     */
    public void deleteDeck(int index) {
        deleteHolder(this.getDecks(), index);
    }

    /**
     * findDeck
     * @param name name of the deck to be found
     * @return index of the binder in decks, -1 if not found
     * Finds a deck in decks given a name
     */
    public int findDeck(String name) {
        return findHolder(this.getDecks(), name);
    }

    /**
     * isBindersEmpty
     * @return true if all binders are empty, false otherwise
     * Checks if all binders are empty
     */
    public boolean isBindersEmpty() {
        return this.isHoldersEmpty(this.getBinders());
    }

    /**
     * isBindersFull
     * @return true if all binders are full, false otherwise
     * Checks if all binders are full
     */
    public boolean isBindersFull() {
        return isHolderFull(this.getBinders());
    }

    /**
     * isDecksEmpty
     * @return true if all decks are empty, false otherwise
     * Checks if all decks are empty
     */
    public boolean isDecksEmpty() {
        return this.isHoldersEmpty(this.getDecks());
    }

    /**
     * isDecksFull
     * @return true if all decks are full, false otherwise
     * Checks if all decks are full
     */
    public boolean isDecksFull() {
        return isHolderFull(this.getDecks());
    }

    public ArrayList<TradableBinder> getTradableBinders() {
        ArrayList<TradableBinder> newList = new ArrayList<TradableBinder>();
        for (Binder b : this.getBinders()) {
            if (b instanceof TradableBinder tb) {
                newList.add(tb);
            }
        }
        return newList;
    }

    public ArrayList<SellableBinder> getSellableBinders() {
        ArrayList<SellableBinder> newList = new ArrayList<SellableBinder>();
        for (Binder b : this.getBinders()) {
            if (b instanceof SellableBinder sb) {
                newList.add(sb);
            }
        }
        return newList;
    }

    public ArrayList<SellableDeck> getSellableDecks() {
        ArrayList<SellableDeck> newList = new ArrayList<SellableDeck>();
        for (Deck d : this.getDecks()) {
            if (d instanceof SellableDeck sd) {
                newList.add(sd);
            }
        }
        return newList;
    }

    /**
     * getCollection
     * @return collection
     * Getter for collection
     */
    public Collection getCollection() {
        return this.collection;
    }

    /**
     * getBinder
     * @param index index of binder to get
     * @return binder at specified index
     * Getter for a binder given an index
     */
    public Binder getBinder(int index) {
        if (index >= 0 && index < this.binders.size()) {
            return this.binders.get(index);
        }
        return null;
    }

    /**
     * getBinders
     * @return binders
     * Getter for binders
     */
    public ArrayList<Binder> getBinders() {
        return this.binders;
    }

    /**
     * getDeck
     * @param index index of deck to get
     * @return deck at specified index
     * Getter for a deck given an index
     */
    public Deck getDeck(int index) {
        if (index >= 0 && index < this.decks.size()) {
            return this.decks.get(index);
        }
        return null;
    }

    /**
     * getDecks
     * @return decks
     * Getter for decks
     */
    public ArrayList<Deck> getDecks() {
        return this.decks;
    }

    public int getCollectionCardCount() {
        return this.collection.getCards().size();
    }

    public int getCollectionTotalCount() {
        int ctr = 0;

        for (Card c : this.getCollection().getCards()) {
            ctr += c.getCollectionCount();
        }

        return ctr;
    }

    public int getBindersCount() {
        return this.binders.size();
    }

    public int getDecksCount() {
        return this.decks.size();
    }

    public void earnMoney(double sale) {
        this.money += sale;
    }

    public double getMoney() {
        return this.money;
    }
}
