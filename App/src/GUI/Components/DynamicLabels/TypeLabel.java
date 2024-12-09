package GUI.Components.DynamicLabels;


import Model.Påfyldning;
import com.sun.javafx.collections.ObservableListWrapper;

import java.util.List;

public class TypeLabel extends DynamicLabel {
    String typeNavn = "";
    String caskStrength = "";
    double fortyndingsMængde = 0;

    public TypeLabel(String text) {
        super(text);
        this.labelName = labelName;
    }

    @Override
    public void update(Object object) {
        //Hvis valgte fade ændres
        if (object.getClass().equals(ObservableListWrapper.class)) {
            List<Påfyldning> liste = (List)object;
            if (fortyndingsMængde < 0.01) {
                caskStrength = ", cask strength";
            }
            if (liste.isEmpty()) {
                typeNavn = "";
                caskStrength = "";
            }
            else if (liste.size() > 1) {
                typeNavn = "Single malt";
            }
            else {
                typeNavn = "Single cask";
            }
        }
        //Hvis fortyndingsMængde ændres
        else if (object.getClass().equals(Double.class)){
            double input = (Double)object;
            if (input < 0.01 && typeNavn != "") {
                caskStrength = ", cask strength";
            }
            else {
                fortyndingsMængde = input;
                caskStrength = "";
            }
        }

        this.setText(labelName + typeNavn + caskStrength);
    }
}