package src.view;

public class BinderView {
    private final String RESET = "\u001B[0m";
    private final String BOLD = "\u001B[1m";
    private final String UNDERLINE = "\u001B[4m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String BLUE = "\u001B[34m";
    private final String MAGENTA = "\u001B[35m";

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

    public void displayBinder(String binderName) {
        String formattedTitle = BOLD + UNDERLINE + RED + binderName + RESET;
        printBorder(28);
        System.out.printf(BLUE + "|" + RESET +" %-43s " + BLUE + "|" + RESET + "\n", formattedTitle);
        printBorder(28);
    }

    public void displayBinderCard(String name) {
        System.out.printf(BLUE + "|" + RESET + MAGENTA + " %-26s " + RESET + BLUE + "|" + RESET + "\n", name);
        printBorder(28);
    }

    public void displayTrade(String incomingName, double incomingValue, String outgoingName, double outgoingValue) {
        String formattedTitle = BOLD + UNDERLINE + RED + "T R A D E  E X C H A N G E" + RESET;
        printBorder(51);
        System.out.printf(BLUE + "|" + RESET + " %-66s " + BLUE + "|" + RESET + "\n", formattedTitle);
        printBorder(51);
        printTradeRow("Incoming", incomingName, incomingValue);
        printBorder(51);
        printTradeRow("Outgoing", outgoingName, outgoingValue);
        printBorder(51);
    }

    private void printTradeRow(String trade, String name, double value) {
        System.out.printf(BLUE + "|" + RESET + RED + " %s " + RESET +
                        BLUE + "|" + RESET + GREEN + " %-26s " + RESET +
                        BLUE + "|" + RESET + MAGENTA + " $%8.2f " + RESET + BLUE + "|" + RESET + "\n"
                , trade, name, value);
    }

    private void printBorder(int count) {
        System.out.println(BLUE + "+" + "-".repeat(count) + "+" + RESET);
    }
}