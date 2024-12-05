package GUI.Components.DynamicLabels;

import GUI.Components.Input;
import GUI.Components.UpdateMessage;
import Model.Påfyldning;
import com.sun.javafx.collections.ObservableListWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AntalFlaskerLabel extends DynamicLabel{

    private double mængdePåfyldninger;
    private double mængdeFortyning;
    private double flaskeStørrelse;
    private Input input1;
    private Input input2;

    public AntalFlaskerLabel(String text, Input input1, Input input2) {
        super(text);
        labelName = text;
        this.input1 = input1;
        this.input2 = input2;
    }


    @Override
    public void update(Object object) {

        //Hvis valgte fade ændres
        if (object.getClass().equals(ObservableListWrapper.class)) {
            System.out.println("Liste");
            List<Påfyldning> liste = (List)object;
            if (liste.isEmpty()) {
                mængdePåfyldninger = 0;
                this.setText(labelName);
            }
            else {
                double udregnetMængdePåfyldninger = 0;
                for (Påfyldning p : liste) {
                    udregnetMængdePåfyldninger += p.getSamletMængde();
                }
                mængdePåfyldninger = udregnetMængdePåfyldninger;
            }
        }

        //Hvis flaskestørrelse eller fortynding ændres
        else if (object.getClass().equals(UpdateMessage.class)) {
            UpdateMessage um = (UpdateMessage) object;
            System.out.println(String.valueOf(um.getObject()));
            String inputLabel = String.valueOf(um.getObject());
            String inputTal = String.valueOf(um.getMessage());
            //Hvis flaskestørrelse ændres
            if (inputLabel.equals(input1.getLabelText())) {
                if (!inputTal.isEmpty()) {
                    flaskeStørrelse = Double.parseDouble(inputTal);
                }
                else {
                    flaskeStørrelse = 0;
                    this.setText(labelName);
                }
            }
            //Hvis fortynding ændres
            else if (inputLabel.equals(input2.getLabelText())) {
                if (!inputTal.isEmpty()) {
                    mængdeFortyning = Double.parseDouble(inputTal);
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
