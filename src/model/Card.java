package src.model;

public class Card {
    private String name;
    private Rarity rarity;
    private Variant variant;
    private double baseValue;
    private double finalValue;
    private int collectionCount;
    private int cardNo;

    public Card() {
        this.setCollectionCount(1);
    }

    public Card(String name, int rarity, double baseValue, int cardNo) {
        this();
        this.setName(name);
        this.rarity = new Rarity(rarity);
        this.variant = new Variant();
        this.setBaseValue(baseValue);
        this.setCardNo(cardNo);
        this.setFinalValue(baseValue);
    }

    public Card(String name, int rarity, int variant, double baseValue, int cardNo) {
        this(name, rarity, baseValue, cardNo);
        if (variant != 1) {
            this.variant = new Variant(variant);
            this.calculateFinalValue();
        }
    }

    public void incrementCollectionCount() {
        this.setCollectionCount(this.getCollectionCount() + 1);
    }

    public void decrementCollectionCount() {
        this.setCollectionCount(this.getCollectionCount() - 1);
    }

    private void calculateFinalValue() {
        this.setFinalValue(this.getBaseValue() * this.getVariant().getValueMultiplier());
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rarity getRarity() {
        return this.rarity;
    }

    public Variant getVariant() {
        return this.variant;
    }

    public double getBaseValue() {
        return this.baseValue;
    }

    public void setBaseValue(double baseValue) {
        this.baseValue = baseValue;
    }

    public double getFinalValue() {
        return this.finalValue;
    }

    public void setFinalValue(double finalValue) {
        this.finalValue = finalValue;
    }

    public int getCollectionCount() {
        return this.collectionCount;
    }

    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }

    public int getCardNo() {
        return this.cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }
}
