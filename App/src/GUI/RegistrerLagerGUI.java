package GUI;

import Controller.Controller;
import GUI.Components.*;
import GUI.Components.Validations.*;
import Model.Lager;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegistrerLagerGUI extends Application {
    private final Validation integerValidator = new IntegerValidation();
    private final Input inputNavn = new Input("Navn", new StringValidation());
    private final Input inputAdresse = new Input("Adresse", new AdresseValidation());
    private final Input inputReoler = new Input("Reoler", integerValidator);
    private final Input inputHylder = new Input("Hylder pr Reol", integerValidator);

    private final CreateButton btnCreate = new CreateButton();

    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMinHeight(650);
        stage.setMinWidth(650);
        stage.show();
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
        pane.add(btnCreate,0,5);
        inputNavn.addObserver(btnCreate);
        inputAdresse.addObserver(btnCreate);
        inputReoler.addObserver(btnCreate);
        inputHylder.addObserver(btnCreate);
        GridPane.setHalignment(btnCreate,HPos.CENTER);
        btnCreate.setOnAction(e -> opretLager());
    }

    public void opretLager(){
        Lager lager = Controller.createLager(inputNavn.getText(), inputAdresse.getText(),inputReoler.getTextAsInt(),inputHylder.getTextAsInt());
        System.out.println(lager);
    }
}
