package GUI.Components.Validations;

import GUI.Components.Observer;
import Model.Lager;
import Model.Reol;

import javax.xml.validation.Validator;

public class HyldeValidation implements Validation, Observer {
    private String errorMessage = "Hylden er optaget";
    private Lager lager;
    private int reol;

    @Override
    public boolean isValid(String string){
        try {
            int input = Integer.parseInt(string);
            if (input <= lager.getReoler().get(reol -1).getHylder().length && lager.getReoler().get(reol).getHylder()[input - 1] != null && input != 0) {
                return true;
            }
            else if (input > lager.getReoler().get(reol).getHylder().length || input == 0){
                errorMessage = "Denne hylde findes ikke";
                return false;
            }
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
        System.out.println(message.getClass());
        if (message != null) {
            if (message.getClass().equals(Lager.class)) {
                lager = (Lager) message;
            }
            if (message.getClass().equals(Integer.class)) {
                reol = (int) message;
            }
        }
        System.out.println("real = " + reol);
    }
}
