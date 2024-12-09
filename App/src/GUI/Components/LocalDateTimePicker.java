package GUI.Components;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimePicker extends Application {

    private final DatePicker datePicker = new DatePicker();
    private final Spinner<Integer> hourSpinner = new Spinner<>();
    private final Spinner<Integer> minuteSpinner = new Spinner<>();
    private LocalDateTime valgteTid;

    public LocalDateTimePicker() {
        // Spinners
        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, LocalTime.now().getHour()));
        hourSpinner.setEditable(true);
        minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 00));
        minuteSpinner.setEditable(true);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        visPicker(new Stage());
    }

    public LocalDateTime visPicker(Stage parent) {
        Stage pickerStage = new Stage();
        pickerStage.initOwner(parent);
        //Hvad er modality?
        pickerStage.initModality(Modality.APPLICATION_MODAL);
        pickerStage.setTitle("Vælg dato og tid");

        // Confirm button
        Button confirmButton = new Button("OK");
        confirmButton.setOnAction(e -> {
            LocalDate date = datePicker.getValue();
            if (date != null) {
                valgteTid = LocalDateTime.of(date, LocalTime.of(hourSpinner.getValue(), minuteSpinner.getValue()));
                pickerStage.close();
            }
        });

        // Cancel button
        Button cancelButton = new Button("Annuller");
        cancelButton.setOnAction(e -> {
            valgteTid = null;
            pickerStage.close();
        });

        // Layout
        HBox timeBox = new HBox(10,
                new Label("Hour:"), hourSpinner,
                new Label("Minute:"), minuteSpinner);

        VBox layout = new VBox(10, datePicker, timeBox,
                new HBox(10, confirmButton, cancelButton));
        layout.setPadding(new javafx.geometry.Insets(20));

        // Scene setup
        Scene scene = new Scene(layout);
        pickerStage.setScene(scene);
        pickerStage.showAndWait();

        return valgteTid; // Return the result after the window is closed
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public Spinner<Integer> getHourSpinner() {
        return hourSpinner;
    }

    public Spinner<Integer> getMinuteSpinner() {
        return minuteSpinner;
    }

    private void updateLabel(DatePicker datePicker, Spinner<Integer> hourSpinner, Spinner<Integer> minuteSpinner, Label label) {
        LocalDate date = datePicker.getValue();
        if (date != null) {
            LocalTime time = LocalTime.of(hourSpinner.getValue(), minuteSpinner.getValue());
            LocalDateTime dateTime = LocalDateTime.of(date, time);
            label.setText("Selected Date-Time: " + dateTime);
        }
    }

}
