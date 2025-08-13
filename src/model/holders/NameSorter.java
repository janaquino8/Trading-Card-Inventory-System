package src.model.holders;

import src.model.card.Card;

import java.util.*;

/**
 * Sorts objects in alphabetical order of name.
 */
public class NameSorter implements Comparator<Card> {
    /**
     * Compares the names of two Cards based on ASCII value.
     * @param c1 first card
     * @param c2 second card
     * @return a negative int if c1 name has a lower ASCII value, a positive int if c2 has a lower ASCII value,
     * and 0 if both are equal
     */
    public int compare (Card c1, Card c2) {
        if (c1 != null && c2 != null) {
            return c1.getName().compareTo(c2.getName());
        }
        return 0;
    }
}
