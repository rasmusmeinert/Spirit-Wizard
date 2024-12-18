package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Tapning implements Serializable {
    private double mængde;
    private LocalDate dato;
    private Påfyldning påfyldning;

    public Tapning(double mængde, Påfyldning påfyldning) {
        this.mængde = mængde;
        this.påfyldning = påfyldning;
        this.dato = LocalDate.now();
    }

    public double getMængde() {
        return mængde;
    }

    public LocalDate getDato() {
        return dato;
    }

    public Påfyldning getPåfyldning() {
        return påfyldning;
    }

    public String print() {
        return "Tapning{" +
                "mængde=" + mængde +
                ", dato=" + dato +
                ", påfyldning=" + påfyldning +
                '}';
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f liter)", påfyldning.getFad().toString(), mængde);

    }
}
