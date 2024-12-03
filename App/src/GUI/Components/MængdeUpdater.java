package GUI.Components;

import Controller.Controller;
import Model.MængdePåfyldt;

import java.util.ArrayList;
import java.util.List;

public class MængdeUpdater implements ListUpdater {

    public List<Object> update(Object list) {
        List<Object> nyListe = new ArrayList<>();
        List<MængdePåfyldt> mængder = (List<MængdePåfyldt>) list;
        for (Object newMake : Controller.getAktuelleNewMakes()) {
            boolean found = false;
            for (MængdePåfyldt mængdePåfyldt : mængder) {
                if (mængdePåfyldt.getNewMake().equals(newMake)) {
                    found = true;
                }
            }
            if (!found) {
                nyListe.add(newMake);
            }

        }
        return nyListe;

    }
}