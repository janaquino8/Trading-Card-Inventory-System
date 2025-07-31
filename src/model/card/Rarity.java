package src.model.card;

/**
 * Represents card rarity with names.
 */
public class Rarity {
    /**
     * Name of the rarity.
     */
    private String name;

    /**
     * Creates specific rarity based on input code.
     * @param rarity numeric code representing rarity level
     */
    public Rarity(int rarity) {
        switch (rarity) {
            case 1:
                this.setName("Common");
            case 2:
                this.setName("Uncommon");
                break;
            case 3:
                this.setName("Rare");
                break;
            case 4:
                this.setName("Legendary");
                break;
        }
    }

    /**
     * Returns the full name of the rarity level.
     * @return name of the rarity
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the rarity's name.
     * @param name new name to assign to the rarity
     */
    public void setName(String name) {
        this.name = name;
    }
}