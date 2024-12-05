package GUI.Components.Validations;

public interface Validation {

    boolean isValid(String string);

    String getErrorMessage();
}
