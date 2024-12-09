package GUI;

import Controller.Controller;
import GUI.Components.ConfirmationWindow;
import GUI.Components.CreateButton;
import GUI.Components.Input;
import GUI.Components.Validations.*;
import Model.Fad;
import Model.Lager;
import Model.WhiskyProdukt;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegistrerFadGUI extends Tab {
    private final Input inputNummer = new Input("Nummer: ", new NumberValidation());
    private final Input inputTrætype = new Input("Trætype: ", new StringValidation());
    private final Input inputTidligereIndhold = new Input("Tidligere indhold: ", new StringValidation());
    private final Input inputStørrelse = new Input("Størrelse (liter): ", new NumberValidation());

    private final CreateButton btnCreate = new CreateButton();


    public RegistrerFadGUI(String s) {
        super(s);
        GridPane pane = new GridPane();
        initContent(pane);
        setContent(pane);

    }

    public void clearContent() {
        inputNummer.clear();
        inputStørrelse.clear();
        inputTidligereIndhold.clear();
        inputTrætype.clear();
    }

    public void initContent(GridPane pane) {
//        pane.setGridLinesVisible(true);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(15));
        pane.setHgap(15);
        pane.setVgap(10);
        int labelWidth = 95;

        Label lblRegistrerFad = new Label("Opret Fad");
        lblRegistrerFad.setStyle("-fx-font-weight: bold");
        GridPane.setHalignment(lblRegistrerFad, HPos.CENTER);
        pane.add(lblRegistrerFad, 0, 0);

        pane.add(inputNummer, 0, 1);
        inputNummer.setWidth(labelWidth);
        inputNummer.addObserver(btnCreate);

        pane.add(inputTrætype, 0, 2);
        inputTrætype.setWidth(labelWidth);
        inputTrætype.addObserver(btnCreate);

        pane.add(inputTidligereIndhold, 0, 3);
        inputTidligereIndhold.setWidth(labelWidth);
        inputTidligereIndhold.addObserver(btnCreate);

        pane.add(inputStørrelse, 0, 4);
        inputStørrelse.setWidth(labelWidth);
        inputStørrelse.addObserver(btnCreate);

        pane.add(btnCreate, 0, 5);
        GridPane.setHalignment(btnCreate, HPos.CENTER);
        btnCreate.setOnAction(e -> opretFad());
    }

    public void opretFad() {
        Fad fad = new Fad(inputNummer.getTextAsInt(), inputTrætype.getText(), inputStørrelse.getTextAsDouble(), inputTidligereIndhold.getText());
        ConfirmationWindow alert = new ConfirmationWindow(fad);
        alert.showAndWait().ifPresent(response -> {
            if (response == alert.getButtonTypes().get(1)) {
                Controller.createFad(inputNummer.getTextAsInt(), inputTrætype.getText(), inputStørrelse.getTextAsDouble(), inputTidligereIndhold.getText());
                clearContent();
            }
        });
    }
}
