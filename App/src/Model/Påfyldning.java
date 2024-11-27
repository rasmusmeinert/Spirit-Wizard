package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Påfyldning {
    private String medarbejder;
    private LocalDateTime dato;
    private double samletMængde;
    private Fad fad;
    private ArrayList<MængdePåfyldt> mængdePåfyldt;

    public Påfyldning(String medarbejder, LocalDateTime dato, double samletMængde, Fad fad, ArrayList<MængdePåfyldt> mængdePåfyldt) {
        this.medarbejder = medarbejder;
        this.dato = dato;
        this.samletMængde = samletMængde;
        this.fad = fad;
        this.mængdePåfyldt = mængdePåfyldt;
    }
}
