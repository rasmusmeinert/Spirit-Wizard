package GUI.Components;

import Controller.Controller;
import Model.Påfyldning;
import Model.Tapning;

import java.util.ArrayList;
import java.util.List;


/**
 * Compares a list of Påfyldning, with the list in the storage, only returns the one that are unique to the storage
 */
public class PåfyldningsUpdater implements ListUpdater {
    @Override
    public List<Object> update(Object list) {
        List<Object> nyListe = new ArrayList<>();
        List<Tapning> tapninger = (List<Tapning>) list;
        for (Object påfyldning : Controller.getTapbarePåfyldninger()) {
            boolean found = false;
            for (Tapning t : tapninger) {
                if (t.getPåfyldning().equals(påfyldning)) {
                    found = true;
                }
            }
            if (!found) {
                nyListe.add(påfyldning);
            }
        }
        return nyListe;
    }
}
