package src.view;

public class CollectionView {
    private final String RESET = "\u001B[0m";
    private final String BOLD = "\u001B[1m";
    private final String UNDERLINE = "\u001B[4m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";

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
        }
        System.out.println();
    }

    public void displayUpdateCardCount() {
        System.out.println(BOLD + UNDERLINE + RED + "\nU P D A T E  C A R D  C O U N T" + RESET);
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