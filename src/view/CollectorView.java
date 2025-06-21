package src.view;

import java.util.*;

public class CollectorView {
    public CollectorView() {

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
        System.out.println("Welcome to the Trading Card Inventory System! The best place for all your trading card needs!\n");
        System.out.println("OPTIONS");

        if (collectionSize == 0) {
            System.out.println("[1] Add Card");
        }
        else {
            System.out.println("[1] Manage Collection");
        }

        if (binderCount == 0) {
            System.out.println("[2] Create a New Binder");
        }
        else {
            System.out.println("[2] Manage Binders");
        }

        if (deckCount == 0) {
            System.out.println("[3] Create a New Deck");
        }
        else {
            System.out.println("[3] Manage Decks");
        }

        System.out.println("[0] Exit");
    }

    public void exit() {
        System.out.println("Thanks for using the Trading Card Inventory System!");
    }
}
