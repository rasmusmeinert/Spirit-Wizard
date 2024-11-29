package GUI.Components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Input extends VBox {
    private final Label label = new Label();
    private final TextField textField = new TextField();
    private final Label errorMessage = new Label("");
    private final Validation validation;
    private final List<Observer> observers = new ArrayList<>();

    //Creates a Label with given String, also creates a TextField, with a Validator that checks if input is correct
    public Input(String labelString, Validation validation) {
        label.setText(labelString);
        textField.setMaxWidth(150);

        HBox box = new HBox();
        box.setSpacing(10);
        box.setAlignment(Pos.BASELINE_LEFT);
        box.getChildren().addAll(label, textField);
        getChildren().addAll(box, errorMessage);

        errorMessage.setStyle("-fx-text-fill: red");

        setSpacing(5);
        setAlignment(Pos.CENTER);
        this.validation = validation;
        textField.textProperty().addListener(e -> {
            validateTextField();
        });
    }

    public String getText() {
        return textField.getText();
    }

    public TextField getTextField() {
        return textField;
    }

    //Checks if input is correct, also notifies observing buttons
    public void validateTextField() {
        if (validation.isValid(textField.getText())) {
            textField.setStyle("");
            notifyObservers(false);
            errorMessage.setText("");
        } else {
            textField.setStyle("-fx-focus-color: red");
            notifyObservers(true);
            errorMessage.setText(validation.getErrorMessage());
        }
    }

    //Toggles button
    public void notifyObservers(Boolean disable) {
        for (Observer observer : observers) {
            observer.update(disable);
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void clear() {
        textField.clear();
    }
}
