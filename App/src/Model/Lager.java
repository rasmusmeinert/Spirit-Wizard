package Model;

import javax.swing.event.HyperlinkListener;
import java.io.Serializable;
import java.util.ArrayList;

public class Lager implements Serializable, Printable {
    private String navn;
    private String lokation;
    private ArrayList<Reol> reoler;

    public Lager(String navn, String lokation, int reoler, int hylderPerReol) {
        this.navn = navn;
        this.lokation = lokation;
        this.reoler = new ArrayList<>();
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

    public int getAntalHylder() {
        int antalHylder = 0;
        for (Reol reol : reoler) {
            antalHylder += reol.getHylder().length;
        }
        return antalHylder;
    }

    public ArrayList<Fad> getFade() {
        ArrayList<Fad> alleFadePåLageret = new ArrayList<>();
        for (Reol reol : reoler) {
            if (!reol.isEmpty()) {
                alleFadePåLageret.addAll(reol.getFade());
            }
        }
        return alleFadePåLageret;
    }

    public Reol getReol(int reolNummer) {
        return reoler.get(reolNummer - 1);
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
                "Hylder: %d \n" +
                "Antal fade %d", navn, lokation, reoler.size(), getAntalHylder(), getFade().size());
    }
}
