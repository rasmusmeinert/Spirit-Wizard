package Controller;

import Model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public abstract class Controller {
    private static Storage storage;

    public static void setStorage(Storage storage) {
        Controller.storage = storage;
    }

    //==============================  Fad  ===============================================

    /**
     * Create a "Fad"
     * pre: nummer and størrelse > 0
     */
    public static Fad createFad(int nummer, String trætype, double størrelse, String tidligereIndhold) {
        Fad fad = new Fad(nummer, trætype, størrelse, tidligereIndhold);
        storage.storeFad(fad);
        return fad;
    }

    /** Delete a "Fad"
     */
    public static void deleteFad(Fad fad) {
        storage.deleteFad(fad);
    }

    public static List<Fad> getFade() {
        return storage.getFade();
    }

    // ======================= New Make ========================================

    /** Create a "NewMake"
     * params not nullable
     */
    public static NewMake createNewMake(String navn, LocalDateTime startDato, LocalDateTime slutDato, double startMængde, double alkoholPct) {
        NewMake newMake = new NewMake(navn, startDato, slutDato, startMængde, alkoholPct);
        storage.storeNewMake(newMake);
        return newMake;
    }


    /** Delete a NewMake
     */
    public static void deleteNewMake(NewMake newMake) {
        storage.deleteNewMake(newMake);
    }

    public static List<NewMake> getNewMakes() {
        return storage.getNewMakes();
    }

    //================================== Påfyldning ===========================================================


    public static Påfyldning createPåfyldning(String medarbejder, LocalDate dato, Fad fad, List<MængdePåfyldt> mængdePåfyldt){
        Påfyldning påfyldning = new Påfyldning(medarbejder,dato, fad, mængdePåfyldt);
        fad.setPåfyldt(true);
        storage.storePåfyldning(påfyldning);
        return påfyldning;
    }

    public static void deletePåfyldning(Påfyldning påfyldning){
        påfyldning.getFad().setPåfyldt(false);
        storage.deletePåfyldning(påfyldning);
    }

    public static List<Påfyldning> getPåfyldninger(){
       return storage.getPåfyldninger();
    }

    //========================================================================================================

    public static MængdePåfyldt createMængdePåfyldt(NewMake newMake, double mængde){
        return new MængdePåfyldt(newMake, mængde);
    }

    //=============================Tapninger===========================================================

    /** Returner Påfyldninger som har lagret mindst tre år
     */
    public static ArrayList<Påfyldning> getTapbarePåfyldninger(){
        ArrayList<Påfyldning> tapbarePåfyldninger = new ArrayList<>();
        for (Påfyldning p : storage.getPåfyldninger()) {
            //Tjekker om påfyldningens dato er mere end 3 år gammel
            if (p.getDato().isBefore(LocalDate.now().minusYears(3))) {
                tapbarePåfyldninger.add(p);
            }
        }
        return tapbarePåfyldninger;
    }

    public static Tapning createTapning(double mængde, Påfyldning påfyldning) {
        Tapning tapning = new Tapning(mængde, påfyldning);
        return tapning;
    }

    //================================== WhiskyProdukt =====================================================
    public static WhiskyProdukt createWhiskyProdukt(String navn, double alkoholProcent, double flaskeStørrelse, String beskrivelse, double mængdeVandTilFortynding, List<Tapning> tapninger) {
        WhiskyProdukt whiskyProdukt = new WhiskyProdukt(navn, alkoholProcent, flaskeStørrelse, beskrivelse, mængdeVandTilFortynding, tapninger);

        //Her reduceres mængden af de påfylgninger som er brugt, og fade markeres som tomme, hvis de bliver tømt.
        for (Tapning t : tapninger) {
            t.getPåfyldning().reducerMængde(t.getMængde());
        }

        storage.storeWhiskyProdukt(whiskyProdukt);
        return whiskyProdukt;
    }
}
