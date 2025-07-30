package src.view;

import java.util.*;

/*
CollectorView - Main view class handling user interactions, input collection, and high-level menu displays
 */
public class CollectorView {
    private final String RESET = "\u001B[0m";
    private final String BOLD = "\u001B[1m";
    private final String UNDERLINE = "\u001B[4m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String BLUE = "\u001B[34m";
    Scanner sc = new Scanner(System.in);

    /*
    Method         - getIntInput
    String prompt  - Message to display for input request
    int min        - Minimum acceptable value
    int max        - Maximum acceptable value
    return type    - int
    Method Comment : Collects and validates integer input within specified range
     */
    public int getIntInput(String prompt, int min, int max) {
        int input;
        do {
            System.out.print(GREEN + prompt + RESET);
            try {
                input = Integer.parseInt(sc.nextLine());
            }
            catch(NumberFormatException e) {
                input = min - 1;
            }
            if (input < min || input > max) {
                System.out.println(RED + "Invalid input. Try again." + RESET);
            }
        } while (input < min || input > max);

        return input;
    }

    /*
    Method         - getStringInput
    String prompt  - Message to display for input request
    return type    - String
    Method Comments : Collects and validates non-empty string input
     */
    public String getStringInput(String prompt) {
        String input;
        do {
            System.out.print(GREEN + prompt + RESET);
            input = sc.nextLine();
        } while (input.isEmpty());

        return input;
    }

    /*
    Method         - getDoubleInput
    String prompt  - Message to display for input request
    return type    - double
    Method Comment : Collects and validates positive double input
     */
    public double getDoubleInput(String prompt) {
        double input;
        do {
            System.out.print(GREEN + prompt + RESET);
            try {
                input = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                input = -1;
            }
            if (input < 0) {
                System.out.println(RED + "Invalid input. Try again." + RESET);
            }
        } while (input < 0);

        return input;
    }

    /*
    Method       - printConfirmationMsg
    int option   - Numeric code representing different confirmation/error messages
    return type  - void
    Method Comment : Displays colored status messages for various operations (success/error cases)
     */
    public void printConfirmationMsg(int option) {
        System.out.println();
        switch (option) {
            case 0:
                System.out.println(GREEN + "Action successfully cancelled." + RESET);
                break;
            case 1:
                System.out.println(GREEN + "Card successfully added to collection." + RESET);
                break;
            case 2:
                System.out.println(GREEN + "Binder successfully created." + RESET);
                break;
            case 3:
                System.out.println(GREEN + "Deck successfully created." + RESET);
                break;
            case 4:
                System.out.println(RED + "Card already exists." + RESET);
                break;
            case 5:
                System.out.println(GREEN + "Card copy successfully added." + RESET);
                break;
            case 6:
                System.out.println(RED + "Card does not yet exist in the collection." + RESET);
                break;
            case 7:
                System.out.println(RED + "Binder already exists. Input another name." + RESET);
                break;
            case 8:
                System.out.println(RED + "Deck already exists. Input another name." + RESET);
                break;
            case 9:
                System.out.println(RED + "Binder doesn't exist. Input another name." + RESET);
                break;
            case 10:
                System.out.println(RED + "Deck doesn't exist. Input another name." + RESET);
                break;
            case 11:
                System.out.println(GREEN + "Binder successfully deleted." + RESET);
                break;
            case 12:
                System.out.println(GREEN + "Deck successfully deleted." + RESET);
                break;
            case 13:
                System.out.println(RED + "There are no existing binders." + RESET);
                break;
            case 14:
                System.out.println(RED + "There are no existing decks." + RESET);
                break;

        }
        System.out.println();
    }

    /*
    Method              - displayMainOptions
    int collectionSize  - Number of cards in collection
    int binderCount     - Number of existing binders
    int deckCount       - Number of existing decks
    return type         - void
    Method Comment : Shows main menu with dynamic options based on the states of Collection, Binder, and Deck
     */
    public void displayMainOptions (int collectionSize, int binderCount, int deckCount) {
        System.out.println( BOLD + BLUE +
                " _____    ____   ___   ____  \n" +
                "|_   _|  / ___| |_ _| / ___| \n" +
                "  | |   | |      | |  \\___ \\ \n" +
                "  | |   | |___   | |   ___) |\n" +
                "  |_|    \\____| |___| |____/\n" + RESET);

        System.out.println(GREEN + "Welcome to the Trading Card  Inventory System! The best place for all your trading card needs!\n" + RESET);
        System.out.println(BOLD + UNDERLINE + RED + "O P T I O N S\n" + RESET);

        if (collectionSize == 0) {
            System.out.println(GREEN + "[1] Add a Card" + RESET);
        }
        else {
            System.out.println(GREEN + "[1] Manage Collection" + RESET);
        }

        if (binderCount == 0) {
            System.out.println(GREEN + "[2] Create a New Binder" + RESET);
        }
        else {
            System.out.println(GREEN + "[2] Manage Binders" + RESET);
        }

        if (deckCount == 0) {
            System.out.println(GREEN + "[3] Create a New Deck" + RESET);
        }
        else {
            System.out.println(GREEN + "[3] Manage Decks" + RESET);
        }

        System.out.println(RED + "[0] Exit\n" + RESET);
    }

    /*
    Method       - displayCollectionOptions
    return type  - void
    Method Comment : Displays collection management submenu with ASCII header
     */
    public void displayCollectionOptions() {
        System.out.println(BOLD + BLUE +
                "  ____    ___    _       _       _____    ____   _____   ___    ___    _   _ \n" +
                " / ___|  / _ \\  | |     | |     | ____|  / ___| |_   _| |_ _|  / _ \\  | \\ | |\n" +
                "| |     | | | | | |     | |     |  _|   | |       | |    | |  | | | | |  \\| |\n" +
                "| |___  | |_| | | |___  | |___  | |___  | |___    | |    | |  | |_| | | |\\  |\n" +
                " \\____|  \\___/  |_____| |_____| |_____|  \\____|   |_|   |___|  \\___/  |_| \\_|\n" + RESET);

        System.out.println(BOLD + UNDERLINE + RED + "O P T I O N S\n" + RESET);
        System.out.println(GREEN + "[1] Add a Card" + RESET);
        System.out.println(GREEN + "[2] Increase/Decrease Card Count" + RESET);
        System.out.println(GREEN + "[3] Display a Card" + RESET);
        System.out.println(GREEN + "[4] Display Collection" + RESET);
        System.out.println(RED + "[0] Back\n" + RESET);
    }

    /*
    Method       - displayBinderOptions
    return type  - void
    Method Comment : Displays binder management submenu with ASCII header
     */
    public void displayBinderOptions() {
        System.out.println(BOLD + BLUE +
                " ____    ___   _   _   ____    _____   ____  \n" +
                "| __ )  |_ _| | \\ | | |  _ \\  | ____| |  _ \\ \n" +
                "|  _ \\   | |  |  \\| | | | | | |  _|   | |_) |\n" +
                "| |_) |  | |  | |\\  | | |_| | | |___  |  _ < \n" +
                "|____/  |___| |_| \\_| |____/  |_____| |_| \\_\\\n" + RESET);

        System.out.println(BOLD + UNDERLINE + RED + "O P T I O N S\n" + RESET);
        System.out.println(GREEN + "[1] Create a New Binder" + RESET);
        System.out.println(GREEN + "[2] Delete a Binder" + RESET);
        System.out.println(GREEN + "[3] Add a Card" + RESET);
        System.out.println(GREEN + "[4] Remove a Card" + RESET);
        System.out.println(GREEN + "[5] View a Binder" + RESET);
        System.out.println(GREEN + "[6] Trade" + RESET);
        System.out.println(RED + "[0] Back\n" + RESET);
    }

    /*
    Method       - displayDeckOptions
    return type  - void
    Method Comment : Displays deck management submenu with ASCII header
     */
    public void displayDeckOptions() {
        System.out.println(BOLD + BLUE +
                " ____    _____    ____   _  __\n" +
                "|  _ \\  | ____|  / ___| | |/ /\n" +
                "| | | | |  _|   | |     | ' / \n" +
                "| |_| | | |___  | |___  | . \\ \n" +
                "|____/  |_____|  \\____| |_|\\_\\\n" + RESET);

        System.out.println(BOLD + UNDERLINE + RED + "O P T I O N S\n" + RESET);
        System.out.println(GREEN + "[1] Create a New Deck" + RESET);
        System.out.println(GREEN + "[2] Delete a Deck" + RESET);
        System.out.println(GREEN + "[3] Add a Card" + RESET);
        System.out.println(GREEN + "[4] Remove a Card" + RESET);
        System.out.println(GREEN + "[5] View a Deck" + RESET);
        System.out.println(RED + "[0] Back\n" + RESET);
    }

    /*
    Method       - displayAddCard
    return type  - void
    Method Comments : Shows "Add Card" operation header with ASCII art
     */
    public void displayAddCard() {
        System.out.println(BOLD + BLUE +
                "    _      ____    ____        ____      _      ____    ____  \n" +
                "   / \\    |  _ \\  |  _ \\      / ___|    / \\    |  _ \\  |  _ \\ \n" +
                "  / _ \\   | | | | | | | |    | |       / _ \\   | |_) | | | | |\n" +
                " / ___ \\  | |_| | | |_| |    | |___   / ___ \\  |  _ <  | |_| |\n" +
                "/_/   \\_\\ |____/  |____/      \\____| /_/   \\_\\ |_| \\_\\ |____/\n" + RESET);
    }

    /*
    Method       - displayCreateBinder
    return type  - void
    Method Comment : Shows "Create Binder" operation header with ASCII art
     */
    public void displayCreateBinder() {
        System.out.println(BOLD + BLUE +
                "  ____   ____    _____      _      _____   _____      ____    ___   _   _   ____    _____   ____  \n" +
                " / ___| |  _ \\  | ____|    / \\    |_   _| | ____|    | __ )  |_ _| | \\ | | |  _ \\  | ____| |  _ \\ \n" +
                "| |     | |_) | |  _|     / _ \\     | |   |  _|      |  _ \\   | |  |  \\| | | | | | |  _|   | |_) |\n" +
                "| |___  |  _ <  | |___   / ___ \\    | |   | |___     | |_) |  | |  | |\\  | | |_| | | |___  |  _ < \n" +
                " \\____| |_| \\_\\ |_____| /_/   \\_\\   |_|   |_____|    |____/  |___| |_| \\_| |____/  |_____| |_| \\_\\\n" + RESET);
    }

    /*
    Method       - displayCreateDeck
    return type  - void
    Method Comment : Shows "Create Deck" operation header with ASCII art
     */
    public void displayCreateDeck() {
        System.out.println(BOLD + BLUE +
                "  ____   ____    _____      _      _____   _____      ____    _____    ____   _  __\n" +
                " / ___| |  _ \\  | ____|    / \\    |_   _| | ____|    |  _ \\  | ____|  / ___| | |/ /\n" +
                "| |     | |_) | |  _|     / _ \\     | |   |  _|      | | | | |  _|   | |     | ' / \n" +
                "| |___  |  _ <  | |___   / ___ \\    | |   | |___     | |_| | | |___  | |___  | . \\ \n" +
                " \\____| |_| \\_\\ |_____| /_/   \\_\\   |_|   |_____|    |____/  |_____|  \\____| |_|\\_\\\n" + RESET);
    }

    /*
    Method       - displayDeleteBinder
    return type  - void
    Method Comment : Shows "Delete Binder" operation header with ASCII art
     */
    public void displayDeleteBinder() {
        System.out.println(BOLD + BLUE +
                " ____    _____   _       _____   _____   _____      ____    ___   _   _   ____    _____   ____  \n" +
                "|  _ \\  | ____| | |     | ____| |_   _| | ____|    | __ )  |_ _| | \\ | | |  _ \\  | ____| |  _ \\ \n" +
                "| | | | |  _|   | |     |  _|     | |   |  _|      |  _ \\   | |  |  \\| | | | | | |  _|   | |_) |\n" +
                "| |_| | | |___  | |___  | |___    | |   | |___     | |_) |  | |  | |\\  | | |_| | | |___  |  _ < \n" +
                "|____/  |_____| |_____| |_____|   |_|   |_____|    |____/  |___| |_| \\_| |____/  |_____| |_| \\_\\" + RESET);
    }

    /*
    Method       - displayDeleteDeck
    return type  - void
    Method Comment : Shows "Delete Deck" operation header with ASCII art
     */
    public void displayDeleteDeck() {
        System.out.println(BOLD + BLUE +
                " ____    _____   _       _____   _____   _____      ____    _____    ____   _  __\n" +
                "|  _ \\  | ____| | |     | ____| |_   _| | ____|    |  _ \\  | ____|  / ___| | |/ /\n" +
                "| | | | |  _|   | |     |  _|     | |   |  _|      | | | | |  _|   | |     | ' / \n" +
                "| |_| | | |___  | |___  | |___    | |   | |___     | |_| | | |___  | |___  | . \\ \n" +
                "|____/  |_____| |_____| |_____|   |_|   |_____|    |____/  |_____|  \\____| |_|\\_\\" + RESET);
    }

    /*
    Method       - printCardCreatedVerification
    String name  - Name of created card
    int cardNo   - Assigned card number
    return type  -  void
    Method Comment : Displays confirmation of successful card creation
     */
    public void printCardCreatedVerification(String name, int cardNo) {
        System.out.println(GREEN + "\nCreated: [" + cardNo + "] " + name + RESET);
    }

    /*
    Method       - exit
    return type  - void
    Method Comment : Shows shutdown message when exiting application
     */
    public void exit() {
        System.out.println(GREEN + "\nThanks for using the Trading Card Inventory System!" + RESET);
        System.out.println(GREEN + "Shutting down..." + RESET);
    }

    // Options for the user for the different kinds of binders
    public void displayCreateBinderOptions() {
        System.out.println(BOLD + UNDERLINE + RED + "O P T I O N S\n" + RESET);
        System.out.println(GREEN + "[1] - Non Curated Binder" + RESET);
        System.out.println(GREEN + "[2] - Pauper Binder" + RESET);
        System.out.println(GREEN + "[3] - Rares Binder" + RESET);
        System.out.println(GREEN + "[4] - Luxury Binder" + RESET);
        System.out.println(GREEN + "[5] - Collector Binder\n" + RESET);
    }

    // Options for the user for the different kinds of binders
    public void displayCreateDeckOptions() {
        System.out.println(BOLD + UNDERLINE + RED + "O P T I O N S\n" + RESET);
        System.out.println(GREEN + "[1] - Normal Deck" + RESET);
        System.out.println(GREEN + "[2] - Sellable Deck\n" + RESET);
    }
}