package Controller;

import Model.Fad;
import Model.MængdePåfyldt;
import Model.NewMake;
import Model.Påfyldning;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    //Returnere tomme fade
    public static List<Fad> getTommeFade(){
        List<Fad> fade = storage.getFade();
        List<Fad> tommeFade = new ArrayList<>();
        for (Fad fad : fade){
            if (!fad.isPåfyldt()){
                tommeFade.add(fad);
            }
        }
        return tommeFade;
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


    //Returns NewMakes that are not used up
    public static List<NewMake> getAktuelleNewMakes(){
        List<NewMake> newMakes = storage.getNewMakes();
        List<NewMake> aktuelleNewMakes = new ArrayList<>();
        for (NewMake newMake : newMakes){
            if (newMake.getAktuelMængde() != 0){
                aktuelleNewMakes.add(newMake);
            }
        }
        return aktuelleNewMakes;
    }

    //================================== Påfyldning ===========================================================


    public static Påfyldning createPåfyldning(String medarbejder, LocalDate dato, Fad fad, List<MængdePåfyldt> mængdePåfyldt){
        Påfyldning påfyldning = new Påfyldning(medarbejder,dato, fad, mængdePåfyldt);
        storage.storePåfyldning(påfyldning);
        return påfyldning;
    }

    public static void deletePåfyldning(Påfyldning påfyldning){
        storage.deletePåfyldning(påfyldning);
    }

    public static List<Påfyldning> getPåfyldninger(){
       return storage.getPåfyldninger();
    }

    //========================================================================================================

    public static MængdePåfyldt createMængdePåfyldt(NewMake newMake, double mængde){
        return new MængdePåfyldt(newMake, mængde);
    }



}
