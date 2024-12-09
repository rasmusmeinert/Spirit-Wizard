package GUI;

import Controller.Controller;
import GUI.Components.*;
import GUI.Components.Validations.Validation;
import GUI.Components.Validations.*;
import GUI.Components.CreateButton;
import Model.Lager;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegistrerLagerGUI extends Tab {
    private final Validation integerValidator = new IntegerValidation();
    private final Input inputNavn = new Input("Navn:", new StringValidation());
    private final Input inputAdresse = new Input("Adresse:", new AdresseValidation());
    private final Input inputReoler = new Input("Reoler:", integerValidator);
    private final Input inputHylder = new Input("Hylder pr Reol:", integerValidator);

    private final CreateButton btnCreate = new CreateButton();


    public RegistrerLagerGUI(String s) {
        super(s);
        GridPane pane = new GridPane();
        selectedProperty().addListener(e -> clearContent());
        initContent(pane);
        setContent(pane);
    }

    private void clearContent() {
        inputNavn.clear();
        inputAdresse.clear();
        inputReoler.clear();
        inputHylder.clear();
    }


    public void initContent(GridPane pane) {
//        pane.setGridLinesVisible(true);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(15));
        pane.setHgap(15);
        pane.setVgap(10);

        Label lblOpretLager = new Label("Opret Lager");
        lblOpretLager.setStyle("-fx-font-weight: bold");
        GridPane.setHalignment(lblOpretLager, HPos.CENTER);
        pane.add(lblOpretLager, 0, 0);
        pane.add(inputNavn, 0, 1);
        inputNavn.setWidth(80);
        pane.add(inputAdresse, 0, 2);
        inputAdresse.setWidth(80);
        pane.add(inputReoler, 0, 3);
        inputReoler.setWidth(80);
        pane.add(inputHylder, 0, 4);
        inputHylder.setWidth(80);
        pane.add(btnCreate, 0, 5);
        inputNavn.addObserver(btnCreate);
        inputAdresse.addObserver(btnCreate);
        inputReoler.addObserver(btnCreate);
        inputHylder.addObserver(btnCreate);
        GridPane.setHalignment(btnCreate, HPos.CENTER);
        btnCreate.setOnAction(e -> opretLager());
    }

    public void opretLager() {
        Lager lager = new Lager(inputNavn.getText(), inputAdresse.getText(), inputReoler.getTextAsInt(), inputHylder.getTextAsInt());
        ConfirmationWindow alert = new ConfirmationWindow(lager);
        alert.showAndWait().ifPresent(response -> {
            if (response == alert.getButtonTypes().get(1)) {
                Controller.createLager(inputNavn.getText(), inputAdresse.getText(), inputReoler.getTextAsInt(), inputHylder.getTextAsInt());
                clearContent();
            }
        });
    }
}
