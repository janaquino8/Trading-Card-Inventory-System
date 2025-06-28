package src.view;

/*
BinderView - Handles all visual displays related to binder operations (adding/removing cards, viewing binders, and trades)
 */
public class BinderView {
    private final String RESET = "\u001B[0m";
    private final String BOLD = "\u001B[1m";
    private final String UNDERLINE = "\u001B[4m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String BLUE = "\u001B[34m";
    private final String MAGENTA = "\u001B[35m";

    /*
    Method       - printConfirmationMsg
    int option   - Numeric code representing different confirmation/error messages
    return type  - void
    Method Comment : Prints colored status messages based on operation results (success/error cases)
     */
    public void printConfirmationMsg(int option) {
        System.out.println();
        switch (option) {
            case 0:
                System.out.println(GREEN + "Action successfully cancelled." + RESET);
                break;
            case 1:
                System.out.println(GREEN + "Card successfully added to binder." + RESET);
                break;
            case 2:
                System.out.println(RED + "There are no cards currently in the collection." + RESET);
                break;
            case 3:
                System.out.println(RED + "All binders are full." + RESET);
                break;
            case 4:
                System.out.println(RED + "Binder doesn't exist." + RESET);
                break;
            case 5:
                System.out.println(RED + "Binder is full." + RESET);
                break;
            case 6:
                System.out.println(RED + "Card doesn't exist" + RESET);
                break;
            case 7:
                System.out.println(RED + "No copies of the card exist in the collection" + RESET);
                break;
            case 8:
                System.out.println(RED + "All binders are empty." + RESET);
                break;
            case 9:
                System.out.println(RED + "Binder is empty." + RESET);
                break;
            case 10:
                System.out.println(RED + "Card is not in binder." + RESET);
                break;
            case 11:
                System.out.println(GREEN + "Card successfully removed from binder." + RESET);
                break;
            case 12:
                System.out.println(RED + "WARNING: Difference in value is greater than or equal to $1.00" + RESET);
                break;
            case 13:
                System.out.println(GREEN + "Trade completed." + RESET);
                break;
        }
        System.out.println();
    }

    /*
    Method       - displayAddCardToBinder
    return type  - void
    Method Comment : Displays large ASCII art header for "Add Card to Binder" operation
     */
    public void displayAddCardToBinder() {
        System.out.println(BOLD + BLUE +
                "    _      ____    ____        ____      _      ____    ____                  ____    ___   _   _   ____    _____   ____  \n" +
                "   / \\    |  _ \\  |  _ \\      / ___|    / \\    |  _ \\  |  _ \\                | __ )  |_ _| | \\ | | |  _ \\  | ____| |  _ \\ \n" +
                "  / _ \\   | | | | | | | |    | |       / _ \\   | |_) | | | | |     _____     |  _ \\   | |  |  \\| | | | | | |  _|   | |_) |\n" +
                " / ___ \\  | |_| | | |_| |    | |___   / ___ \\  |  _ <  | |_| |    |_____|    | |_) |  | |  | |\\  | | |_| | | |___  |  _ < \n" +
                "/_/   \\_\\ |____/  |____/      \\____| /_/   \\_\\ |_| \\_\\ |____/                |____/  |___| |_| \\_| |____/  |_____| |_| \\_\\" + RESET);
    }

    /*
    Method       - displayRemoveCardFromBinder
    return type  - void
    Method Comment : Displays large ASCII art header for "Remove Card from Binder" operation
     */
    public void displayRemoveCardFromBinder() {
        System.out.println(BOLD + BLUE +
                " ____    _____   __  __    ___   __     __  _____       ____      _      ____    ____                  ____    ___   _   _   ____    _____   ____  \n" +
                "|  _ \\  | ____| |  \\/  |  / _ \\  \\ \\   / / | ____|     / ___|    / \\    |  _ \\  |  _ \\                | __ )  |_ _| | \\ | | |  _ \\  | ____| |  _ \\ \n" +
                "| |_) | |  _|   | |\\/| | | | | |  \\ \\ / /  |  _|      | |       / _ \\   | |_) | | | | |     _____     |  _ \\   | |  |  \\| | | | | | |  _|   | |_) |\n" +
                "|  _ <  | |___  | |  | | | |_| |   \\ V /   | |___     | |___   / ___ \\  |  _ <  | |_| |    |_____|    | |_) |  | |  | |\\  | | |_| | | |___  |  _ < \n" +
                "|_| \\_\\ |_____| |_|  |_|  \\___/     \\_/    |_____|     \\____| /_/   \\_\\ |_| \\_\\ |____/                |____/  |___| |_| \\_| |____/  |_____| |_| \\_\\" + RESET);
    }

    /*
    Method       - displayViewBinder
    return type  - void
    Method Comment : Displays large ASCII art header for "View Binder" operation
     */
    public void displayViewBinder() {
        System.out.println(BOLD + BLUE +
                "__     __  ___   _____  __        __     ____    ___   _   _   ____    _____   ____  \n" +
                "\\ \\   / / |_ _| | ____| \\ \\      / /    | __ )  |_ _| | \\ | | |  _ \\  | ____| |  _ \\ \n" +
                " \\ \\ / /   | |  |  _|    \\ \\ /\\ / /     |  _ \\   | |  |  \\| | | | | | |  _|   | |_) |\n" +
                "  \\ V /    | |  | |___    \\ V  V /      | |_) |  | |  | |\\  | | |_| | | |___  |  _ < \n" +
                "   \\_/    |___| |_____|    \\_/\\_/       |____/  |___| |_| \\_| |____/  |_____| |_| \\_\\" + RESET);
    }

    /*
    Method       - displayTrade
    return type  - void
    Method Comment : Displays large ASCII art header for general trade operations
     */
    public void displayTrade() {
        System.out.println(BOLD + BLUE +
                " _____   ____       _      ____    _____ \n" +
                "|_   _| |  _ \\     / \\    |  _ \\  | ____|\n" +
                "  | |   | |_) |   / _ \\   | | | | |  _|  \n" +
                "  | |   |  _ <   / ___ \\  | |_| | | |___ \n" +
                "  |_|   |_| \\_\\ /_/   \\_\\ |____/  |_____|" + RESET);
    }

    /*
    Method             - displayBinder
    String binderName  - Name of the binder to display
    return type        - void
    Method Comments : Shows a formatted binder title with borders and underlining
     */
    public void displayBinder(String binderName) {
        String formattedTitle = BOLD + UNDERLINE + RED + binderName + RESET;
        printBorder(28);
        System.out.printf(BLUE + "|" + RESET +" %-43s " + BLUE + "|" + RESET + "\n", formattedTitle);
        printBorder(28);
    }

    /*
    Method       - displayBinderCard
    String name  - Name of the card to display in binder view
    return type  - void
    Method Comment : Prints an individual card entry within binder view with borders
     */
    public void displayBinderCard(String name) {
        System.out.printf(BLUE + "|" + RESET + MAGENTA + " %-26s " + RESET + BLUE + "|" + RESET + "\n", name);
        printBorder(28);
    }

    /*
    Method       - displayCardSearchOptions
    return type  - void
    Method Comment : Shows menu options for card search methods (by name or number) with an exit
     */
    public void displayCardSearchOptions() {
        System.out.println(GREEN + "[1] Search card by name" + RESET);
        System.out.println(GREEN + "[2] Search card by card no." + RESET);
        System.out.println(GREEN + "[0] Back\n" + RESET);
    }

    /*
    Method                - displayTrade
    String incomingName   - Name of card being received
    double incomingValue  - Value of incoming card
    String outgoingName   - Name of card being traded away
    double outgoingValue  - Value of outgoing card
    double difference     - Net value difference between cards
    return type           - void
    Method Comment : Displays detailed trade comparison with values and net difference in formatted table
     */
    public void displayTrade(String incomingName, double incomingValue, String outgoingName, double outgoingValue, double difference) {
        String formattedTitle = BOLD + UNDERLINE + RED + "T R A D E  E X C H A N G E" + RESET;
        String formattedDifference = String.format("%,.2f", difference);

        printBorder(51);
        System.out.printf(BLUE + "|" + RESET + " %-66s " + BLUE + "|" + RESET + "\n", formattedTitle);
        printBorder(51);
        printTradeRow("Incoming", incomingName, incomingValue);
        printBorder(51);
        printTradeRow("Outgoing", outgoingName, outgoingValue);
        printBorder(51);
        System.out.printf(BLUE + "|" + RESET + " Difference:" + MAGENTA + " $%-36s " + RESET + BLUE + "|" + RESET + "\n", formattedDifference);
        printBorder(51);
    }

    /*
    Method        - printTradeRow (private)
    String trade  - Trade direction label ("Incoming"/"Outgoing")
    String name   - Card name
    double value  - Card monetary value
    return type   - void
    Method Comment : Helper method to format individual rows in trade display table
     */
    private void printTradeRow(String trade, String name, double value) {
        System.out.printf(BLUE + "|" + RESET + RED + " %s " + RESET +
                        BLUE + "|" + RESET + GREEN + " %-26s " + RESET +
                        BLUE + "|" + RESET + MAGENTA + " $%8.2f " + RESET + BLUE + "|" + RESET + "\n"
                , trade, name, value);
    }

    /*
    Method       - printBorder (private)
    int count    - Number of dashes in the border
    return type  - void
    Method Comment : Helper method to print consistent bordered lines for binder displays
     */
    private void printBorder(int count) {
        System.out.println(BLUE + "+" + "-".repeat(count) + "+" + RESET);
    }
}