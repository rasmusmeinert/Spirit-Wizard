package Model;

import java.io.Serializable;

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
        return String.format("Fad Nummer: %d \n" +
                "Træsort: %s \n" +
                "Størrelse: %.2f L \n" +
                "Tidligere Indhold: %s \n" +
                "Påfyldt: %s", nummer, trætype,størrelse,tidligereIndhold, isPåfyldt() ? "Ja" : "Nej");
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
