package src.model.card;

/*
Rarity - Represents card rarity levels with its names and abbreviations
 */
public class Rarity {
    private String name;

    /*
    Method      - Rarity (constructor)
    int rarity  - Numeric code representing rarity level (2-4)
    Method Comment : Creates specific rarity based on input code (2: Uncommon, 3: Rare, 4: Legendary)
     */
    public Rarity(int rarity) {
        switch (rarity) {
            case 1:
                this.setName("Common");
            case 2:
                this.setName("Uncommon");
                break;
            case 3:
                this.setName("Rare");
                break;
            case 4:
                this.setName("Legendary");
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
}