package Test;

import Model.Fad;
import Model.MængdePåfyldt;
import Model.NewMake;
import Model.Påfyldning;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PåfyldningTest {


    @Test
    void Påfyldning(){
        NewMake newmake = new NewMake("NMP68", LocalDateTime.of(2020,07,12,16,20),LocalDateTime.of(2020,07,13,14,20),1000,60);
        Fad fad = new Fad(15,"Birk",1200,"Sherry");
        List<MængdePåfyldt> mængderPåfyldt = List.of(new MængdePåfyldt(newmake,500));
        Påfyldning påfyldning = new Påfyldning("Martin",LocalDateTime.now(),fad,mængderPåfyldt);

//        assertEquals();
    }
}