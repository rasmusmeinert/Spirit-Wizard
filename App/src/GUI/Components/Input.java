package GUI.Components;

import GUI.Components.DynamicLabels.*;
import GUI.Components.Validations.HyldeValidation;
import GUI.Components.Validations.ReolValidation;
import GUI.Components.Validations.StringValidation;
import javafx.geometry.HPos;
import GUI.Components.Validations.Validation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A label and a textfield, with a validation
 */
public class Input extends GridPane implements Observer {
    private final Label label = new Label();
    private final TextField textField = new TextField();
    private final Label errorMessage = new Label("");
    private final Validation validation;
    private final List<Observer> observers = new ArrayList<>();

    /**
    Creates a Label with given String, also creates a TextField, with a Validator that checks if input is correct
     */
    public Input(String labelString, Validation validation) {
        label.setText(labelString);
        textField.setMaxWidth(150);
//        setGridLinesVisible(true);



        add(label,0,0);
        add(textField,1,0);
        add(errorMessage,1,1);
        GridPane.setHalignment(textField, HPos.RIGHT);



        errorMessage.setStyle("-fx-text-fill: red");

        setVgap(5);
        setHgap(5);

        //Det er den her som fucker med navne inputtet i opret påfyldningsGUIen
        setAlignment(Pos.CENTER);

        this.validation = validation;
        textField.textProperty().addListener(e -> {
            validateTextField();
        });
    }

    public String getText() {
        return textField.getText();
    }


    public void setWidth(int width){
        label.setMinWidth(width);
    }

    public int getTextAsInt(){
        try {
            return Integer.parseInt(getText());
        }
        catch (NumberFormatException e) {
            return 0;
        }
    }

    public TextField getTextField() {
        return textField;
    }

    public Double getTextAsDouble() {
        try {
            return Double.parseDouble(textField.getText());
        }
        catch (NumberFormatException e) {
            return 0.;
        }
    }
    public LocalDateTime getTextAsLocalDateTime() {
        return LocalDateTime.parse(textField.getText());
    }

    //Checks if input is correct, also notifies observing buttons
    public void validateTextField() {
        if (validation.isValid(textField.getText())) {
            textField.setStyle("");
            notifyObservers(false);
            errorMessage.setText("");
        } else {
            if (getText().isEmpty()) {
                notifyObservers(true);
                errorMessage.setText("");
            } else {
                notifyObservers(true);
                textField.setStyle("-fx-focus-color: red");
                errorMessage.setText(validation.getErrorMessage());
            }
        }
    }

    /**
     * Toggles observing buttons
     * @param disable
     */
    public void notifyObservers(Boolean disable) {
        for (Observer observer : observers) {
            if (observer.getClass().equals(CreateButton.class)) {
                observer.update(new UpdateMessage(this, disable));
            }
            else if(observer.getClass().equals(AntalFlaskerLabel.class)) {
                observer.update(new UpdateMessage(this.label.getText(), this.getTextAsDouble()));
            }
            else if(observer.getClass().getSuperclass().equals(DynamicLabel.class)) {
                observer.update(this.getTextAsDouble());
            }
            else if(observer.getClass().equals(ReolValidation.class)) {
                observer.update(this.getTextAsInt());
            }
            else if(observer.getClass().equals(HyldeValidation.class)) {
                observer.update(this.getTextAsInt());
            }

            else if (observer.getClass().equals(SearchList.class)){
                if (getText().isEmpty() || disable){
                    observer.update(new UpdateMessage(this.label.getText(), null));
                } else {
                    observer.update(new UpdateMessage(this.label.getText(), this.getText()));
                }
            }
            else if (observer.getClass().equals(CustomButton.class))  {
                observer.update(disable);
            }
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
        notifyObservers(true);
    }

    public void setTextFieldPrefWidth(int size) {
        textField.setPrefWidth(size);
    }

    public void clear() {
        textField.clear();
    }

    public String getLabelText() {
        return this.label.getText();
    }

    public void setText(String string) {
        this.textField.setText(string);
    }

    @Override
    public void update(Object message) {
        validateTextField();
        if (message == null) {
            setDisable(true);
        } else {
            setDisable(false);
        }
    }
}
