package src.model;

import src.model.card.Card;
import src.model.holders.Collection;
import src.model.holders.Holder;
import src.model.holders.binder.*;
import src.model.holders.deck.*;
import java.util.*;

/**
 * Represents the user as the collector using the program.
 */
public class Collector {
    private Collection collection;
    private ArrayList<Binder> binders;
    private ArrayList<Deck> decks;
    private double money;

    /**
     * Constructor to construct a Collector object.
     */
    public Collector() {
        this.collection = new Collection();
        this.binders = new ArrayList<>();
        this.decks = new ArrayList<>();
        this.money = 0;
    }

    /**
     * Deletes a holder at the specified index from the given list of holders.
     * If the holder is not empty, its cards are returned to the collection before deletion.
     * @param holders the list of holders from which to delete
     * @param index the index of the holder to delete
     */
    private void deleteHolder(ArrayList<? extends Holder> holders, int index) {
        if (index >= 0 && index < holders.size()) {
            if (!holders.get(index).isEmpty()) {
                this.getCollection().returnCards(holders.get(index).getCards());
            }
            holders.remove(index);
        }
    }

    /**
     * Finds the index of the first holder with the specified name in the given list.
     * @param holders the list of holders to search through
     * @param name the name of the holder to find (case-sensitive)
     * @return the index of the holder with the matching name, or -1 if not found
     */
    private int findHolder(ArrayList<? extends Holder> holders, String name) {
        for (int i = 0; i < holders.size(); i++) {
            if (holders.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if all holders in the list are empty.
     * @param holders the list of holders to check
     * @return true if all holders are empty, false if at least one holder contains cards
     */
    private boolean isHoldersEmpty(ArrayList<? extends Holder> holders) {
        for (Holder h : holders) {
            if (!h.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if all holders in the list are full.
     * Returns false immediately if the list is empty.
     * @param holders the list of holders to check
     * @return true if the list is not empty and all holders are full, false otherwise
     */
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
     * Creates a binder.
     * @param name name of the created binder
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
     * Deletes a binder.
     * @param index integer of the binder in binders to be deleted
     */
    public void deleteBinder(int index) {
        deleteHolder(this.getBinders(), index);
    }

    /**
     * Finds a binder in binders given a name.
     * @param name name of the binder to be found
     * @return index of the binder in binders, -1 if not found
     */
    public int findBinder(String name) {
        return findHolder(this.getBinders(), name);
    }

    /**
     * Creates a deck.
     * @param name name of the created deck
     */
    public void createDeck(String name, int type) {
        switch (type) {
            case 1 -> this.getDecks().add(new Deck(name));
            case 2 -> this.getDecks().add(new SellableDeck(name));
        }
    }

    /**
     * Deletes a deck.
     * @param index integer of the deck in decks to be deleted
     */
    public void deleteDeck(int index) {
        deleteHolder(this.getDecks(), index);
    }

    /**
     * Finds a deck in decks given a name.
     * @param name name of the deck to be found
     * @return index of the binder in decks, -1 if not found
     */
    public int findDeck(String name) {
        return findHolder(this.getDecks(), name);
    }

    /**
     * Checks if all binders are empty.
     * @return true if all binders are empty, false otherwise
     */
    public boolean isBindersEmpty() {
        return this.isHoldersEmpty(this.getBinders());
    }

    /**
     * Checks if all binders are full.
     * @return true if all binders are full, false otherwise
     */
    public boolean isBindersFull() {
        return isHolderFull(this.getBinders());
    }

    /**
     * Checks if all decks are empty.
     * @return true if all decks are empty, false otherwise
     */
    public boolean isDecksEmpty() {
        return this.isHoldersEmpty(this.getDecks());
    }

    /**
     * Checks if all decks are full.
     * @return true if all decks are full, false otherwise
     */
    public boolean isDecksFull() {
        return isHolderFull(this.getDecks());
    }

    /**
     * Retrieves all binders that are TradableBinder objects.
     * Creates and returns a new list containing only the TradableBinder objects.
     * @return a new ArrayList containing all TradableBinder objects from the binders collection.
     */
    public ArrayList<TradableBinder> getTradableBinders() {
        ArrayList<TradableBinder> newList = new ArrayList<TradableBinder>();
        for (Binder b : this.getBinders()) {
            if (b instanceof TradableBinder tb) {
                newList.add(tb);
            }
        }
        return newList;
    }

    /**
     * Retrieves all binders that are sellable. Creates and returns a new list
     * containing only sellable binders.
     * @return a new ArrayList containing all sellable binders from the binders collection.
     */
    public ArrayList<Binder> getSellableBinders() {
        ArrayList<Binder> newList = new ArrayList<Binder>(this.getBinders());
        newList.removeIf(b -> b.getID() <= 2);
        return newList;
    }

    /**
     * Retrieves all decks that are sellable. Creates and returns a new list
     * containing only sellable decks.
     * @return a new ArrayList containing all sellable decks.
     */
    public ArrayList<Deck> getSellableDecks() {
        ArrayList<Deck> newList = new ArrayList<Deck>(this.getDecks());
        newList.removeIf(d -> d.getID() == 1);
        return newList;
    }

    /**
     * Getter for collection.
     * @return collection
     */
    public Collection getCollection() {
        return this.collection;
    }

    /**
     * Getter for a binder given an index.
     * @param index index of binder to get
     * @return binder at specified index
     */
    public Binder getBinder(int index) {
        if (index >= 0 && index < this.binders.size()) {
            return this.binders.get(index);
        }
        return null;
    }

    /**
     * Getter for binders.
     * @return binders
     */
    public ArrayList<Binder> getBinders() {
        return this.binders;
    }

    /**
     * Getter for a deck given an index.
     * @param index index of deck to get
     * @return deck at specified index
     */
    public Deck getDeck(int index) {
        if (index >= 0 && index < this.decks.size()) {
            return this.decks.get(index);
        }
        return null;
    }

    /**
     * Getter for decks.
     * @return decks
     */
    public ArrayList<Deck> getDecks() {
        return this.decks;
    }

    /**
     * Gets the number of unique card types in the collection.
     * This counts each distinct card once, regardless of quantity.
     * @return the number of unique card types in the collection
     */
    public int getCollectionCardCount() {
        return this.collection.getCards().size();
    }

    /**
     * Gets the total quantity of all cards in the collection.
     * This sums the quantities of each card instance.
     * @return the total count of all cards in the collection
     */
    public int getCollectionTotalCount() {
        int ctr = 0;

        for (Card c : this.getCollection().getCards()) {
            ctr += c.getCollectionCount();
        }

        return ctr;
    }

    /**
     * Gets the number of unique binders.
     * @return the number of unique binders
     */
    public int getBindersCount() {
        return this.binders.size();
    }

    /**
     * Gets the number of unique decks.
     * @return the number of unique decks
     */
    public int getDecksCount() {
        return this.decks.size();
    }

    /**
     * Adds the specified amount to the current money balance.
     * @param sale the amount of money to add
     */
    public void earnMoney(double sale) {
        this.money += sale;
    }

    /**
     * Gets the current money balance.
     * @return the current amount of money available
     */
    public double getMoney() {
        return this.money;
    }
}
