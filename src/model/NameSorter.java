package src.model;

import java.util.*;

public class NameSorter implements Comparator<Card>{
    public int compare (Card c1, Card c2) {
        return c1.getName().compareTo(c2.getName());
    }
}
