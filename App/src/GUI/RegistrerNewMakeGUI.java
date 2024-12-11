package GUI;

import Controller.Controller;
import GUI.Components.ConfirmationWindow;
import GUI.Components.CreateButton;
import GUI.Components.Input;
import GUI.Components.LocalDateTimePicker;
import GUI.Components.Validations.AdresseValidation;
import GUI.Components.Validations.LocalDateTimeValidator;
import GUI.Components.Validations.NumberValidation;
import GUI.Components.Validations.StringValidation;
import Model.NewMake;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RegistrerNewMakeGUI extends Tab {
    private final Input inputNavn = new Input("Navn: ", new AdresseValidation());
    private final Input inputStartDato = new Input("Start dato: ", new LocalDateTimeValidator());
    private final Input inputSlutDato = new Input("Slut dato: ", new LocalDateTimeValidator());
    private final Input inputMængde = new Input("Mængde (liter): ", new NumberValidation());
    private final Input inputAlkoholProcent = new Input("Alkoholprocent (%): ", new NumberValidation());

    private final CreateButton btnCreate = new CreateButton();


    public RegistrerNewMakeGUI(String s) {
        super(s);
        GridPane pane = new GridPane();
        initContent(pane);
        setContent(pane);
    }

    public void initContent(GridPane pane) {
//        pane.setGridLinesVisible(true);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(15));
        pane.setHgap(15);
        pane.setVgap(10);
        int labelWidth = 110;

        Label lblRegistrerFad = new Label("Opret NewMake");
        lblRegistrerFad.setStyle("-fx-font-weight: bold");
        GridPane.setHalignment(lblRegistrerFad, HPos.CENTER);
        pane.add(lblRegistrerFad, 0, 0);

        pane.add(inputNavn, 0, 1);
        inputNavn.setWidth(labelWidth);
        inputNavn.addObserver(btnCreate);

        pane.add(inputStartDato, 0, 2);
        inputStartDato.getTextField().setEditable(false);
        inputStartDato.setWidth(labelWidth);
        inputStartDato.addObserver(btnCreate);
        inputStartDato.getTextField().setOnMouseClicked(e -> launchDateTimePicker(inputStartDato));
//        inputStartDato.getTextField().focusedProperty().addListener(e -> launchDateTimePicker(inputStartDato));

        pane.add(inputSlutDato, 0, 3);
        inputSlutDato.getTextField().setEditable(false);
        inputSlutDato.setWidth(labelWidth);
        inputSlutDato.addObserver(btnCreate);
        inputSlutDato.getTextField().setOnMouseClicked(e -> launchDateTimePicker(inputSlutDato));

        pane.add(inputMængde, 0, 4);
        inputMængde.setWidth(labelWidth);
        inputMængde.addObserver(btnCreate);

        pane.add(inputAlkoholProcent, 0, 5);
        inputAlkoholProcent.setWidth(labelWidth);
        inputAlkoholProcent.addObserver(btnCreate);

        pane.add(btnCreate,0,6);
        GridPane.setHalignment(btnCreate,HPos.CENTER);
        btnCreate.setOnAction(e -> opretNewMake());
    }
    private void launchDateTimePicker(Input input) {
        LocalDateTimePicker dateTimePicker = new LocalDateTimePicker();
        LocalDateTime selectedDateTime = dateTimePicker.visPicker((Stage) input.getScene().getWindow());

        if (selectedDateTime != null) {
            input.setText(selectedDateTime.toString()); // Update the Input field with the selected date-time
        }
    }

    private void clearContent(){
        inputNavn.clear();
        inputStartDato.clear();
        inputSlutDato.clear();
        inputMængde.clear();
        inputAlkoholProcent.clear();

    }

    public void opretNewMake(){
        NewMake newMake = new NewMake(inputNavn.getText(), LocalDateTime.parse(inputStartDato.getText()), LocalDateTime.parse(inputSlutDato.getText()), inputMængde.getTextAsDouble(), inputAlkoholProcent.getTextAsDouble());
        ConfirmationWindow alert = new ConfirmationWindow(newMake);
        alert.showAndWait().ifPresent(response -> {
            if (response == alert.getButtonTypes().get(1)){
                Controller.createNewMake(inputNavn.getText(), LocalDateTime.parse(inputStartDato.getText()), LocalDateTime.parse(inputSlutDato.getText()), inputMængde.getTextAsDouble(), inputAlkoholProcent.getTextAsDouble());
                clearContent();
            }
        });
    }
}
