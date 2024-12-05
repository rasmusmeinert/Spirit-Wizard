package GUI.Components;

public class AdresseValidation implements Validation {
    private String errorMessage = "Indtast en Adresse";
    @Override
    //Todo, rent faktisk check om det er en adresse
    public boolean isValid(String string) {
        if (!string.equals("") && string.matches("^[a-zA-Z 0-9]*$")){
            return true;
        } else {
            return false;
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
