package GUI.Components;


/**
 * A validation behaviour that checks if a given string only contains letters and spaces
 */
public class StringValidation implements Validation{
    private String errorMessage = "Indtast et ord";
    @Override
    public boolean isValid(String string) {
        if (!string.equals("") && string.matches("^[a-zA-Z ]*$")){
            return true;
        } else {
            return false;
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
