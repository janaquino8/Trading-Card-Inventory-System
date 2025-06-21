package src.model;

public class Rarity {
    private String name;
    private String abbreviation;

    public Rarity() {

    }

    public Rarity(int rarity) {
        this();
        switch (rarity) {
            case 1:
                this.setName("Common");
                this.setAbbreviation("COM");
            case 2:
                this.setName("Uncommon");
                this.setAbbreviation("UCM");
            case 3:
                this.setName("Rare");
                this.setAbbreviation("RAR");
            case 4:
                this.setName("Legendary");
                this.setAbbreviation("LEG");
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
}
