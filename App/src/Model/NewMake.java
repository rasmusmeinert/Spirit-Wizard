package Model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class NewMake implements Printable, Serializable {
    private String navn;
    private LocalDateTime startDato;
    private LocalDateTime slutDato;
    private double startMængde;
    private double aktuelMængde;
    private double alkoholPct;

    public NewMake(String navn, LocalDateTime startDato, LocalDateTime slutDato, double startMængde, double alkoholPct) {
        this.navn = navn;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.startMængde = startMængde;
        aktuelMængde = startMængde;
        this.alkoholPct = alkoholPct;
    }

    @Override
    public String toString() {
        return navn;
    }

    @Override
    public String print() {
        return String.format("Navn: %s \n" +
                "Destillationsperiode : %s -> %s \n" +
                "Mængde Produceret: %.2f L \n" +
                "Aktuel Mængde: %.2f L \n" +
                "Alkohol Procent: %.2f", navn, startDato, slutDato, startMængde, aktuelMængde, alkoholPct);

    }
}
