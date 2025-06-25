package src.view;

public class BinderView {
    private final String RESET = "\u001B[0m";
    private final String BOLD = "\u001B[1m";
    private final String UNDERLINE = "\u001B[4m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String BLUE = "\u001B[34m";

    public void displayBinder() {
        String formattedTitle = BOLD + UNDERLINE + RED + "B I N D E R" + RESET;
        printBorder(28);
        System.out.printf(BLUE + "|" + RESET +" %-43s " + BLUE + "|" + RESET + "\n", formattedTitle);
        printBorder(28);
    }

    public void displayBinderCard(String name) {
        System.out.printf(BLUE + "|" + RESET + GREEN + " %-26s " + RESET + BLUE + "|" + RESET + "\n", name);
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
                        BLUE + "|" + RESET + RED + " $%8.2f " + RESET + BLUE + "|" + RESET + "\n"
                , trade, name, value);
    }

    private void printBorder(int count) {
        System.out.println(BLUE + "+" + "-".repeat(count) + "+" + RESET);
    }
}