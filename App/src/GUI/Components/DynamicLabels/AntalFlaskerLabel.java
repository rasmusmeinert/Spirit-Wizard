package GUI.Components.DynamicLabels;

import GUI.Components.Input;
import GUI.Components.UpdateMessage;
import Model.Påfyldning;
import Model.Tapning;
import com.sun.javafx.collections.ObservableListWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AntalFlaskerLabel extends DynamicLabel{

    private double mængdePåfyldninger;
    private double mængdeFortyning;
    private double flaskeStørrelse;
    private String  input1;
    private String  input2;

    public AntalFlaskerLabel(String text, String  input1, String  input2) {
        super(text);
        labelName = text;
        this.input1 = input1;
        this.input2 = input2;
    }


    @Override
    public void update(Object object) {

        //Hvis valgte fade ændres
        if (object.getClass().equals(ObservableListWrapper.class)) {
            List<Tapning> liste = (List)object;
            if (liste.isEmpty()) {
                mængdePåfyldninger = 0;
                this.setText(labelName);
            }
            else {
                double udregnetMængdePåfyldninger = 0;
                for (Tapning t : liste) {
                    udregnetMængdePåfyldninger += t.getMængde();
                }
                mængdePåfyldninger = udregnetMængdePåfyldninger;
            }
        }

        //Hvis flaskestørrelse eller fortynding ændres
        else if (object.getClass().equals(UpdateMessage.class)) {
            UpdateMessage um = (UpdateMessage) object;
            String inputLabel = String.valueOf(um.getObject());
            double input = (double) um.getMessage();
            //Hvis flaskestørrelse ændres
            if (inputLabel.equals(input1)) {
                if (input > 0.01) {
                    flaskeStørrelse = input;
                }
                else {
                    flaskeStørrelse = 0;
                    this.setText(labelName);
                }
            }
            //Hvis fortynding ændres
            else if (inputLabel.equals(input2)) {
                if (input > 0.01) {
                    mængdeFortyning = input;
                }
                else {
                    mængdeFortyning = 0;
                }
            }
        }
        //Hvis der er data til at udregne antal flasker
        if (mængdePåfyldninger > 0 && flaskeStørrelse > 0.01) {
            int antalFlasker = (int)((mængdePåfyldninger + mængdeFortyning) / flaskeStørrelse);
            this.setText(labelName + antalFlasker);
        }
    }
}
