package src.view;

import java.util.*;

public class CollectorView {
    public CollectorView() {

    }

    public void start() {
        System.out.println("Welcome to the Trading Card Inventory System! The best place for all your trading card needs!");
    }

    public void displayMainOptions (int collectionSize, int binderCount, int deckCount) {
        System.out.println("""
                    ____________________ .___  _________   \s
                 /\\ \\__    ___/\\_   ___ \\|   |/   _____/ /\\\s
                 \\/   |    |   /    \\  \\/|   |\\_____  \\  \\/\s
                 /\\   |    |   \\     \\___|   |/        \\ /\\\s
                 \\/   |____|    \\______  /___/_______  / \\/\s
                                       \\/            \\/\s
                """);
        System.out.println("OPTIONS");
        System.out.println("[1] Add Card");
        System.out.println("[2] Create a New Binder");
        System.out.println("[3] Create a New Deck");
        // card options
        // binder options
        // deck options
        System.out.println("~~~");
        System.out.println("[0] Exit");
    }

    public void exit() {
        System.out.println("Thanks for using the Trading Card Inventory System!");
    }
}
