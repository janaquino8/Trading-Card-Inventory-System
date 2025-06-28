package src.model;

public class Variant {
    private String name;
    private String abbreviation;
    private double valueMultiplier;

    public Variant() {
        this.setName("Normal");
        this.setAbbreviation("N");
        this.setValueMultiplier(1);
    }

    public Variant(int variant) {
        switch (variant) {
            case 2:
                this.setName("Extended-art");
                this.setAbbreviation("EA");
                this.setValueMultiplier(1.5);
                break;
            case 3:
                this.setName("Full-art");
                this.setAbbreviation("FA");
                this.setValueMultiplier(2);
                break;
            case 4:
                this.setName("Alt-art");
                this.setAbbreviation("AA");
                this.setValueMultiplier(3);
                break;
        }
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
