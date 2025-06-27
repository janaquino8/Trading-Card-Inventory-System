package src.view;

/*
CardView - Displays the card information in a formatted box with color coding
*/
public class CardView {

    /*
    Method               - displayCard
    String name          - Name of the card
    int cardNo           - Unique identifier number of the card
    String rarityName    - Rarity level of the card (Common, Uncommon, Rare, Legendary)
    String variantName   - Specific variant of the card (Normal, Extended-art, Full-art, Alt-art)
    int collectionCount  - Copies that currently exist in collection
    double baseValue     - Base value of card WITHOUT the variant's increase in value
    double finalValue    - Final value of card AFTER the variant's increase in value
    return type          - void
    Method Comment : Displays a visually formatted card with colored borders, formatted numbers, and organized sections showing all card details
     */
    public void displayCard(String name, int cardNo, String rarityName, String variantName, int collectionCount, double baseValue, double finalValue) {
        String RESET = "\u001B[0m";
        String BOLD = "\u001B[1m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String MAGENTA = "\u001B[35m";
        String CYAN = "\u001B[36m";

        String rarityColor = switch (rarityName) {
            case "Common" -> YELLOW;
            case "Uncommon" -> GREEN;
            case "Rare" -> MAGENTA;
            default -> RED;
        };

        String formattedCardNo = String.format("%,d", cardNo);
        String formattedCollectionCount = String.format("%,d", collectionCount);
        String formattedBaseValue = String.format("%,.2f", baseValue);
        String formattedFinalValue = String.format("%,.2f", finalValue);

        System.out.println(BOLD + CYAN + "╔══════════════════════════════════════╗" + RESET);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + rarityColor + " %-36s " + RESET + BOLD + CYAN + "║\n" + RESET, name);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + " Card Number:" + rarityColor + " %-23s " + RESET + BOLD + CYAN + "║\n" + RESET, formattedCardNo);

        System.out.println(BOLD + CYAN + "╠──────────────────────────────────────╣" + RESET);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + " Variant:" + rarityColor + " %-27s " + RESET + BOLD + CYAN + "║\n" + RESET, variantName);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + " Rarity:" + rarityColor + " %-28s " + RESET + BOLD + CYAN + "║\n" + RESET, rarityName);

        System.out.println(BOLD + CYAN + "╠──────────────────────────────────────╣" + RESET);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + " Collection Count:" + rarityColor + " %-18s " + RESET + BOLD + CYAN + "║\n" + RESET, formattedCollectionCount);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + " Base Value:" + rarityColor + " $%-23s " + RESET + BOLD + CYAN + "║\n" + RESET, formattedBaseValue);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + " Final Value:" + rarityColor + " $%-22s " + RESET + BOLD + CYAN + "║\n" + RESET, formattedFinalValue);

        System.out.println(BOLD + CYAN + "╚══════════════════════════════════════╝" + RESET);
    }
}
