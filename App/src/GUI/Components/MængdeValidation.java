package GUI.Components;

import Model.NewMake;
import Model.Påfyldning;

public class MængdeValidation implements Observer, Validation {
    private double max;

    @Override
    public boolean isValid(String string) {
        try {
            return (Double.parseDouble(string) > 0 && Double.parseDouble(string) <= max);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return "Ikke et tal, eller tal for højt";
    }

    @Override
    public void update(Object message) {
        if (message != null) {
            if (message.getClass().equals(NewMake.class)) {
                NewMake newMake = (NewMake) message;
                max = newMake.getAktuelMængde();
            } else if (message.getClass().equals(Påfyldning.class)) {
                Påfyldning påfyldning = (Påfyldning) message;
                max = påfyldning.getSamletMængde();
            }
        } else {
            max = 0;
        }
    }
}
