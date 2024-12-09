package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Lager implements Serializable, Printable {
    private String navn;
    private String lokation;
    private ArrayList<Reol> reoler;
    private int hylderPerReol;

    public Lager(String navn, String lokation, int reoler, int hylderPerReol) {
        this.navn = navn;
        this.lokation = lokation;
        this.reoler = new ArrayList<>();
        this.hylderPerReol = hylderPerReol;
        for (int i = 0; i < reoler; i++) {
            this.reoler.add(new Reol(i+1, hylderPerReol));
        }
    }


    public String getNavn() {
        return navn;
    }

    public String getLokation() {
        return lokation;
    }

    public ArrayList<Reol> getReoler() {
        return reoler;
    }

    @Override
    public String toString() {
        return navn;
    }

    @Override
    public String print() {
        return String.format("Navn: %s \n" +
                "Adresse: %s \n" +
                "Reoler: %d \n" +
                "Hylder per Reol: %d \n", navn,lokation,reoler.size(),hylderPerReol);
    }

    public int getHylderPerReol() {
        return hylderPerReol;
    }
}
