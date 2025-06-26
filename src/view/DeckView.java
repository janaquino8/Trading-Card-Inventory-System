package src.view;

public class DeckView {
    private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private final String BLUE = "\u001B[34m";
    private final String GREEN = "\u001B[32m";

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

    public void displayDeck(String deckName) {
        String UNDERLINE = "\u001B[4m";
        String BOLD = "\u001B[1m";

        String formattedTitle = BOLD + UNDERLINE + RED + deckName + RESET;
        printBorder();
        System.out.printf(BLUE + "|" + RESET +" %-47s " + BLUE + "|" + RESET + "\n", formattedTitle);
        printBorder();
    }

    public void displayDeckCard(int index, String name) {
        String GREEN = "\u001B[32m";

        System.out.printf(BLUE + "|" + RESET +
                RED + " %2d " + RESET + BLUE + "|" + RESET +
                GREEN + " %-26s" + RESET + BLUE + "|" + RESET + "\n", index, name);
        printBorder();
    }

    private void printBorder() {
        System.out.println(BLUE + "+" + "-".repeat(32) + "+" + RESET);
    }
}
