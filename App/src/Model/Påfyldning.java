package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Påfyldning implements Serializable, Printable {
    private String medarbejder;
    private LocalDate dato;
    private Fad fad;
    private ArrayList<MængdePåfyldt> mængdePåfyldt;
    private double samletMængde;
    private double samletMængdePåfyldt;

    public Påfyldning(String medarbejder, LocalDate dato, Fad fad, ArrayList<MængdePåfyldt> mængdePåfyldt) {
        this.medarbejder = medarbejder;
        this.dato = dato;
        this.fad = fad;
        this.mængdePåfyldt = mængdePåfyldt;
        this.samletMængde = udregnSamletMængde();
        this.samletMængdePåfyldt = samletMængde;
    }

    public double getSamletMængde() {
        return samletMængde;
    }

    private double udregnSamletMængde(){
        double samletMængde = 0;
        for (MængdePåfyldt  mængde : mængdePåfyldt){
            samletMængde += mængde.getMængde();
        }
        return samletMængde;
    }
    public void reducerMængde(double mængdeTappet){
        samletMængde -= mængdeTappet;
        if (samletMængde <= 0) {
            fad.setPåfyldt(false);
        }
    }

    public LocalDate getDato() {
        return dato;
    }

    public Fad getFad() {
        return fad;
    }

    @Override
    public String toString() {
        return fad.toString();
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Lagret %s af %s \n" +
                "%.2f liter \n" +
                "Består af: \n", dato, medarbejder, samletMængde));
        for(MængdePåfyldt mp : mængdePåfyldt) {
            sb.append(String.format("%s (%.2f %%) \n", mp.getNewMake(), ((mp.getMængde() / this.samletMængdePåfyldt) * 100)));
        }
        return sb.toString();
    }
}
