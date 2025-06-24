package src.view;

public class CollectionView {

    public void printConfirmationMsg(int option) {
        System.out.println();

        switch (option) {
            case 0:
                System.out.println("Action successfully cancelled.");
                break;
            case 1:
                System.out.println("Card count successfully incremented.");
                break;
            case 2:
                System.out.println("Card count successfully decremented.");
                break;
            case 3:
                System.out.println("Card not found.");
                break;
            case 4:
                System.out.println("Card has no copies in collection.");
        }

        System.out.println();
    }

    public void displayUpdateCardCount() {
        System.out.println("""
                       __   __       ___  ___     __        __   __      __   __            ___\s
                 |  | |__) |  \\  /\\   |  |__     /  `  /\\  |__) |  \\    /  ` /  \\ |  | |\\ |  | \s
                 \\__/ |    |__/ /~~\\  |  |___    \\__, /~~\\ |  \\ |__/    \\__, \\__/ \\__/ | \\|  | \s
                """);
        System.out.println("[1] Search card by name");
        System.out.println("[2] Search card by card no.");
        System.out.println("[0] Back\n");
    }

    public void displayCardtoUpdateCount(String name, int collectionCount) {
        System.out.println("\n"+ name + " - " + collectionCount + " copies in collection\n");
    }

    public void displayUpdateCardCountOptions() {
        System.out.println("[1] Increment Card Count");
        System.out.println("[2] Decrement Card Count");
        System.out.println("[0] Back\n");
    }


}
