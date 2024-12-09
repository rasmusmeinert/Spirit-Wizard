package Controller;

import GUI.Components.Observer;
import Model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class Controller {
    private static final List<Observer> observers = new ArrayList<>();
    private static Storage storage;


    public static void addObserver(Observer observer) {
        observers.add(observer);
    }

    public static void notifyObservers() {
        observers.forEach(observer -> observer.update(""));
    }

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
        notifyObservers();
        return fad;
    }

    /**
     * Delete a "Fad"
     */
    public static void deleteFad(Fad fad) {
        storage.deleteFad(fad);
        notifyObservers();
    }

    public static List<Fad> getFade() {
        return storage.getFade();
    }

    //Returnere tomme fade
    public static List<Fad> getTommeFade() {
        List<Fad> fade = getFade();
        List<Fad> tommeFade = new ArrayList<>();
        for (Fad fad : fade) {
            if (!fad.isPåfyldt()) {
                tommeFade.add(fad);
            }
        }
        return tommeFade;
    }


    /**
     * Searches for a "fad" matching any of the search input
     * Pre: Search inputs that dont matter should be null!
     *
     * @param nummer
     * @param træType
     * @param størrelse
     * @param tidligereIndhold
     * @param isPåfyldt
     * @return
     */
    public static List<Fad> søgFad(Integer nummer, String træType, Double størrelse, String tidligereIndhold, boolean isPåfyldt) {
        List<Fad> søgeResultat = storage.getFade();
        Iterator<Fad> iter = søgeResultat.iterator();
        if (nummer != null) {
            while (iter.hasNext()) {
                Fad fad = iter.next();
                if (fad.getNummer() != nummer) {
                    iter.remove();
                }
            }
        }

        iter = søgeResultat.iterator();

        if (træType != null) {
            while (iter.hasNext()) {
                Fad fad = iter.next();
                if (!fad.getTrætype().equalsIgnoreCase(træType)) {
                    iter.remove();
                }
            }
        }

        iter = søgeResultat.iterator();

        if (størrelse != null) {
            while (iter.hasNext()) {
                Fad fad = iter.next();
                if (fad.getStørrelse() != størrelse) {
                    iter.remove();
                }
            }

        }

        iter = søgeResultat.iterator();

        if (tidligereIndhold != null) {
            while (iter.hasNext()) {
                Fad fad = iter.next();
                if (!fad.getTidligereIndhold().equalsIgnoreCase(tidligereIndhold)) {
                    iter.remove();
                }
            }

        }

        iter = søgeResultat.iterator();
        while (iter.hasNext()) {
            Fad fad = iter.next();
            if (fad.isPåfyldt() != isPåfyldt) {
                iter.remove();
            }
        }

        return søgeResultat;
    }

    // ======================= New Make ========================================

    /**
     * Create a "NewMake"
     * params not nullable
     */
    public static NewMake createNewMake(String navn, LocalDateTime startDato, LocalDateTime slutDato, double startMængde, double alkoholPct) {
        NewMake newMake = new NewMake(navn, startDato, slutDato, startMængde, alkoholPct);
        storage.storeNewMake(newMake);
        notifyObservers();
        return newMake;
    }


    /**
     * Delete a NewMake
     */
    public static void deleteNewMake(NewMake newMake) {
        storage.deleteNewMake(newMake);
        notifyObservers();
    }

    public static List<NewMake> getNewMakes() {
        return storage.getNewMakes();
    }


    //Returns NewMakes that are not used up
    public static List<NewMake> getAktuelleNewMakes() {
        List<NewMake> newMakes = storage.getNewMakes();
        List<NewMake> aktuelleNewMakes = new ArrayList<>();
        for (NewMake newMake : newMakes) {
            if (newMake.getAktuelMængde() > 0) {
                aktuelleNewMakes.add(newMake);
            }
        }
        return aktuelleNewMakes;
    }

    //================================== Påfyldning ===========================================================


    public static Påfyldning createPåfyldning(String medarbejder, LocalDate dato, Fad fad, ArrayList<MængdePåfyldt> mængdePåfyldt) {
        Påfyldning påfyldning = new Påfyldning(medarbejder, dato, fad, mængdePåfyldt);
        fad.setPåfyldt(true);
        for (MængdePåfyldt mp : mængdePåfyldt) {
            mp.getNewMake().reducerMængde(mp.getMængde());
        }
        storage.storePåfyldning(påfyldning);
        notifyObservers();
        return påfyldning;
    }

    public static void deletePåfyldning(Påfyldning påfyldning) {
        påfyldning.getFad().setPåfyldt(false);
        storage.deletePåfyldning(påfyldning);
        notifyObservers();
    }

    public static List<Påfyldning> getPåfyldninger() {
        return storage.getPåfyldninger();
    }

    //========================================================================================================

    public static MængdePåfyldt createMængdePåfyldt(NewMake newMake, double mængde) {
        return new MængdePåfyldt(newMake, mængde);
    }

    //=============================Tapninger===========================================================

    /**
     * Returner Påfyldninger som har lagret mindst tre år
     */
    public static ArrayList<Påfyldning> getTapbarePåfyldninger() {
        ArrayList<Påfyldning> tapbarePåfyldninger = new ArrayList<>();
        for (Påfyldning p : storage.getPåfyldninger()) {
            //Tjekker om påfyldningens dato er mere end 3 år gammel
            if (p.getDato().isBefore(LocalDate.now().minusYears(3)) && p.getSamletMængde() > 0) {
                tapbarePåfyldninger.add(p);
            }
        }
        return tapbarePåfyldninger;
    }

    public static Tapning createTapning(double mængde, Påfyldning påfyldning) {
        Tapning tapning = new Tapning(mængde, påfyldning);
        notifyObservers();
        return tapning;
    }

    //================================== WhiskyProdukt =====================================================
    public static WhiskyProdukt createWhiskyProdukt(String navn, double alkoholProcent, double flaskeStørrelse, String beskrivelse, double mængdeVandTilFortynding, ArrayList<Tapning> tapninger) {
        WhiskyProdukt whiskyProdukt = new WhiskyProdukt(navn, alkoholProcent, flaskeStørrelse, beskrivelse, mængdeVandTilFortynding, tapninger);

        //Her reduceres mængden af de påfylgninger som er brugt, og fade markeres som tomme, hvis de bliver tømt.
        for (Tapning t : tapninger) {
            t.getPåfyldning().reducerMængde(t.getMængde());
        }

        storage.storeWhiskyProdukt(whiskyProdukt);
        notifyObservers();
        return whiskyProdukt;
    }

    //====================================== Lager ========================================================

    public static Lager createLager(String navn, String lokation, int reoler, int hylderPerReol) {
        Lager lager = new Lager(navn, lokation, reoler, hylderPerReol);
        storage.storeLager(lager);
        notifyObservers();
        return lager;
    }

    public static void deleteLager(Lager lager) {
        storage.deleteLager(lager);
        notifyObservers();
    }

    public static List<Lager> getLagere() {
        return storage.getLagere();
    }
}
