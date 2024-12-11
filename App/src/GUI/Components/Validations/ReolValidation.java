package GUI.Components.Validations;

import GUI.Components.Input;
import GUI.Components.Observer;
import Model.Lager;
import Model.Reol;

public class ReolValidation implements Validation, Observer {
    private String errorMessage;
    private Lager lager;

    @Override
    public boolean isValid(String string) {

        try {
            int input = Integer.parseInt(string);
            if (input == 0) {
                errorMessage = "Denne reol findes ikke";
                return false;
            } else if (input >= lager.getReoler().size()) {
                errorMessage = "Denne reol findes ikke";
                return false;
            }
            if (input <= lager.getReoler().size() && !lager.getReol(input).isFull()) {
                return true;
            }
            errorMessage = "Reolen er fyldt";
            return false;

        } catch (NumberFormatException e) {
            errorMessage = "Indtast et tal";
            return false;
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void update(Object message) {
        if (message != null) {
            if (message.getClass().equals(Lager.class)) {
                lager = (Lager) message;
            }
        }
    }
}
