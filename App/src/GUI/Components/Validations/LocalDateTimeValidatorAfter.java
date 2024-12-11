package GUI.Components.Validations;

import GUI.Components.Input;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeValidatorAfter implements Validation {

    private String errorMessage = "Indtast en dato i formatet: \"yyyy-MM-dd'T'HH:mm\"";
    private Input input;

    public LocalDateTimeValidatorAfter(Input input) {
        this.input = input;
    }

    public boolean isValid(String localDateTimeString) {

        if (LocalDateTime.parse(localDateTimeString).isBefore(input.getTextAsLocalDateTime())) {
            errorMessage = "Indast en dato og klokkeslet som er efter start dato";
            return false;
        }

        DateTimeFormatter datoFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        try {
            LocalDateTime.parse(localDateTimeString, datoFormat);
            return true;
        } catch (DateTimeParseException e) {
            errorMessage = "Indtast en dato i formatet: \"yyyy-MM-dd'T'HH:mm\"";
            return false;
        }
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
