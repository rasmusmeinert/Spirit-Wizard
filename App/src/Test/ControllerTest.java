package Test;

import Controller.Controller;
import Model.Fad;
import Model.MængdePåfyldt;
import Model.NewMake;
import Model.Påfyldning;
import Storage.ListStorage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {


    @Test
    void søgFadTC1() {
        Controller.setStorage(new ListStorage());
        Fad fad1 = Controller.createFad(1, "Birk", 500, "Sherry");
        Fad fad2 = Controller.createFad(2, "Birk", 500, "Bourbon");
        Fad fad3 = Controller.createFad(3, "Eg", 1000, "Sherry");
        fad3.setPåfyldt(true);
        Fad fad4 = Controller.createFad(4, "Eg", 1000, "Bourbon");
        fad4.setPåfyldt(true);
        Fad fad5 = Controller.createFad(5, "Birk", 500, "Sherry");
        fad5.setPåfyldt(true);

        List<Fad> fade = List.of(fad1);

        assertEquals(Controller.søgFad(null, "birk", 500., "sherry", false), fade);
    }

    @Test
    void søgFadTC2() {
        Controller.setStorage(new ListStorage());
        Fad fad1 = Controller.createFad(1, "Birk", 500, "Sherry");
        Fad fad2 = Controller.createFad(2, "Birk", 500, "Bourbon");
        Fad fad3 = Controller.createFad(3, "Eg", 1000, "Sherry");
        fad3.setPåfyldt(true);
        Fad fad4 = Controller.createFad(4, "Eg", 1000, "Bourbon");
        fad4.setPåfyldt(true);
        Fad fad5 = Controller.createFad(5, "Birk", 500, "Sherry");
        fad5.setPåfyldt(true);

        List<Fad> fade = List.of(fad3, fad4);

        assertEquals(Controller.søgFad(null, "eg", 1000.0, null, true), fade);
    }

    @Test
    void søgFadTC3() {
        Controller.setStorage(new ListStorage());
        Fad fad1 = Controller.createFad(1, "Birk", 500, "Sherry");
        Fad fad2 = Controller.createFad(2, "Birk", 500, "Bourbon");
        Fad fad3 = Controller.createFad(3, "Eg", 1000, "Sherry");
        fad3.setPåfyldt(true);
        Fad fad4 = Controller.createFad(4, "Eg", 1000, "Bourbon");
        fad4.setPåfyldt(true);
        Fad fad5 = Controller.createFad(5, "Birk", 500, "Sherry");
        fad5.setPåfyldt(true);

        List<Fad> fade = List.of(fad1, fad2);

        assertEquals(Controller.søgFad(null, null, null, null, false), fade);
    }

    @Test
    void søgFadTC4() {
        Controller.setStorage(new ListStorage());
        Fad fad1 = Controller.createFad(1, "Birk", 500, "Sherry");
        Fad fad2 = Controller.createFad(2, "Birk", 500, "Bourbon");
        Fad fad3 = Controller.createFad(3, "Eg", 1000, "Sherry");
        fad3.setPåfyldt(true);
        Fad fad4 = Controller.createFad(4, "Eg", 1000, "Bourbon");
        fad4.setPåfyldt(true);
        Fad fad5 = Controller.createFad(5, "Birk", 500, "Sherry");
        fad5.setPåfyldt(true);

        List<Fad> fade = List.of(fad3, fad4, fad5);

        assertEquals(Controller.søgFad(null, null, null, null, true), fade);
    }

    @Test
    void søgFadTC5() {
        Controller.setStorage(new ListStorage());
        Fad fad1 = Controller.createFad(1, "Birk", 500, "Sherry");
        Fad fad2 = Controller.createFad(2, "Birk", 500, "Bourbon");
        Fad fad3 = Controller.createFad(3, "Eg", 1000, "Sherry");
        fad3.setPåfyldt(true);
        Fad fad4 = Controller.createFad(4, "Eg", 1000, "Bourbon");
        fad4.setPåfyldt(true);
        Fad fad5 = Controller.createFad(5, "Birk", 500, "Sherry");
        fad5.setPåfyldt(true);

        List<Fad> fade = List.of(fad1);

        assertEquals(Controller.søgFad(1, null, null, null, false), fade);
    }

    @Test
    void søgFadTC6() {
        Controller.setStorage(new ListStorage());
        Fad fad1 = Controller.createFad(1, "Birk", 500, "Sherry");
        Fad fad2 = Controller.createFad(2, "Birk", 500, "Bourbon");
        Fad fad3 = Controller.createFad(3, "Eg", 1000, "Sherry");
        fad3.setPåfyldt(true);
        Fad fad4 = Controller.createFad(4, "Eg", 1000, "Bourbon");
        fad4.setPåfyldt(true);
        Fad fad5 = Controller.createFad(5, "Birk", 500, "Sherry");
        fad5.setPåfyldt(true);

        List<Fad> fade = List.of();

        assertEquals(Controller.søgFad(1, null, null, null, true), fade);
    }

    @Test
    void createPåfyldningTC1() {
        Controller.setStorage(new ListStorage());
        NewMake newMake1 = new NewMake("NM1", LocalDateTime.now(), LocalDateTime.now(), 500, 60);
        MængdePåfyldt mængdePåfyldt = new MængdePåfyldt(newMake1, 100);
        ArrayList<MængdePåfyldt> mængdePåfyldts = new ArrayList<>(List.of(mængdePåfyldt));
        Fad fad1 = new Fad(1, "Eg", 500, "Sherry");
        LocalDate dato = LocalDate.of(2024,12,16);
        Påfyldning påfyldning = Controller.createPåfyldning("Henrik",dato,fad1,mængdePåfyldts);

        assertEquals(påfyldning.getMedarbejder(), "Henrik");
        assertEquals(påfyldning.getDato(), dato);
        assertEquals(påfyldning.getFad(), fad1);
        assertEquals(påfyldning.getMængdePåfyldt(), mængdePåfyldts);
        assertEquals(påfyldning.getSamletMængde(), 100);
        assertEquals(fad1.isPåfyldt(), true);
        assertEquals(newMake1.getAktuelMængde(), 400);
        assertEquals(Controller.getPåfyldninger().get(0), påfyldning);

    }
}