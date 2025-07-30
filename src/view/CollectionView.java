package src.view;

/*
CollectionView - Handles all visual displays related to card collection operations
 */
public class CollectionView {
    private final String RESET = "\u001B[0m";
    private final String BOLD = "\u001B[1m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String BLUE = "\u001B[34m";

    /*
    Method       - printConfirmationMsg
    int option   - Numeric code representing different confirmation/error messages
    return type  - void
    Method Comment : Prints colored status messages for collection operations (success/error cases)
     */
    public void printConfirmationMsg(int option) {
        System.out.println();
        switch (option) {
            case 0:
                System.out.println(GREEN + "Action successfully cancelled." + RESET);
                break;
            case 1:
                System.out.println(GREEN + "Card count successfully incremented." + RESET);
                break;
            case 2:
                System.out.println(GREEN + "Card count successfully decremented." + RESET);
                break;
            case 3:
                System.out.println(RED + "Card not found." + RESET);
                break;
            case 4:
                System.out.println(RED + "Card has no copies in collection." + RESET);
                break;
            case 5:
                System.out.println(RED + "There are currently no cards in collection." + RESET);
                break;
            case 6:
                System.out.println(GREEN + "Card successfully sold." + RESET);
                break;
        }
        System.out.println();
    }

    /*
    Method       - displayUpdateCardCount
    return type  - void
    Method Comment : Displays large ASCII art header for "Update Card Count" with search options
     */
    public void displayUpdateCardCount() {
        System.out.println(BOLD + BLUE +
                " _   _   ____    ____       _      _____   _____       ____      _      ____    ____        ____    ___    _   _   _   _   _____ \n" +
                "| | | | |  _ \\  |  _ \\     / \\    |_   _| | ____|     / ___|    / \\    |  _ \\  |  _ \\      / ___|  / _ \\  | | | | | \\ | | |_   _|\n" +
                "| | | | | |_) | | | | |   / _ \\     | |   |  _|      | |       / _ \\   | |_) | | | | |    | |     | | | | | | | | |  \\| |   | |  \n" +
                "| |_| | |  __/  | |_| |  / ___ \\    | |   | |___     | |___   / ___ \\  |  _ <  | |_| |    | |___  | |_| | | |_| | | |\\  |   | |  \n" +
                " \\___/  |_|     |____/  /_/   \\_\\   |_|   |_____|     \\____| /_/   \\_\\ |_| \\_\\ |____/      \\____|  \\___/   \\___/  |_| \\_|   |_|  " + RESET);
        System.out.println(GREEN + "[1] Search card by name" + RESET);
        System.out.println(GREEN + "[2] Search card by card no." + RESET);
        System.out.println(GREEN + "[0] Back\n" + RESET);
    }

    /*
    Method       - displayDisplayCard
    return type  - void
    Method Comment : Displays large ASCII art header for "Display Card" with search options
     */
    public void displayDisplayCard() {
        System.out.println(BOLD + BLUE +
                " ____    ___   ____    ____    _          _     __   __      ____      _      ____    ____  \n" +
                "|  _ \\  |_ _| / ___|  |  _ \\  | |        / \\    \\ \\ / /     / ___|    / \\    |  _ \\  |  _ \\ \n" +
                "| | | |  | |  \\___ \\  | |_) | | |       / _ \\    \\ V /     | |       / _ \\   | |_) | | | | |\n" +
                "| |_| |  | |   ___) | |  __/  | |___   / ___ \\    | |      | |___   / ___ \\  |  _ <  | |_| |\n" +
                "|____/  |___| |____/  |_|     |_____| /_/   \\_\\   |_|       \\____| /_/   \\_\\ |_| \\_\\ |____/ " + RESET);
        System.out.println(GREEN + "[1] Search card by name" + RESET);
        System.out.println(GREEN + "[2] Search card by card no." + RESET);
        System.out.println(GREEN + "[0] Back\n" + RESET);
    }

    public void displaySellCard(double money) {
        System.out.println(BOLD + BLUE +
                " ____    _____   _       _           ____      _      ____    ____  \n" +
                "/ ___|  | ____| | |     | |         / ___|    / \\    |  _ \\  |  _ \\ \n" +
                "\\___ \\  |  _|   | |     | |        | |       / _ \\   | |_) | | | | |\n" +
                " ___) | | |___  | |___  | |___     | |___   / ___ \\  |  _ <  | |_| |\n" +
                "|____/  |_____| |_____| |_____|     \\____| /_/   \\_\\ |_| \\_\\ |____/ " + RESET);
        System.out.println(GREEN + "You currently have $" + money + RESET);
        System.out.println(GREEN + "[1] Search card by name" + RESET);
        System.out.println(GREEN + "[2] Search card by card no." + RESET);
        System.out.println(GREEN + "[0] Back\n" + RESET);
    }

    /*
    Method               - displayCardToUpdateCount
    String name          - Name of the card to be modified
    int collectionCount  - Current number of copies in collection
    return type          - void
    Method Comment : Shows the current card name and its count before modification
     */
    public void displayCardToUpdateCount(String name, int collectionCount) {
        System.out.println("\n"+ GREEN + name + RESET + " - " +
                RED + collectionCount + RESET + " copies in collection\n");
    }

    /*
    Method       - displayUpdateCardCountOptions
    return type  - void
    Method Comment : Displays modification options (increment/decrement) for card counts
     */
    public void displayUpdateCardCountOptions() {
        String UNDERLINE = "\u001B[4m";
        System.out.println(BOLD + UNDERLINE + RED + "O P T I O N S" + RESET);
        System.out.println(GREEN + "[1] Increment Card Count" + RESET);
        System.out.println(GREEN + "[2] Decrement Card Count" + RESET);
        System.out.println(GREEN + "[0] Back\n" + RESET);
    }

    /*
    Method       - displayCollection
    return type  - void
    Method Comment : Shows formatted header for the full collection display with borders
     */
    public void displayCollection() {
        String UNDERLINE = "\u001B[4m";
        String BOLD = "\u001B[1m";

        String formattedTitle = BOLD + UNDERLINE + RED + "C O L L E C T I O N" + RESET;
        printBorder();
        System.out.printf(BLUE + "|" + RESET +" %-57s " + BLUE + "|" + RESET + "\n", formattedTitle);
        printBorder();
    }

    /*
    Method               - displayCollectionCard
    int collectionCount  - Number of copies in collection
    String name          - Name of the card
    return type          - void
    Method Comment : Prints an individual card entry in collection view with collection count and borders
     */
    public void displayCollectionCard(int collectionCount, String name) {
        String MAGENTA = "\u001B[35m";

        System.out.printf(BLUE + "|" + RESET + GREEN + " %-26s " + RESET + BLUE + "|" + RESET +
                MAGENTA + " Count : %3d " + RESET + BLUE + "|" + RESET + "\n", name, collectionCount);
        printBorder();
    }

    public void displayCardToBeSold(String cardName, double cardValue, double currentMoney) {
        System.out.println("\n" + GREEN + cardName + " - $" + cardValue + RESET);
        System.out.println(GREEN + "If sold, current money: $" + currentMoney + " -> $" + (currentMoney + cardValue) + RESET);
    }

    /*
    Method       - printBorder (private)
    return type  - void
    Method Comment : Helper method to print consistent bordered lines for collection displays
     */
    private void printBorder() {
        System.out.println(BLUE + "+" + "-".repeat(42) + "+" + RESET);
    }
}