package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Påfyldning implements Serializable {
    private String medarbejder;
    private LocalDate dato;
    private Fad fad;
    private List<MængdePåfyldt> mængdePåfyldt;

    public Påfyldning(String medarbejder, LocalDate dato, Fad fad, List<MængdePåfyldt> mængdePåfyldt) {
        this.medarbejder = medarbejder;
        this.dato = dato;
        this.fad = fad;
        this.mængdePåfyldt = mængdePåfyldt;
    }

    public double getSamletMængde(){
        double samletMængde = 0;
        for (MængdePåfyldt  mængde : mængdePåfyldt){
            samletMængde += mængde.getMængde();
        }
        return samletMængde;
    }

    @Override
    public String toString() {
        return "Påfyldning{" +
                "medarbejder='" + medarbejder + '\'' +
                ", dato=" + dato +
                ", \n fad=" + fad +
                ", \n mængdePåfyldt=" + mængdePåfyldt +
                '}';
    }
}
