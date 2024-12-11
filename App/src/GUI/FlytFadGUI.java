package GUI;

import Controller.Controller;
import GUI.Components.*;
import GUI.Components.Validations.HyldeValidation;
import GUI.Components.Validations.IntegerValidation;
import GUI.Components.Validations.IntegerValidationWithMax;
import GUI.Components.Validations.ReolValidation;
import Model.Fad;
import Model.Lager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

public class FlytFadGUI extends Tab implements Observer {
    private final ReolValidation reolValidation = new ReolValidation();
    private final HyldeValidation hyldeValidation = new HyldeValidation();
    private final Input inputReol = new Input("Reol: ", reolValidation);
    private final Input inputHylde = new Input("Hylde: ", hyldeValidation);

    private final Picker<Fad> pickerFad = new Picker<>(Controller.getFade());
    private final Picker<Lager> pickerLager = new Picker<>(Controller.getLagere());

    private final InfoBox ibFad = new InfoBox();
    private final InfoBox ibLager = new InfoBox();

    private final CreateButton btnFlyt = new CreateButton("Flyt Fad");

    public FlytFadGUI(String s) {
        super(s);
        GridPane pane = new GridPane();
        selectedProperty().addListener(e -> clearContent());
        initContent(pane);
        setContent(pane);
        Controller.addObserver(this);
    }

    private void clearContent() {
        inputHylde.clear();
        inputReol.clear();
        pickerFad.getSelectionModel().select(0);
        pickerLager.getSelectionModel().select(0);
    }

    public void initContent(GridPane pane){
//        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(15));
        pane.setHgap(15);
        pane.setVgap(15);
        pane.setAlignment(Pos.BASELINE_CENTER);

        //============================ Fad ============================

        Label lblFad = new Label("Fad");
        lblFad.setStyle("-fx-font-weight: bold");
        pane.add(lblFad,0,0);
        pane.add(pickerFad,0,1);
        pane.add(ibFad,0,2);
        pickerFad.addObserver(ibFad);

        //============================= Lager ============================

        Label lblLager = new Label("Ny Placering");
        lblLager.setStyle("-fx-font-weight: bold");
        pane.add(lblLager,0,3);
        pane.add(pickerLager,0,4);
        pane.add(ibLager,0,5,1,2);
        pickerLager.addObserver(ibLager);
        pickerLager.addObserver(hyldeValidation);
        pickerLager.addObserver(reolValidation);

        pane.add(inputReol,1,5);
        inputReol.setWidth(40);
        pane.add(inputHylde,1,6);
        inputHylde.setWidth(40);
        inputHylde.addObserver(inputReol);
        inputReol.addObserver(btnFlyt);
        inputHylde.addObserver(btnFlyt);
        inputReol.addObserver(hyldeValidation);
//        inputHylde.addObserver(inputReol);

        btnFlyt.setOnAction(e -> flytFad());
        pane.add(btnFlyt,1,7);

    }

    private void flytFad() {
        Fad fad = (Fad) pickerFad.getSelectionModel().getSelectedItem();
        Lager lager = (Lager) pickerLager.getSelectionModel().getSelectedItem();
        Controller.flytFad(fad, inputReol.getTextAsInt(), inputHylde.getTextAsInt(), lager);
        inputReol.clear();
        inputHylde.clear();
        update(null);
    }

    @Override
    public void update(Object message) {
        pickerFad.getItems().setAll(Controller.getFade());
        pickerFad.getSelectionModel().select(0);
        pickerLager.getItems().setAll(Controller.getLagere());
        pickerLager.getSelectionModel().select(0);
    }
}
