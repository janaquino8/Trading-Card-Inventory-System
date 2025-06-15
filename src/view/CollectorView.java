package src.view;

public class CollectorView {
    CollectorView() {

    }

    public void printHeader () {
        System.out.println("""
                    ____________________ .___  _________   \s
                 /\\ \\__    ___/\\_   ___ \\|   |/   _____/ /\\\s
                 \\/   |    |   /    \\  \\/|   |\\_____  \\  \\/\s
                 /\\   |    |   \\     \\___|   |/        \\ /\\\s
                 \\/   |____|    \\______  /___/_______  / \\/\s
                                       \\/            \\/\s
                """);
        System.out.println();
    }

    public void start() {
        System.out.println("Welcome to the Trading Card Inventory System! The best place for all your trading card needs!");
    }
}
