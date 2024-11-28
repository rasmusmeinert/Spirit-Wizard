package Controller;

import Model.Fad;
import Model.NewMake;
import Model.Påfyldning;

import java.util.List;

public interface Storage {
    List<Fad> getFade();
    void storeFad(Fad fad);
    void deleteFad(Fad fad);


    List<NewMake> getNewMakes();
    void deleteNewMake(NewMake newMake);
    void storeNewMake(NewMake newMake);

    List<Påfyldning> getPåfyldninger();

    void storePåfyldning(Påfyldning påfyldning);
    void deletePåfyldning(Påfyldning påfyldning);
}
