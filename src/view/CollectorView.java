package src.view;

import java.util.*;

public class CollectorView {
    Scanner sc = new Scanner(System.in);

    public int getIntInput(String prompt, int min, int max) {
        int input;

        do {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(sc.nextLine());
            }
            catch(NumberFormatException e) {
                input = min - 1;
            }
            if (input < min || input > max) {
                System.out.println("Invalid input. Try again");
            }
        } while (input < min || input > max);

        return input;
    }

    public String getStringInput(String prompt) {
        String input;

        do {
            System.out.print(prompt);
            input = sc.nextLine();
        } while (input.isEmpty());

        return input;
    }

    public double getDoubleInput(String prompt) {
        double input;

        do {
            System.out.print(prompt);
            try {
                input = Double.parseDouble(sc.nextLine());
            }
            catch(NumberFormatException e) {
                input = -1;
            }
            if (input < 0) {
                System.out.println("Invalid input. Try again.");
            }
        } while (input < 0);

        return input;
    }

    public void printConfirmationMsg(int option) {
        System.out.println();

        switch (option) {
            case 0:
                System.out.println("Action successfully cancelled.");
                break;
            case 1:
                System.out.println("Card successfully added to collection.");
                break;
            case 2:
                System.out.println("Binder successfully created.");
                break;
            case 3:
                System.out.println("Deck successfully created.");
                break;
            case 4:
                System.out.println("Card already exists.");
                break;
            case 5:
                System.out.println("Card copy successfully added.");
                break;
            case 6:
                System.out.println("Card does not yet exist in the collection.");
                break;
        }

        System.out.println();
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
        System.out.println("OPTIONS\n");

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

        System.out.println("[0] Exit\n");
    }

    public void displayCollectionOptions() {
        System.out.println("""
                            ____        __  _        \s
                  _______  / / /__ ____/ /_(_)__  ___\s
                 / __/ _ \\/ / / -_) __/ __/ / _ \\/ _ \\\s
                 \\__/\\___/_/_/\\__/\\__/\\__/_/\\___/_//_/\s
                """);
        System.out.println("OPTIONS\n");
        System.out.println("[1] Increase/Decrease Card Count");
        System.out.println("[2] Display a Card");
        System.out.println("[3] Display Collection");
        System.out.println("[0] Back\n");
    }

    public void displayBinderOptions() {
        System.out.println("""
                    __   _         __      \s
                   / /  (_)__  ___/ /__ ____\s
                  / _ \\/ / _ \\/ _  / -_) __/\s
                 /_.__/_/_//_/\\_,_/\\__/_/  \s
                """);
        System.out.println("OPTIONS\n");
        System.out.println("[1] Delete a Binder");
        System.out.println("[2] Add a Card");
        System.out.println("[3] Remove a Card");
        System.out.println("[4] View a Binder");
        System.out.println("[5] Trade");
        System.out.println("[0] Back\n");
    }

    public void displayDeckOptions() {
        System.out.println("""
                      __        __ \s
                  ___/ /__ ____/ /__\s
                 / _  / -_) __/  '_/\s
                 \\_,_/\\__/\\__/_/\\_\\\s
                """);
        System.out.println("OPTIONS\n");
        System.out.println("[1] Delete a Binder");
        System.out.println("[2] Add a Card");
        System.out.println("[3] Remove a Card");
        System.out.println("[4] View a Binder");
        System.out.println("[0] Back\n");
    }

    public void exit() {
        System.out.println("\nThanks for using the Trading Card Inventory System!");
        System.out.println("Shutting down...");
    }

    public void displayAddCard() {
        System.out.println("""
                       __   __      __        __   __ \s
                  /\\  |  \\ |  \\    /  `  /\\  |__) |  \\\s
                 /~~\\ |__/ |__/    \\__, /~~\\ |  \\ |__/\s
                """);
    }

    public void displayCreateBinder() {
        System.out.println("""
                  __   __   ___      ___  ___     __          __   ___  __ \s
                 /  ` |__) |__   /\\   |  |__     |__) | |\\ | |  \\ |__  |__)\s
                 \\__, |  \\ |___ /~~\\  |  |___    |__) | | \\| |__/ |___ |  \\\s
                """);
    }

    public void displayCreateDeck() {
        System.out.println("""
                  __   __   ___      ___  ___     __   ___  __      \s
                 /  ` |__) |__   /\\   |  |__     |  \\ |__  /  ` |__/\s
                 \\__, |  \\ |___ /~~\\  |  |___    |__/ |___ \\__, |  \\\s
                """);
    }
}
