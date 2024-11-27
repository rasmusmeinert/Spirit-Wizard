package Model;

import Controller.Controller;
import Controller.Storage;
import Storage.ListStorage;

public class App {
    public static void main(String[] args) {
        Storage storage = ListStorage.loadStorage();
        if (storage == null) {
            storage = new ListStorage();
            System.out.println("Empty ListStorage created");
            Controller.setStorage(storage);
        } else {
            Controller.setStorage(storage);
        }

        ListStorage.saveStorage(storage);
    }

    public static void initStorage() {
        Controller.createFad(1, "Birk", 512, "Sherry");
        Controller.createFad(3, "Ask", 1025, "Bourbon");
    }
}
