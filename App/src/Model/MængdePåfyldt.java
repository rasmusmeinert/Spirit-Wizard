package Model;

import java.io.Serializable;

public class MængdePåfyldt implements Serializable {
    private NewMake newMake;
    private int mængde;


    public MængdePåfyldt(NewMake newMake, int mængde) {
        this.newMake = newMake;
        this.mængde = mængde;
    }

    public NewMake getNewMake() {
        return newMake;
    }

    public int getMængde() {
        return mængde;
    }

    @Override
    public String toString() {
        return "MængdePåfyldt{" +
                "\n newMake=" + newMake +
                ", mængde=" + mængde +
                '}';
    }
}
