package Model;

import java.io.Serializable;
import java.util.HashMap;

public class Reol implements Serializable {
    private int nummer;
    private int antalHylder;
    private HashMap<Integer, Fad> hylder = new HashMap<Integer, Fad>();

    public Reol(int nummer, int antalHylder) {
        this.nummer = nummer;
        this.antalHylder = antalHylder;
    }
    
    @Override
    public String toString() {
        return "Reol{" +
                "nummer=" + nummer +
                ", antalHylder=" + antalHylder;
    }

    public int getNummer() {
        return nummer;
    }

    public int getAntalHylder() {
        return antalHylder;
    }

    public HashMap<Integer, Fad> getHylder() {
        return hylder;
    }
}
