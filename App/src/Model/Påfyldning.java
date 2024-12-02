package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Påfyldning implements Serializable {
    private String medarbejder;
    private LocalDate dato;
    private Fad fad;
    private List<MængdePåfyldt> mængdePåfyldt;
    private double samletMængde;

    public Påfyldning(String medarbejder, LocalDate dato, Fad fad, List<MængdePåfyldt> mængdePåfyldt) {
        this.medarbejder = medarbejder;
        this.dato = dato;
        this.fad = fad;
        this.mængdePåfyldt = mængdePåfyldt;
        this.samletMængde = udregnSamletMængde();
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
        return "Påfyldning{" +
                "medarbejder='" + medarbejder + '\'' +
                ", dato=" + dato +
                ",\n fad=" + fad +
                ",\n mængdePåfyldt=" + mængdePåfyldt +
                '}';
    }
}
