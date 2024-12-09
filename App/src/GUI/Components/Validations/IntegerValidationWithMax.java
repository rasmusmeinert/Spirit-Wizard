package GUI.Components.Validations;

import GUI.Components.Observer;
import Model.Lager;

public class IntegerValidationWithMax implements Validation, Observer {
    private int max;
    private String errorMessage;
    private String lookingFor;

    public IntegerValidationWithMax(String lookingFor) {
        this.lookingFor = lookingFor;
    }

    @Override
    public boolean isValid(String string) {
        try {
            int num = Integer.parseInt(string);
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

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void update(Object message) {
        if (message.getClass().equals(Lager.class)){
            Lager lager = (Lager) message;
            if (lookingFor.equalsIgnoreCase("Reoler")){
                max = lager.getReoler().size();
            } else if (lookingFor.equalsIgnoreCase("Hylder")){
                max = lager.getHylderPerReol();
            }
        }
    }
}
