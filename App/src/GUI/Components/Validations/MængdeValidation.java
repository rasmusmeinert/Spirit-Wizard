package GUI.Components.Validations;

import GUI.Components.Observer;
import Model.Fad;
import Model.NewMake;
import Model.Påfyldning;

/**
 * A validation behaviour that checks if a given string is a number below a maximum value observed.
 */
public class MængdeValidation implements Observer, Validation {
    private double max;
    private String errorMessage;

    @Override
    public boolean isValid(String string) {
        try {
            double num = Double.parseDouble(string);
            if (num <= 0){
                errorMessage =  "Indtast et tal over 0";
                return false;
            } else if (num > max){
                errorMessage = "Indtast et tal under " + max;
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            errorMessage = "Indtast et tal";
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    //TODO
    //Det her kan måske gøres mere generisk i stedet for det her helvede

    /**
     * Depending on what object the validation is send, it sets the max value to a corresponding number
     * @param message
     */
    @Override
    public void update(Object message) {
        if (message != null) {
            if (message.getClass().equals(NewMake.class)) {
                NewMake newMake = (NewMake) message;
                max = newMake.getAktuelMængde();
            } else if (message.getClass().equals(Påfyldning.class)) {
                Påfyldning påfyldning = (Påfyldning) message;
                max = påfyldning.getSamletMængde();
            } else if (message.getClass().equals(Fad.class)){
                Fad fad = (Fad) message;
                max = fad.getStørrelse();
            }
        } else {
            max = 0;
        }
    }
}
