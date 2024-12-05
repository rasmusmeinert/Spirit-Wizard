package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Lager implements Serializable {
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

    @Override
    public String toString() {
        return "Lager{" +
                "navn='" + navn + '\'' +
                ", lokation='" + lokation + '\'' +
                ", reoler=" + reoler +
                '}';
    }
}
