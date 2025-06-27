package src.model;

public class Rarity {
    private String name;
    private String abbreviation;

    public Rarity() {
        this.setName("Common");
        this.setAbbreviation("COM");
    }

    public Rarity(int rarity) {
        this();
        switch (rarity) {
            case 2:
                this.setName("Uncommon");
                this.setAbbreviation("UCM");
                break;
            case 3:
                this.setName("Rare");
                this.setAbbreviation("RAR");
                break;
            case 4:
                this.setName("Legendary");
                this.setAbbreviation("LEG");
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
}
