package Controller;

import Model.Fad;

import java.util.List;

public interface Storage {
    List<Fad> getFade();
    void storeFad(Fad fad);
    void deleteFad(Fad fad);
}
