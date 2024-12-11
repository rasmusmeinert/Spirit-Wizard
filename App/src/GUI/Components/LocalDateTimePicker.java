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
        Button btnOk = new Button("OK");
        btnOk.setOnAction(e -> {
            LocalDate date = datePicker.getValue();
            if (date != null) {
                valgteTid = LocalDateTime.of(date, LocalTime.of(hourSpinner.getValue(), minuteSpinner.getValue()));
                pickerStage.close();
            }
        });

        Button btnAnnuller = new Button("Annuller");
        btnAnnuller.setOnAction(e -> {
            valgteTid = null;
            pickerStage.close();
        });

        // Layout
        HBox tidsBox = new HBox(10,
                new Label("Time:"), hourSpinner,
                new Label("Minut:"), minuteSpinner);

        VBox layout = new VBox(10, datePicker, tidsBox,
                new HBox(10, btnOk, btnAnnuller));
        layout.setPadding(new javafx.geometry.Insets(20));

        Scene scene = new Scene(layout);
        pickerStage.setOnShown(e -> {
            datePicker.show();
        });
        pickerStage.setScene(scene);
        pickerStage.showAndWait();

        return valgteTid;
    }
}
