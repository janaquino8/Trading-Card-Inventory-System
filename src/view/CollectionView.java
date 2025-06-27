package src.view;

public class CollectionView {
    private final String RESET = "\u001B[0m";
    private final String BOLD = "\u001B[1m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String BLUE = "\u001B[34m";

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
        }
        System.out.println();
    }

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

    public void displayCardToUpdateCount(String name, int collectionCount) {
        System.out.println("\n"+ GREEN + name + RESET + " - " +
                RED + collectionCount + RESET + " copies in collection\n");
    }

    public void displayUpdateCardCountOptions() {
        String UNDERLINE = "\u001B[4m";
        System.out.println(BOLD + UNDERLINE + RED + "O P T I O N S" + RESET);
        System.out.println(GREEN + "[1] Increment Card Count" + RESET);
        System.out.println(GREEN + "[2] Decrement Card Count" + RESET);
        System.out.println(GREEN + "[0] Back\n" + RESET);
    }

    public void displayCollection() {
        String UNDERLINE = "\u001B[4m";
        String BOLD = "\u001B[1m";

        String formattedTitle = BOLD + UNDERLINE + RED + "C O L L E C T I O N" + RESET;
        printBorder();
        System.out.printf(BLUE + "|" + RESET +" %-57s " + BLUE + "|" + RESET + "\n", formattedTitle);
        printBorder();
    }

    public void displayCollectionCard(int collectionCount, String name) {
        String MAGENTA = "\u001B[35m";

        System.out.printf(BLUE + "|" + RESET + GREEN + " %-26s " + RESET + BLUE + "|" + RESET +
                MAGENTA + " Count : %3d " + RESET + BLUE + "|" + RESET + "\n", name, collectionCount);
        printBorder();
    }

    private void printBorder() {
        System.out.println(BLUE + "+" + "-".repeat(42) + "+" + RESET);
    }
}