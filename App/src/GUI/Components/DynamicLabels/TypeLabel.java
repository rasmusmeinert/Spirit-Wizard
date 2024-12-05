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
        System.out.println(object.getClass());
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
        else if (object.getClass().equals(String.class)){
            String input = (String)object;
            if (input.isEmpty() && typeNavn != "") {
                caskStrength = ", cask strength";
            }
            else if (!input.isEmpty()){
                String inputFortynding = String.valueOf(input);
                fortyndingsMængde = Double.valueOf(inputFortynding);
                caskStrength = "";
            }
        }

        this.setText(labelName + typeNavn + caskStrength);
    }
}