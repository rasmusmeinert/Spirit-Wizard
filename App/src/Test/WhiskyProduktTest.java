package Test;

import Model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WhiskyProduktTest {

    @Test
    void udregnTypeTC1() {
        NewMake nm1 = new NewMake("NM1", LocalDateTime.now(), LocalDateTime.now(), 500, 60);
        Fad fad = new Fad(1, "Birk", 500, "Sherry");
        MængdePåfyldt mængdePåfyldt1 = new MængdePåfyldt(nm1, 100);
        Påfyldning påfyldning = new Påfyldning("Rasmus", LocalDate.now(), fad, new ArrayList<>(List.of(mængdePåfyldt1)));
        ArrayList<Tapning> tapninger = new ArrayList<>(List.of(new Tapning(100, påfyldning)));
        WhiskyProdukt whiskyProdukt = new WhiskyProdukt("Whisky", 60, 1, "God Whisky", 0, tapninger);

        assertEquals(whiskyProdukt.getType(), "Single cask, cask strength");
    }

    @Test
    void udregnTypeTC2() {
        NewMake nm1 = new NewMake("NM1", LocalDateTime.now(), LocalDateTime.now(), 500, 60);
        Fad fad = new Fad(1, "Birk", 500, "Sherry");
        MængdePåfyldt mængdePåfyldt1 = new MængdePåfyldt(nm1, 100);
        Påfyldning påfyldning = new Påfyldning("Rasmus", LocalDate.now(), fad, new ArrayList<>(List.of(mængdePåfyldt1)));
        NewMake nm2 = new NewMake("NM1", LocalDateTime.now(), LocalDateTime.now(), 500, 60);
        Fad fad2 = new Fad(1, "Birk", 500, "Sherry");
        MængdePåfyldt mængdePåfyldt2 = new MængdePåfyldt(nm2, 100);
        Påfyldning påfyldning2 = new Påfyldning("Rasmus", LocalDate.now(), fad2, new ArrayList<>(List.of(mængdePåfyldt2)));
        ArrayList<Tapning> tapninger = new ArrayList<>(List.of(new Tapning(100, påfyldning), new Tapning(100, påfyldning2)));
        WhiskyProdukt whiskyProdukt = new WhiskyProdukt("Whisky", 60, 1, "God Whisky", 100, tapninger);

        assertEquals(whiskyProdukt.getType(), "Single malt");
    }

}