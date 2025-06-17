package src.model;

import java.util.*;

public class Binder {
    private String name;
    private Card[] cards;
    public static final int MAX_BINDER_CARD_COUNT = 10;

    public Binder() {
        cards = new Card[20];
    }
}
