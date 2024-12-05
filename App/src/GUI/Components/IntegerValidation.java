package GUI.Components;

public class IntegerValidation implements Validation{
    private String errorMessage = "Indtast et heltal";
    @Override
    public boolean isValid(String string){
        try {
            return (Integer.parseInt(string) > 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
