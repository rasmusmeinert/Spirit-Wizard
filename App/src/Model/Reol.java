package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Reol implements Serializable {
    private int nummer;
    private Fad[] hylder;

    public Reol(int nummer, int antalHylder) {
        this.nummer = nummer;
        this.hylder = new Fad[antalHylder + 1];
    }

    public int getNummer() {
        return nummer;
    }

    public Fad[] getHylder() {
        return hylder;
    }

    public ArrayList<Fad> getFade() {
        ArrayList<Fad> fade = new ArrayList<>();
        for (int i = 0; i < hylder.length; i++) {
            if (!(hylder[i] == null)) {
                fade.add(hylder[i]);
            }
        }
        return fade;
    }

    public boolean isEmpty() {
        boolean emptyReol = true;
        for (int i = 0; i < hylder.length; i++) {
            if (hylder[i] == null) {
                emptyReol = false;
            }
        }
        return emptyReol;
    }

    public boolean isFull() {
        for (int i = 0; i < hylder.length; i++) {
            if (hylder[i] == null) {
                return false;
            }
        }
        return true;
    }

    public void addFad(Fad fad, int hylde) {
        hylder[hylde] = fad;
    }

    public boolean removeFad(Fad fad) {
        for (int i = 0; i < hylder.length; i++) {
            if (hylder[i] == fad) {
                hylder[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Reol: " + nummer;
//                ", hylder=" + hylder;
    }
}
