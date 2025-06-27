package src.view;

public class DeckView {
    private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private final String BLUE = "\u001B[34m";
    private final String GREEN = "\u001B[32m";
    private final String BOLD = "\u001B[1m";

    public void printConfirmationMsg(int option) {
        System.out.println();
        switch (option) {
            case 0:
                System.out.println(GREEN + "Action successfully cancelled." + RESET);
                break;
            case 1:
                System.out.println(GREEN + "Card successfully added to deck." + RESET);
                break;
            case 2:
                System.out.println(RED + "There are no cards currently in the collection." + RESET);
                break;
            case 3:
                System.out.println(RED + "All decks are full." + RESET);
                break;
            case 4:
                System.out.println(RED + "Deck doesn't exist." + RESET);
                break;
            case 5:
                System.out.println(RED + "Deck is full." + RESET);
                break;
            case 6:
                System.out.println(RED + "Card doesn't exist" + RESET);
                break;
            case 7:
                System.out.println(RED + "No copies of the card exist in the collection" + RESET);
                break;
            case 8:
                System.out.println(RED + "All decks are empty." + RESET);
                break;
            case 9:
                System.out.println(RED + "Deck is empty." + RESET);
                break;
            case 10:
                System.out.println(RED + "Card is not in deck." + RESET);
                break;
            case 11:
                System.out.println(GREEN + "Card successfully removed from deck." + RESET);
                break;
            case 12:
                System.out.println(RED + "Card is already in the deck." + RESET);
                break;
        }
        System.out.println();
    }

    public void displayAddCardToDeck() {
        System.out.println(BOLD + BLUE +
                "    _      ____    ____        ____      _      ____    ____                  ____    _____    ____   _  __\n" +
                "   / \\    |  _ \\  |  _ \\      / ___|    / \\    |  _ \\  |  _ \\                |  _ \\  | ____|  / ___| | |/ /\n" +
                "  / _ \\   | | | | | | | |    | |       / _ \\   | |_) | | | | |     _____     | | | | |  _|   | |     | ' / \n" +
                " / ___ \\  | |_| | | |_| |    | |___   / ___ \\  |  _ <  | |_| |    |_____|    | |_| | | |___  | |___  | . \\ \n" +
                "/_/   \\_\\ |____/  |____/      \\____| /_/   \\_\\ |_| \\_\\ |____/                |____/  |_____|  \\____| |_|\\_\\" + RESET);
    }

    public void displayRemoveCardFromDeck() {
        System.out.println(BOLD + BLUE +
                " ____    _____   __  __    ___   __     __  _____       ____      _      ____    ____                  ____    _____    ____   _  __\n" +
                "|  _ \\  | ____| |  \\/  |  / _ \\  \\ \\   / / | ____|     / ___|    / \\    |  _ \\  |  _ \\                |  _ \\  | ____|  / ___| | |/ /\n" +
                "| |_) | |  _|   | |\\/| | | | | |  \\ \\ / /  |  _|      | |       / _ \\   | |_) | | | | |     _____     | | | | |  _|   | |     | ' / \n" +
                "|  _ <  | |___  | |  | | | |_| |   \\ V /   | |___     | |___   / ___ \\  |  _ <  | |_| |    |_____|    | |_| | | |___  | |___  | . \\ \n" +
                "|_| \\_\\ |_____| |_|  |_|  \\___/     \\_/    |_____|     \\____| /_/   \\_\\ |_| \\_\\ |____/                |____/  |_____|  \\____| |_|\\_\\" + RESET);
    }

    public void displayViewDeck() {
        System.out.println(BOLD + BLUE +
                "__     __  ___   _____  __        __     ____    _____    ____   _  __\n" +
                "\\ \\   / / |_ _| | ____| \\ \\      / /    |  _ \\  | ____|  / ___| | |/ /\n" +
                " \\ \\ / /   | |  |  _|    \\ \\ /\\ / /     | | | | |  _|   | |     | ' / \n" +
                "  \\ V /    | |  | |___    \\ V  V /      | |_| | | |___  | |___  | . \\ \n" +
                "   \\_/    |___| |_____|    \\_/\\_/       |____/  |_____|  \\____| |_|\\_\\" + RESET);
    }

    public void displayDeck(String deckName) {
        String UNDERLINE = "\u001B[4m";
        String BOLD = "\u001B[1m";

        String formattedTitle = BOLD + UNDERLINE + RED + deckName + RESET;
        printBorder();
        System.out.printf(BLUE + "|" + RESET +" %-47s " + BLUE + "|" + RESET + "\n", formattedTitle);
        printBorder();
    }

    public void displayDeckCard(int index, String name) {
        String MAGENTA = "\u001B[35m";

        System.out.printf(BLUE + "|" + RESET + MAGENTA + " %2d " + RESET +
                BLUE + "|" + RESET + GREEN + " %-26s" + RESET + BLUE + "|" + RESET + "\n", index, name);
        printBorder();
    }

    public void displayCardSearchOptions() {
        System.out.println(GREEN + "[1] Search card by name" + RESET);
        System.out.println(GREEN + "[2] Search card by card no." + RESET);
        System.out.println(GREEN + "[0] Back\n" + RESET);
    }

    private void printBorder() {
        System.out.println(BLUE + "+" + "-".repeat(32) + "+" + RESET);
    }
}
