package src.view;

public class CollectionView {
    private final String RESET = "\u001B[0m";
    private final String BOLD = "\u001B[1m";
    private final String UNDERLINE = "\u001B[4m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String BLUE = "\u001B[34m";

    public void printConfirmationMsg(int option) {
        switch (option) {
            case 0:
                System.out.println(UNDERLINE + RED + "\nAction successfully cancelled.\n" + RESET);
                break;
            case 1:
                System.out.println(UNDERLINE + GREEN + "\nCard count successfully incremented.\n" + RESET);
                break;
            case 2:
                System.out.println(UNDERLINE + GREEN + "\nCard count successfully decremented.\n" + RESET);
                break;
            case 3:
                System.out.println(UNDERLINE + RED + "\nCard not found.\n" + RESET);
                break;
            case 4:
                System.out.println(UNDERLINE + RED + "\nCard has no copies in collection.\n" + RESET);
        }
    }

    public void displayUpdateCardCount() {
        System.out.println(BOLD + UNDERLINE + RED + "U P D A T E  C A R D  C O U N T" + RESET);
        System.out.println(GREEN + "[1] Search card by name" + RESET);
        System.out.println(GREEN + "[2] Search card by card no." + RESET);
        System.out.println(GREEN + "[0] Back\n" + RESET);
    }

    public void displayCardToUpdateCount(String name, int collectionCount) {
        System.out.println("\n"+ GREEN + name + RESET + " - " +
                RED + collectionCount + RESET + " copies in collection\n");
    }

    public void displayUpdateCardCountOptions() {
        System.out.println(BOLD + UNDERLINE + RED + "O P T I O N S" + RESET);
        System.out.println(GREEN + "[1] Increment Card Count" + RESET);
        System.out.println(GREEN + "[2] Decrement Card Count" + RESET);
        System.out.println(GREEN + "[0] Back\n" + RESET);
    }
}