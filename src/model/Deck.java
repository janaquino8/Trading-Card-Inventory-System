package src.model;

import java.util.*;

public class Deck {
    private String name;
    private Card[] cards;
    public static final int MAX_COUNT = 10;

    public Deck() {
        cards = new Card[MAX_COUNT];
    }
}
