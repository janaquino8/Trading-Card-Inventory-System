package src.model;

public class Variant {
    private String name;
    private String abbreviation;
    private double valueMultiplier;

    public Variant() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public double getValueMultiplier() {
        return this.valueMultiplier;
    }

    public void setValueMultiplier(double valueMultiplier) {
        this.valueMultiplier = valueMultiplier;
    }
}
