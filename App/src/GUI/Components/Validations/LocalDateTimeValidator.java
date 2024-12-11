package GUI.Components.Validations;

import javax.xml.validation.Validator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeValidator implements Validation {
    private String errorMessage = "Indtast en date i formatet: \"yyyy-MM-dd'T'HH:mm\"";
    public boolean isValid(String localDateTimeString) {
        DateTimeFormatter datoFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        try {
            LocalDateTime.parse(localDateTimeString, datoFormat);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
