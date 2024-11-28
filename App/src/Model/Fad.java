package Model;

import java.io.Serializable;

public class Fad implements Serializable{
    private int nummer;
    private String trætype;
    private double størrelse;
    private String tidligereIndhold;
    private boolean påfyldt = false;

    public Fad(int nummer, String trætype, double størrelse, String tidligereIndhold) {
        this.nummer = nummer;
        this.trætype = trætype;
        this.størrelse = størrelse;
        this.tidligereIndhold = tidligereIndhold;
    }

    @Override
    public String toString() {
        return "Fad{" +
                "nummer=" + nummer +
                ", trætype='" + trætype + '\'' +
                ", størrelse=" + størrelse +
                ", tidligereIndhold='" + tidligereIndhold + '\'' +
                ", påfyldt=" + påfyldt +
                '}';
    }
}
