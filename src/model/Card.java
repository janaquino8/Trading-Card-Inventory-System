package src.model;

/**
 * Card
 * Represents a card
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
     * Card
     * Constructor to construct a Card object
     */
    public Card() {
        this.setCollectionCount(1);
    }

    /**
     * Card
     * @param name name of the card
     * @param rarity integer representation of the rarity of the card
     * @param baseValue base value of the card
     * @param cardNo card number of the card
     * Constructor to construct a Card object that is common/uncommon in rarity
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
     * Card
     * @param name name of the card
     * @param rarity integer representation of the rarity of the card
     * @param variant integer representation of the variant of the card
     * @param baseValue base value of the card
     * @param cardNo card number of the card
     * Constructor to construct a Card object that is rare/legendary in rarity
     */
    public Card(String name, int rarity, int variant, double baseValue, int cardNo) {
        this(name, rarity, baseValue, cardNo);
        if (variant != 1) {
            this.variant = new Variant(variant);
            this.calculateFinalValue();
        }
    }

    /**
     * incrementCollectionCount
     * Increments the collection count of the card
     */
    public void incrementCollectionCount() {
        this.setCollectionCount(this.getCollectionCount() + 1);
    }

    /**
     * decrementCollectionCount
     * Decrements the collection count of the card
     */
    public void decrementCollectionCount() {
        this.setCollectionCount(this.getCollectionCount() - 1);
    }

    /**
     * calculateFinalValue
     * Calculates the final value of the card using the value multiplier set by the variant
     */
    private void calculateFinalValue() {
        this.setFinalValue(this.getBaseValue() * this.getVariant().getValueMultiplier());
    }

    /**
     * getName
     * @return name of the card
     * Getter for name
     */
    public String getName() {
        return this.name;
    }

    /**
     * setName
     * @param name new name of the card
     * Setter for name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getRarity
     * @return Rarity of the card
     * Getter for Rarity
     */
    public Rarity getRarity() {
        return this.rarity;
    }

    /**
     * getVariant
     * @return Variant of the card
     * Getter for Variant
     */
    public Variant getVariant() {
        return this.variant;
    }

    /**
     * getBaseValue
     * @return base value of the card
     * Getter for base value
     */
    public double getBaseValue() {
        return this.baseValue;
    }

    /**
     * setBaseValue
     * @param baseValue new base value of the card
     * Setter for base value
     */
    public void setBaseValue(double baseValue) {
        this.baseValue = baseValue;
    }

    /**
     * getFinalValue
     * @return final value of the card
     * Getter for final value
     */
    public double getFinalValue() {
        return this.finalValue;
    }

    /**
     * setFinalValue
     * @param finalValue new final value of the card
     * Setter for final value
     */
    public void setFinalValue(double finalValue) {
        this.finalValue = finalValue;
    }

    /**
     * getCollectionCount
     * @return collection count of the card
     * Getter for collection count
     */
    public int getCollectionCount() {
        return this.collectionCount;
    }

    /**
     * setCollectionCount
     * @param collectionCount new collection count of the card
     * Setter for collection count
     */
    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }

    /**
     * getCardNo
     * @return card number of the card
     * Getter for card number
     */
    public int getCardNo() {
        return this.cardNo;
    }

    /**
     * setCardNo
     * @param cardNo new card number of the card
     * Setter for card number
     */
    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }
}
