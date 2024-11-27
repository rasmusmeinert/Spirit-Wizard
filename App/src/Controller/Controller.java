package Controller;

import Model.Fad;

public abstract class Controller {
    private static Storage storage;

    public static void setStorage(Storage storage) {
        Controller.storage = storage;
    }

    //===============================================================================

    public static Fad createFad(int nummer, String trætype, double størrelse, String tidligereIndhold){
        Fad fad = new Fad(nummer, trætype, størrelse, tidligereIndhold);
        storage.storeFad(fad);
        return fad;
    }

}
