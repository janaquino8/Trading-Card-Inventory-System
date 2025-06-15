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
        this.rarity = new Rarity();
        this.variant = new Variant();
        this.setCollectionCount(1);
    }

    // add increaseCollectionCount and decreaseCollectionCount

    public void calculateFinalValue() {
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

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public Variant getVariant() {
        return this.variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
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
