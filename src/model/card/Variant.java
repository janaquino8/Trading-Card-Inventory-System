package src.model.card;

/**
 * Represents card variant with name and value multiplier.
 */
public class Variant {
    /**
     * Name of the variant.
     */
    private String name;

    /**
     * Multiplier of the value of the card.
     */
    private double valueMultiplier;

    /**
     * Creates specific variant based on input code.
     * @param variant numeric code representing variant type
     */
    public Variant(int variant) {
        switch (variant) {
            case 1:
                this.setName("Normal");
                this.setValueMultiplier(1);
                break;
            case 2:
                this.setName("Extended-art");
                this.setValueMultiplier(1.5);
                break;
            case 3:
                this.setName("Full-art");
                this.setValueMultiplier(2);
                break;
            case 4:
                this.setName("Alt-art");
                this.setValueMultiplier(3);
                break;
        }
    }

    /**
     * Returns the full name of the variant.
     * @return name of the variant
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the variant's name.
     * @param name new name to assign to the variant
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the multiplier applied to card's base value.
     * @return value multiplier corresponding to the variant
     */
    public double getValueMultiplier() {
        return this.valueMultiplier;
    }

    /**
     * Sets the value multiplier for this variant.
     * @param valueMultiplier new multiplier value to assign
     */
    public void setValueMultiplier(double valueMultiplier) {
        this.valueMultiplier = valueMultiplier;
    }
}