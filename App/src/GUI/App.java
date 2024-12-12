package GUI;

import Controller.Controller;
import Controller.Storage;
import GUI.*;
import Model.*;
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

        Application.launch(MainWindowGUI.class);
        ListStorage.saveStorage(storage);
    }

    public static void initStorage() {
        NewMake NM77P = Controller.createNewMake("NM77P", LocalDateTime.of(2024, 07, 14, 16, 50), LocalDateTime.of(2024, 07, 15, 8, 15), 1000, 60.5);
        NewMake NM76P = Controller.createNewMake("NM76P", LocalDateTime.of(2024, 06, 28, 12, 25), LocalDateTime.of(2024, 06, 28, 23, 15), 500, 63.2);
        NewMake NM79P = Controller.createNewMake("NM79P", LocalDateTime.of(2024, 10, 15, 8, 25), LocalDateTime.of(2024, 10, 15, 23, 15), 500, 61.5);
        ArrayList<MængdePåfyldt> mængderPåfyldt = new ArrayList<>(List.of(new MængdePåfyldt(NM77P, 200), new MængdePåfyldt(NM76P, 300)));
        Fad fad1 = Controller.createFad(1, "Egetræ", 500, "Sherry");
        Fad fad2 = Controller.createFad(3, "Birk", 500, "Bourbon");
        Påfyldning påfyldning1 = Controller.createPåfyldning("Martin", LocalDate.now(), fad1, mængderPåfyldt);

        //Test af getTapbarePåfyldninger
        NewMake NM12P = Controller.createNewMake("NM12P", LocalDateTime.of(2012, 10, 12, 12, 24), LocalDateTime.of(2012, 10, 12, 22, 30), 400, 64);
        NewMake NM22P = Controller.createNewMake("NM22P", LocalDateTime.of(2022, 10, 12, 12, 24), LocalDateTime.of(2022, 10, 12, 22, 30), 600, 64);
        Fad fad3 = Controller.createFad(2, "Egetræ", 500, "Sherry");
        Fad fad4 = Controller.createFad(4, "Egetræ", 250, "Bourbon");
        Fad fad5 = Controller.createFad(6,"Ahorn",500, "Sherry");
        ArrayList<MængdePåfyldt> mængderPåfyldt2 = new ArrayList<>(List.of(new MængdePåfyldt(NM22P, 500)));
        ArrayList<MængdePåfyldt> mængderPåfyldt3 = new ArrayList<>(List.of(new MængdePåfyldt(NM12P, 150), (new MængdePåfyldt(NM22P, 100))));
        Påfyldning påfyldning2 = Controller.createPåfyldning("Jens", LocalDate.of(2018, 10, 12), fad2, mængderPåfyldt2);
        Påfyldning påfyldning3 = Controller.createPåfyldning("Magnus", LocalDate.of(2021, 12, 1), fad4, mængderPåfyldt3);
        System.out.println("\n \n");

        //Test af WhiskyProdukt
        Tapning tapning1 = Controller.createTapning(20, påfyldning2);
        Tapning tapning2 = Controller.createTapning(20, påfyldning3);
        ArrayList<Tapning> tapninger1 = new ArrayList<>(List.of(tapning1));
        WhiskyProdukt whiskyProdukt = Controller.createWhiskyProdukt("Kaptajnens", 52, 0.5, "Kaptajnens Whisky, Yarr den er god!", 0.1, tapninger1);

        //Test af Lager
        Lager lager1 = Controller.createLager("Lars Lager", "Lars Tyndskids Mark 7, 8450 Hammel", 5, 2);
    }
}
