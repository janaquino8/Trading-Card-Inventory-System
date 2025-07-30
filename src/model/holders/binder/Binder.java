package src.model.holders.binder;

import src.model.holders.Holder;
import src.model.holders.NameSorter;

import java.util.*;

/**
 * Binder representation in the program.
 *
 * <p>A binder is a type of holder that can store up to 20 cards at a time, including duplicates.
 */
public abstract class Binder extends Holder {
    /**
     * Constructor to construct a Binder object given its name.
     * @param name name of the created binder
     */
    public Binder(String name) {
        super(name, 20);
        MAX_COUNT = 20;
    }

    /**
     * Sorts cards in alphabetical order of name.
     */
    public void sort() {
        if (this.getCard(2) != null) {
            Arrays.sort(this.getCards(), new NameSorter());
        }
    }
}
