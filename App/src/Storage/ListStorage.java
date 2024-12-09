package Storage;

import Controller.Storage;
import Model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListStorage implements Storage, Serializable {
    private final List<Fad> fade = new ArrayList<>();
    private final List<NewMake> newMakes = new ArrayList<>();
    private final List<Påfyldning> påfyldninger = new ArrayList<>();
    private final List<WhiskyProdukt> whiskyProdukter = new ArrayList<>();
    private final List<Lager> lagere = new ArrayList<>();

    public List<Fad> getFade() {
        return new ArrayList<>(fade);
    }

    public List<NewMake> getNewMakes() {
        return new ArrayList<>(newMakes);
    }

    public void storeFad(Fad fad) {
        fade.add(fad);
    }

    public void deleteFad(Fad fad) {
        fade.remove(fad);
    }

    public void storeNewMake(NewMake newMake) {
        newMakes.add(newMake);
    }

    public void deleteNewMake(NewMake newMake) {
        newMakes.remove(newMake);
    }

    public void storeWhiskyProdukt(WhiskyProdukt whiskyProdukt) {
        whiskyProdukter.add(whiskyProdukt);
    }

    public void deleteWhiskyProdukt(WhiskyProdukt whiskyProdukt) {
        whiskyProdukter.remove(whiskyProdukt);
    }


    public void storeLager(Lager lager) {
        lagere.add(lager);
    }

    public void deleteLager(Lager lager) {
        lagere.remove(lager);
    }

    @Override
    public List<Lager> getLagere() {
        return new ArrayList<>(lagere);
    }

    @Override
    public List<Påfyldning> getPåfyldninger() {
        return new ArrayList<>(påfyldninger);
    }

    @Override
    public void storePåfyldning(Påfyldning påfyldning) {
        påfyldninger.add(påfyldning);

    }

    @Override
    public void deletePåfyldning(Påfyldning påfyldning) {
        påfyldninger.remove(påfyldning);
    }

    //============================================================================
    public static ListStorage loadStorage() {
        String fileName = "App/src/storage.ser";
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objIn = new ObjectInputStream(fileIn)) {
            Object obj = objIn.readObject();
            ListStorage storage = (ListStorage) obj;
            System.out.println("Storage loaded from file " + fileName);
            return storage;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing storage");
            System.out.println(e);
            return null;
        }
    }

    public static void saveStorage(Storage storage) {
        String fileName = "App/src/storage.ser";
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {
            objOut.writeObject(storage);
            System.out.println("Storage saved in file " + fileName);

        } catch (IOException e) {
            System.out.println("Error serializing storage");
            System.out.println(e);
            throw new RuntimeException();
        }
    }
}
