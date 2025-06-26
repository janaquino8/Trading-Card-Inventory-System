package src.view;

public class DeckView {
    private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private final String BLUE = "\u001B[34m";


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
