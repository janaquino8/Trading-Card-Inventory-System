package src.model;

public class Rarity {
    private String name;

    public Rarity() {
        this.setName("Common");
    }

    public Rarity(int rarity) {
        this();
        switch (rarity) {
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
