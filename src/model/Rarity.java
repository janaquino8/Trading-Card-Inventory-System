package src.model;

/*
Rarity - Represents card rarity levels with its names and abbreviations
 */
public class Rarity {
    private String name;
    private String abbreviation;

    /*
    Method  - Rarity (constructor)
    Method Comment : Default constructor creates a Common rarity
     */
    public Rarity() {
        this.setName("Common");
        this.setAbbreviation("COM");
    }

    /*
    Method      - Rarity (constructor)
    int rarity  - Numeric code representing rarity level (2-4)
    Method Comment : Creates specific rarity based on input code (2: Uncommon, 3: Rare, 4: Legendary)
     */
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

    /*
    Method       - getName
    return type  - String
    Method Comment : Returns the full name of the rarity level
     */
    public String getName() {
        return this.name;
    }

    /*
    Method       - setName
    String name  - New name to assign to the rarity
    return type  - void
    Method Comment : Sets the rarity's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
    Method       - getAbbreviation
    return type  - String
    Method Comment : Returns the shortened version of the rarity name
     */
    public String getAbbreviation() {
        return this.abbreviation;
    }

    /*
    Method               - setAbbreviation
    String abbreviation  - New abbreviation to assign
    return type          - void
    Method Comment : Sets the rarity's abbreviation
     */
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
