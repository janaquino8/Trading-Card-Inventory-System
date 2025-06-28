package src.model;

public class Variant {
    private String name;
    private double valueMultiplier;

    public Variant() {
        this.setName("Normal");
        this.setValueMultiplier(1);
    }

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValueMultiplier() {
        return this.valueMultiplier;
    }

    public void setValueMultiplier(double valueMultiplier) {
        this.valueMultiplier = valueMultiplier;
    }
}
