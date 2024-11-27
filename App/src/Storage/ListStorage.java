package Storage;

import Controller.Storage;
import Model.Fad;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListStorage implements Storage, Serializable {
    private final List<Fad> fade = new ArrayList<>();

    @Override
    public List<Fad> getFade() {
        return new ArrayList<>(fade);
    }

    @Override
    public void storeFad(Fad fad) {
        fade.add(fad);
    }

    @Override
    public void deleteFad(Fad fad) {
        fade.remove(fad);
    }

    //============================================================================
    public static ListStorage loadStorage() {
        String fileName = "App/src/storage.ser";
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objIn = new ObjectInputStream(fileIn)) {
            Object obj = objIn.readObject();
            ListStorage storage = (ListStorage) obj;
            System.out.println("Storage loaded from file" + fileName);
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
