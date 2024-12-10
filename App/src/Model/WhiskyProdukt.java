package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class WhiskyProdukt implements Serializable, Printable {
    private String navn;
    private double alder;
    private String type;
    private double alkoholProcent;
    private double flaskeStørrelse;
    private String beskrivelse; //Kan være null
    private int antalFlasker;
    private double mængdeVandTilFortynding;
    private ArrayList<Tapning> tapninger;

    public WhiskyProdukt(String navn, double alkoholProcent, double flaskeStørrelse, String beskrivelse, double mængdeVandTilFortynding, ArrayList<Tapning> tapninger) {
        this.navn = navn;
        this.alkoholProcent = alkoholProcent;
        this.flaskeStørrelse = flaskeStørrelse;
        this.beskrivelse = beskrivelse;
        this.mængdeVandTilFortynding = mængdeVandTilFortynding;
        this.tapninger = tapninger;
        this.alder = udregnAlder();
        this.antalFlasker = udregnAntalFlasker();
        this.type = udregnType();
    }
    private double udregnAlder() {
        Tapning yngsteTapning = null;
        for (Tapning t : tapninger) {

            if (yngsteTapning == null) {
                yngsteTapning = t;
            }
            else if (t.getPåfyldning().getDato().isAfter(yngsteTapning.getPåfyldning().getDato())) {
                yngsteTapning = t;
            }
        }
        double udregnetAlder = ChronoUnit.YEARS.between(yngsteTapning.getPåfyldning().getDato(), LocalDate.now());
        return udregnetAlder;
    }
    private int udregnAntalFlasker() {
        double samletMængdeVæskeFraTapninger = 0;
        samletMængdeVæskeFraTapninger += mængdeVandTilFortynding;
        for (Tapning t : tapninger) {
            samletMængdeVæskeFraTapninger += t.getMængde();
        }
        return (int)(samletMængdeVæskeFraTapninger / flaskeStørrelse);
    }
    private String udregnType() {
        //(Metoden skal justeres hvis muligheden for blended implementeres)

        StringBuilder sb = new StringBuilder();
        if (tapninger.size() > 1) {
            sb.append("Single malt");
        }
        else {
            sb.append("Single cask");
        }
        if (mængdeVandTilFortynding == 0) {
            sb.append(", cask strength");
        }
        return sb.toString();
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }


    public String getNavn() {
        return navn;
    }

    public double getAlder() {
        return alder;
    }

    public String getType() {
        return type;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public double getFlaskeStørrelse() {
        return flaskeStørrelse;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public int getAntalFlasker() {
        return antalFlasker;
    }

    public double getMængdeVandTilFortynding() {
        return mængdeVandTilFortynding;
    }

    public ArrayList<Tapning> getTapninger() {
        return tapninger;
    }

    @Override
    public String toString() {
        return navn;
    }

    @Override
    public String print() {
        return String.format("%s \n" +
                "%d års %s whisky\n" +
                "%.2f cl, %d %%", navn, (int)alder, type, flaskeStørrelse, (int)alkoholProcent);
    }
}
