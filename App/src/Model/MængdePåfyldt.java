package Model;

import java.io.Serializable;

public class MængdePåfyldt implements Serializable {
    private NewMake newMake;
    private double mængde;


    public MængdePåfyldt(NewMake newMake, double mængde) {
        this.newMake = newMake;
        this.mængde = mængde;
    }

    public NewMake getNewMake() {
        return newMake;
    }

    public double getMængde() {
        return mængde;
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f L)", newMake, mængde);
    }
}
