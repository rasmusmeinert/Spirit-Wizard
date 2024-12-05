package GUI.Components.Validations;

public class NumberValidationWithZero implements Validation {
    private String errorMessage = "Indtast et tal eller 0";
    @Override
    public boolean isValid(String string){
        try {
            return (Double.parseDouble(string) > -1);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
