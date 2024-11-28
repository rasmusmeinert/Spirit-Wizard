package Model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class NewMake implements Serializable {
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
        return "NewMake{" +
                "navn='" + navn + '\'' +
                ", startDato=" + startDato +
                ", slutDato=" + slutDato +
                ", startMængde=" + startMængde +
                ", aktuelMængde=" + aktuelMængde +
                ", alkoholPct=" + alkoholPct +
                '}';
    }
}
