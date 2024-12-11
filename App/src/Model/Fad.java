package Model;

import Controller.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fad implements Printable, Serializable{
    private int nummer;
    private String trætype;
    private double størrelse;
    private String tidligereIndhold;
    private boolean påfyldt = false;
    private Reol reol;

    public Reol getReol() {
        return reol;
    }

    public void setReol(Reol reol) {
        this.reol = reol;
    }

    public Fad(int nummer, String trætype, double størrelse, String tidligereIndhold) {
        this.nummer = nummer;
        this.trætype = trætype;
        this.størrelse = størrelse;
        this.tidligereIndhold = tidligereIndhold;
    }
    public void setPåfyldt(boolean bool) {
        påfyldt = bool;
    }

    public double getStørrelse() {
        return størrelse;
    }

    @Override
    public String toString() {
        return "Fad " + nummer;
    }

    public boolean isPåfyldt(){
        return påfyldt;
    }

    @Override
    public String print() {
        List<Lager> lagere = Controller.getLagere();
        int hylde = 0;
        for (Lager l : lagere) {
            for (Reol r : l.getReoler()) {
                for (int i = 0; i < r.getHylder().length; i++) {
                    if (r.getHylder()[i] == this) {
                        hylde = i;
                    }
                }
            }
        }
        return String.format("Fad Nummer: %d \n" +
                "Træsort: %s \n" +
                "Størrelse: %.2f L \n" +
                "Tidligere Indhold: %s \n" +
                "Påfyldt: %s \n" +
                "%s \n" +
                "Hylde: %s", nummer, trætype,størrelse,tidligereIndhold, isPåfyldt() ? "Ja" : "Nej", reol, hylde);
    }

    public int getNummer() {
        return nummer;
    }

    public String getTrætype() {
        return trætype;
    }

    public String getTidligereIndhold() {
        return tidligereIndhold;
    }
}
