package src.view;

public class CardView {
    public void displayCard(String name, String rarityName, String variantName, double baseValue, double finalValue) {
        String RESET = "\u001B[0m";
        String BOLD = "\u001B[1m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String MAGENTA = "\u001B[35m";
        String CYAN = "\u001B[36m";

        String rarityColor;
        switch (rarityName.toLowerCase()) {
            case "uncommon":
                rarityColor = GREEN;
                break;
            case "rare":
                rarityColor = MAGENTA;
                break;
            case "legendary":
                rarityColor = RED;
                break;
            default:
                rarityColor = YELLOW;
                break;
        }

        String formattedBaseValue = String.format("%,.2f", baseValue);
        String formattedFinalValue = String.format("%,.2f", finalValue);

        System.out.println(BOLD + CYAN + "╔══════════════════════════════════════╗" + RESET);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + rarityColor + " %-36s " + RESET + BOLD + CYAN + "║\n" + RESET, name);

        System.out.println(BOLD + CYAN + "╠──────────────────────────────────────╣" + RESET);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + " Variant:" + rarityColor + " %-27s " + RESET + BOLD + CYAN + "║\n" + RESET, variantName);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + " Rarity:" + rarityColor + " %-28s " + RESET + BOLD + CYAN + "║\n" + RESET, rarityName);

        System.out.println(BOLD + CYAN + "╠──────────────────────────────────────╣" + RESET);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + " Base Value:" + rarityColor + " %-24s " + RESET + BOLD + CYAN + "║\n" + RESET, formattedBaseValue);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + " Current Value:" + rarityColor + " %-21s " + RESET + BOLD + CYAN + "║\n" + RESET, formattedFinalValue);

        System.out.println(BOLD + CYAN + "╚══════════════════════════════════════╝" + RESET);
    }
}
