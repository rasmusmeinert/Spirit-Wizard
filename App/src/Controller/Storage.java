package Controller;

import Model.*;

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

    void storeWhiskyProdukt(WhiskyProdukt whiskyProdukt);
    void deleteWhiskyProdukt(WhiskyProdukt whiskyProdukt);

    List<WhiskyProdukt> getWhiskyProdukter();

    void storeLager(Lager lager);

    void deleteLager(Lager lager);

    List<Lager> getLagere();
}
