package GUI.Components.Validations;

/**
 * A validation behaviour that checks if a given string is a valid number above 0
 */
public class NumberValidation implements Validation {
    private String errorMessage = "Indtast et tal";
    @Override
    public boolean isValid(String string){
        try {
            return (Double.parseDouble(string) > 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
