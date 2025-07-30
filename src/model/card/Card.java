package src.model.card;

/**
 * Represents a card in the program
 */
public class Card {
    private String name;
    private Rarity rarity;
    private Variant variant;
    private double baseValue;
    private double finalValue;
    private int collectionCount;
    private int cardNo;

    /**
     * Constructor to construct a Card object.
     */
    public Card() {
        this.setCollectionCount(1);
    }

    /**
     * Constructor to construct a Card object not given the variant (mainly for common/uncommon cards).
     * @param name name of the card
     * @param rarity integer representation of the rarity of the card
     * @param baseValue base value of the card
     * @param cardNo card number of the card
     */
    public Card(String name, int rarity, double baseValue, int cardNo) {
        this();
        this.setName(name);
        this.rarity = new Rarity(rarity);
        this.variant = new Variant(1);
        this.setBaseValue(baseValue);
        this.setCardNo(cardNo);
        this.setFinalValue(baseValue);
    }

    /**
     * Constructor to construct a Card object given the variant.
     * @param name name of the card
     * @param rarity integer representation of the rarity of the card
     * @param variant integer representation of the variant of the card
     * @param baseValue base value of the card
     * @param cardNo card number of the card
     */
    public Card(String name, int rarity, int variant, double baseValue, int cardNo) {
        this(name, rarity, baseValue, cardNo);
        if (variant != 1) {
            this.variant = new Variant(variant);
        }
        this.calculateFinalValue();
    }

    /**
     * Increments the collection count of the card.
     */
    public void incrementCollectionCount() {
        this.setCollectionCount(this.getCollectionCount() + 1);
    }

    /**
     * Decrements the collection count of the card.
     */
    public void decrementCollectionCount() {
        if (this.getCollectionCount() > 0) {
            this.setCollectionCount(this.getCollectionCount() - 1);
        }
    }

    /**
     * Calculates the final value of the card using the value multiplier set by the variant.
     */
    private void calculateFinalValue() {
        this.setFinalValue(this.getBaseValue() * this.getVariant().getValueMultiplier());
    }

    /**
     * Getter for name.
     * @return name of the card
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for name.
     * @param name new name of the card
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for Rarity.
     * @return Rarity of the card
     */
    public Rarity getRarity() {
        return this.rarity;
    }

    /**
     * Getter for Variant.
     * @return Variant of the card
     */
    public Variant getVariant() {
        return this.variant;
    }

    /**
     * Getter for base value.
     * @return base value of the card
     */
    public double getBaseValue() {
        return this.baseValue;
    }

    /**
     * Setter for base value.
     * @param baseValue new base value of the card
     */
    public void setBaseValue(double baseValue) {
        this.baseValue = baseValue;
    }

    /**
     * Getter for final value.
     * @return final value of the card
     */
    public double getFinalValue() {
        return this.finalValue;
    }

    /**
     * Setter for final value.
     * @param finalValue new final value of the card
     */
    public void setFinalValue(double finalValue) {
        this.finalValue = finalValue;
    }

    /**
     * Getter for collection count.
     * @return collection count of the card
     */
    public int getCollectionCount() {
        return this.collectionCount;
    }

    /**
     * Setter for collection count.
     * @param collectionCount new collection count of the card
     */
    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }

    /**
     * Getter for card number.
     * @return card number of the card
     */
    public int getCardNo() {
        return this.cardNo;
    }

    /**
     * Setter for card number.
     * @param cardNo new card number of the card
     */
    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }
}
