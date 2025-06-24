package src.view;

public class BinderView {

    public void displayBinder() {
        String formattedTitle = "\u001B[1m\u001B[4m\u001B[31mB I N D E R\u001B[0m";
        String formattedBorder = "\u001B[34m|\u001B[0m";
        printBorder();
        System.out.printf("%s %-43s %s\n", formattedBorder, formattedTitle, formattedBorder);
        printBorder();
    }

    public void displayBinderCard(String name) {
        String formattedBorder = "\u001B[34m|\u001B[0m";
        System.out.printf("%s \u001B[32m%-26s\u001B[0m %s\n", formattedBorder, name, formattedBorder);
        printBorder();
    }

    public void printBorder() {
        String formattedPlus = "\u001B[34m+\u001B[0m";
        System.out.printf("%s" + "\u001B[34m-\u001B[0m".repeat(28) + "%s\n", formattedPlus, formattedPlus);
    }
}
