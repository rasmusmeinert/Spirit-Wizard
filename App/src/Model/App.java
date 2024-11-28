package Model;

import Controller.Controller;
import Controller.Storage;
import Storage.ListStorage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Storage storage = ListStorage.loadStorage();
        if (storage == null) {
            storage = new ListStorage();
            Controller.setStorage(storage);
            initStorage();
            System.out.println("Empty ListStorage created");
        } else {
            Controller.setStorage(storage);
        }

        System.out.println(Controller.getPåfyldninger());


        ListStorage.saveStorage(storage);
    }

    public static void initStorage() {
        NewMake NM77P = Controller.createNewMake("NM77P", LocalDateTime.of(2024,07,14,16,50), LocalDateTime.of(2024,07,15,8,15),1000,60.5);
        NewMake NM76P = Controller.createNewMake("NM76P", LocalDateTime.of(2024,06,28,12,25), LocalDateTime.of(2024,06,28,23,15),500,63.2);
        List<MængdePåfyldt> mængderPåfyldt = List.of(new MængdePåfyldt(NM77P,200),new MængdePåfyldt(NM76P,300));
        Fad fad1 = Controller.createFad(1, "Birk", 512, "Sherry");
        Fad fad2 = Controller.createFad(3, "Ask", 1025, "Bourbon");
        Påfyldning påfyldning1 = Controller.createPåfyldning("Martin",LocalDateTime.now(),fad1, mængderPåfyldt);

    }
}
