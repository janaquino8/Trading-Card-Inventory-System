package src.model;

/*
Variant - Represents different card variants with its name, abbreviations, and unique value multipliers
 */
public class Variant {
    private String name;
    private double valueMultiplier;

    /*
    Method  - Variant (constructor)
    Method Comment : Default constructor creates a Normal variant with 1x multiplier
     */
    public Variant() {
        this.setName("Normal");
        this.setValueMultiplier(1);
    }

    /*
    Method       - Variant (constructor)
    int variant  - Numeric code representing variant type (2-4)
    Method Comment : Creates specific variant based on input code (2: Extended-art, 3: Full-art, 4: Alt-art)
     */
    public Variant(int variant) {
        switch (variant) {
            case 2:
                this.setName("Extended-art");
                this.setValueMultiplier(1.5);
                break;
            case 3:
                this.setName("Full-art");
                this.setValueMultiplier(2);
                break;
            case 4:
                this.setName("Alt-art");
                this.setValueMultiplier(3);
                break;
        }
    }

    /*
    Method       - getName
    return type  - String
    Method Comment : Returns the full name of the variant
     */
    public String getName() {
        return this.name;
    }

    /*
    Method       - setName
    String name  - New name to assign to the variant
    return type  - void
    Method Comment : Sets the variant's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
    Method       - getValueMultiplier
    return type  - double
    Method Comment : Returns the multiplier applied to card's base value
     */
    public double getValueMultiplier() {
        return this.valueMultiplier;
    }

    /*
    Method                  - setValueMultiplier
    double valueMultiplier  - New multiplier value to assign
    return type             - void
    Method Comment : Sets the value multiplier for this variant
     */
    public void setValueMultiplier(double valueMultiplier) {
        this.valueMultiplier = valueMultiplier;
    }
}