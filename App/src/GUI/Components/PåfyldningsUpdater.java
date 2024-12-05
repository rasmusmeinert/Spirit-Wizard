package GUI.Components;

import Controller.Controller;
import Model.Påfyldning;

import java.util.ArrayList;
import java.util.List;


/**
 * Compares a list of Påfyldning, with the list in the storage, only returns the one that are unique to the storage
 */
public class PåfyldningsUpdater implements ListUpdater {
    @Override
    public List<Object> update(Object list) {
        List<Object> nyListe = new ArrayList<>();
        List<Påfyldning> påfyldninger = (List<Påfyldning>) list;
        for (Object påfyldning : Controller.getTapbarePåfyldninger()) {
            boolean found = false;
            for (Påfyldning p : påfyldninger) {
                if (p.equals(påfyldning)) {
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
