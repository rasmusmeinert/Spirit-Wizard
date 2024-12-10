package Test;

import Model.Fad;
import Model.MængdePåfyldt;
import Model.NewMake;
import Model.Påfyldning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PåfyldningTest {


    @Test
    void PåfyldningTC1(){
        NewMake newmake = new NewMake("NMP68", LocalDateTime.of(2020,07,12,16,20),LocalDateTime.of(2020,07,13,14,20),500,60);
        Fad fad = new Fad(1,"Egetræ",70,"Bourbon");
        ArrayList<MængdePåfyldt> mængderPåfyldt = new ArrayList<>(List.of(new MængdePåfyldt(newmake,40)));
        LocalDate dato = LocalDate.of(2024,12,16);
        Påfyldning påfyldning = new Påfyldning("Rasmus", dato,fad,mængderPåfyldt);


        assertEquals(påfyldning.getMedarbejder(), "Rasmus");
        assertEquals(påfyldning.getMængdePåfyldt(), mængderPåfyldt);
        assertEquals(påfyldning.getSamletMængdePåfyldt(), 40);
        assertEquals(påfyldning.getSamletMængde(), 40);
        assertEquals(påfyldning.getFad(),fad);
        assertEquals(påfyldning.getDato(), dato);
    }



    @Test
    void udregnSamletMængdeTc1() {
        NewMake newMake1 = new NewMake("NMP68", LocalDateTime.of(2020,07,12,16,20),LocalDateTime.of(2020,07,13,14,20),500,60);
        NewMake newMake2 = new NewMake("NMP68", LocalDateTime.of(2020,07,12,16,20),LocalDateTime.of(2020,07,13,14,20),500,60);
        Fad fad = new Fad(1, "Egetræ", 140, "Bourbon");
        LocalDate dato = LocalDate.of(2024,12,16);
        ArrayList<MængdePåfyldt> mængderPåfyldt = new ArrayList<>(List.of(new MængdePåfyldt(newMake1,70), new MængdePåfyldt(newMake2, 70)));
        Påfyldning påfyldning = new Påfyldning("Rasmus", dato,fad,mængderPåfyldt);

        assertEquals(påfyldning.getSamletMængde(),140);
    }

    @Test
    void reducerMængdeTC1() {
        NewMake newMake1 = new NewMake("NMP68", LocalDateTime.of(2020,07,12,16,20),LocalDateTime.of(2020,07,13,14,20),500,60);
        NewMake newMake2 = new NewMake("NMP68", LocalDateTime.of(2020,07,12,16,20),LocalDateTime.of(2020,07,13,14,20),500,60);
        Fad fad = new Fad(1, "Egetræ", 140, "Bourbon");
        LocalDate dato = LocalDate.of(2024,12,16);
        ArrayList<MængdePåfyldt> mængderPåfyldt = new ArrayList<>(List.of(new MængdePåfyldt(newMake1,70), new MængdePåfyldt(newMake2, 70)));
        Påfyldning påfyldning = new Påfyldning("Rasmus", dato,fad,mængderPåfyldt);

        påfyldning.reducerMængde(70);
        assertEquals(påfyldning.getSamletMængde(), 70);
    }

    @Test
    void reducerMængdeTC2() {
        NewMake newMake1 = new NewMake("NMP68", LocalDateTime.of(2020,07,12,16,20),LocalDateTime.of(2020,07,13,14,20),500,60);
        NewMake newMake2 = new NewMake("NMP68", LocalDateTime.of(2020,07,12,16,20),LocalDateTime.of(2020,07,13,14,20),500,60);
        Fad fad = new Fad(1, "Egetræ", 140, "Bourbon");
        LocalDate dato = LocalDate.of(2024,12,16);
        ArrayList<MængdePåfyldt> mængderPåfyldt = new ArrayList<>(List.of(new MængdePåfyldt(newMake1,70), new MængdePåfyldt(newMake2, 70)));
        Påfyldning påfyldning = new Påfyldning("Rasmus", dato,fad,mængderPåfyldt);

        påfyldning.reducerMængde(0);
        assertEquals(påfyldning.getSamletMængde(), 140);
    }

    @Test
    void getSamletMængde() {
        NewMake newMake1 = new NewMake("NMP68", LocalDateTime.of(2020,07,12,16,20),LocalDateTime.of(2020,07,13,14,20),500,60);
        NewMake newMake2 = new NewMake("NMP68", LocalDateTime.of(2020,07,12,16,20),LocalDateTime.of(2020,07,13,14,20),500,60);
        Fad fad = new Fad(1, "Egetræ", 140, "Bourbon");
        LocalDate dato = LocalDate.of(2024,12,16);
        ArrayList<MængdePåfyldt> mængderPåfyldt = new ArrayList<>(List.of(new MængdePåfyldt(newMake1,70), new MængdePåfyldt(newMake2, 70)));
        Påfyldning påfyldning = new Påfyldning("Rasmus", dato,fad,mængderPåfyldt);

        assertEquals(påfyldning.getSamletMængde(), 140);
    }

    @Test
    void getSamletMængdePåfyldt() {
        NewMake newMake1 = new NewMake("NMP68", LocalDateTime.of(2020,07,12,16,20),LocalDateTime.of(2020,07,13,14,20),500,60);
        NewMake newMake2 = new NewMake("NMP68", LocalDateTime.of(2020,07,12,16,20),LocalDateTime.of(2020,07,13,14,20),500,60);
        Fad fad = new Fad(1, "Egetræ", 140, "Bourbon");
        LocalDate dato = LocalDate.of(2024,12,16);
        ArrayList<MængdePåfyldt> mængderPåfyldt = new ArrayList<>(List.of(new MængdePåfyldt(newMake1,70), new MængdePåfyldt(newMake2, 70)));
        Påfyldning påfyldning = new Påfyldning("Rasmus", dato,fad,mængderPåfyldt);

        assertEquals(påfyldning.getSamletMængdePåfyldt(), 140);
    }

    @Test
    void getMedarbejder() {
        NewMake newMake1 = new NewMake("NMP68", LocalDateTime.of(2020,07,12,16,20),LocalDateTime.of(2020,07,13,14,20),500,60);
        Fad fad = new Fad(1, "Egetræ", 140, "Bourbon");
        LocalDate dato = LocalDate.of(2024,12,16);
        ArrayList<MængdePåfyldt> mængderPåfyldt = new ArrayList<>(List.of(new MængdePåfyldt(newMake1,70)));
        Påfyldning påfyldning = new Påfyldning("Henrik", dato,fad,mængderPåfyldt);


        assertEquals(påfyldning.getMedarbejder(), "Henrik");
    }

    @Test
    void getDato() {
        NewMake newMake1 = new NewMake("NMP68", LocalDateTime.of(2020,07,12,16,20),LocalDateTime.of(2020,07,13,14,20),500,60);
        Fad fad = new Fad(1, "Egetræ", 140, "Bourbon");
        LocalDate dato = LocalDate.of(2024,12,16);
        ArrayList<MængdePåfyldt> mængderPåfyldt = new ArrayList<>(List.of(new MængdePåfyldt(newMake1,70)));
        Påfyldning påfyldning = new Påfyldning("Henrik", dato,fad,mængderPåfyldt);


        assertEquals(påfyldning.getDato(), LocalDate.of(2024,12,16));
    }

    @Test
    void getFad() {

        NewMake newMake1 = new NewMake("NMP68", LocalDateTime.of(2020,07,12,16,20),LocalDateTime.of(2020,07,13,14,20),500,60);
        Fad fad = new Fad(1, "Egetræ", 140, "Bourbon");
        LocalDate dato = LocalDate.of(2024,12,16);
        ArrayList<MængdePåfyldt> mængderPåfyldt = new ArrayList<>(List.of(new MængdePåfyldt(newMake1,70)));
        Påfyldning påfyldning = new Påfyldning("Henrik", dato,fad,mængderPåfyldt);


        assertEquals(påfyldning.getFad(), fad);

    }

    @Test
    void getMængdePåfyldt() {
        NewMake newMake1 = new NewMake("NMP68", LocalDateTime.of(2020,07,12,16,20),LocalDateTime.of(2020,07,13,14,20),500,60);
        NewMake newMake2 = new NewMake("NMP68", LocalDateTime.of(2020,07,12,16,20),LocalDateTime.of(2020,07,13,14,20),500,60);
        Fad fad = new Fad(1, "Egetræ", 140, "Bourbon");
        LocalDate dato = LocalDate.of(2024,12,16);
        ArrayList<MængdePåfyldt> mængderPåfyldt = new ArrayList<>(List.of(new MængdePåfyldt(newMake1,70), new MængdePåfyldt(newMake2, 70)));
        Påfyldning påfyldning = new Påfyldning("Rasmus", dato,fad,mængderPåfyldt);

        assertEquals(påfyldning.getMængdePåfyldt(), mængderPåfyldt);
    }
}