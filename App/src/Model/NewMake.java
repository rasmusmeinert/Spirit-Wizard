package Model;

import java.time.LocalDateTime;

public class NewMake {
    private String navn;
    private LocalDateTime startDato;
    private LocalDateTime slutDato;
    private double startMængde;
    private double aktuelMængde;
    private double alkoholPct;

    public NewMake(String navn, LocalDateTime startDato, LocalDateTime slutDato, double startMængde, double aktuelMængde, double alkoholPct) {
        this.navn = navn;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.startMængde = startMængde;
        this.aktuelMængde = aktuelMængde;
        this.alkoholPct = alkoholPct;
    }
}
