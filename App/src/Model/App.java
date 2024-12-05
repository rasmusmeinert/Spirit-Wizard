package Model;

import Controller.Controller;
import Controller.Storage;
import GUI.PåfyldningsGui;
import GUI.RegistrerLagerGUI;
import GUI.RegistrerProduktGUI;
import Storage.ListStorage;
import javafx.application.Application;

import java.time.LocalDate;
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

        Application.launch(RegistrerProduktGUI.class);
//        Application.launch(PåfyldningsGui.class);

        ListStorage.saveStorage(storage);
    }

    public static void initStorage() {
        NewMake NM77P = Controller.createNewMake("NM77P", LocalDateTime.of(2024,07,14,16,50), LocalDateTime.of(2024,07,15,8,15),1000,60.5);
        NewMake NM76P = Controller.createNewMake("NM76P", LocalDateTime.of(2024,06,28,12,25), LocalDateTime.of(2024,06,28,23,15),500,63.2);
        NewMake NM79P = Controller.createNewMake("NM79P", LocalDateTime.of(2024,10,15,8,25), LocalDateTime.of(2024,10,15,23,15),500,61.5);
        List<MængdePåfyldt> mængderPåfyldt = List.of(new MængdePåfyldt(NM77P,200),new MængdePåfyldt(NM76P,300));
        Fad fad1 = Controller.createFad(1, "Birk", 512, "Sherry");
        Fad fad2 = Controller.createFad(3, "Ask", 1025, "Bourbon");
        Påfyldning påfyldning1 = Controller.createPåfyldning("Martin",LocalDate.now(),fad1, mængderPåfyldt);

        //Test af getTapbarePåfyldninger
        NewMake NM12P = Controller.createNewMake("NM12P", LocalDateTime.of(2012, 10, 12, 12, 24), LocalDateTime.of(2012,10,12,22,30), 200, 64);
        NewMake NM22P = Controller.createNewMake("NM22P", LocalDateTime.of(2022, 10, 12, 12, 24), LocalDateTime.of(2022,10,12,22,30), 200, 64);
        Fad fad3 = Controller.createFad(2, "ask", 100, "Sherry");
        Fad fad4 = Controller.createFad(4, "ask", 100, "Bourbon");
        List<MængdePåfyldt> mængderPåfyldt2 = List.of(new MængdePåfyldt(NM12P,150));
        List<MængdePåfyldt> mængderPåfyldt3 = List.of(new MængdePåfyldt(NM12P,15), (new MængdePåfyldt(NM22P, 20)));
        Påfyldning påfyldning2 = Controller.createPåfyldning("Jens", LocalDate.of(2018, 10, 12), fad2, mængderPåfyldt2);
        Påfyldning påfyldning3 = Controller.createPåfyldning("Magnus", LocalDate.of(2021, 12, 1), fad3, mængderPåfyldt3);
        System.out.println(Controller.getTapbarePåfyldninger());
        System.out.println("\n \n");

        //Test af WhiskyProdukt
        Tapning tapning1 = Controller.createTapning(20, påfyldning2);
        Tapning tapning2 = Controller.createTapning(20, påfyldning3);
        List<Tapning> tapninger1 = List.of(tapning1);
        WhiskyProdukt whiskyProdukt = Controller.createWhiskyProdukt("Kaptajnens", 52, 0.5, "Kaptajnens pishamrende skipperskrås whisky!", 0.1, tapninger1);
        System.out.println(whiskyProdukt);
        System.out.println(whiskyProdukt.print());

        //Test af Lager
        Lager lager1 = Controller.createLager("Lars Lager", "Lars Tyndskids Mark 7, 8450 Hammel", 15, 7);
        System.out.println(lager1);
    }
}
