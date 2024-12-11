package GUI.Components.Validations;

import GUI.Components.Observer;
import Model.Lager;
import Model.Reol;

public class HyldeValidation implements Validation, Observer {
    private String errorMessage = "Denne hylde findes ikke";
    private Lager lager;
    private int reolNr;

    @Override
    public boolean isValid(String string) {
        try {
            int input = Integer.parseInt(string);
            if (input == 0) {
                errorMessage = "Denne hylde findes ikke";
                return false;
            }
            Reol observingReol = lager.getReoler().get(reolNr);
            if (input >= observingReol.getHylder().length) {
                errorMessage = "Denne hylde findes ikke";
                return false;
            } else if (input < observingReol.getHylder().length && observingReol.getHylder()[input] == null) {
                return true;
            }
            System.out.println(observingReol.getHylder()[input]);
            errorMessage = "Hylden er optaget";
            return false;

        } catch (NumberFormatException e) {
            errorMessage = "Indtast et tal";
            return false;
        }


//        System.out.println("HyldeValidation: isValid()");
//        int input = Integer.parseInt(string);
//        try {
//            return (lager.getReol(reol).getHylder()[input] == null);
//        } catch (NumberFormatException e) {
//            return false;
//        }
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
            if (message.getClass().equals(Integer.class)) {
                reolNr = (int) message;
            }
        }
    }
}
