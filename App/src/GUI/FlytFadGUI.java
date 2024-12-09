package GUI;

import Controller.Controller;
import GUI.Components.CreateButton;
import GUI.Components.InfoBox;
import GUI.Components.Input;
import GUI.Components.Picker;
import GUI.Components.Validations.IntegerValidation;
import GUI.Components.Validations.IntegerValidationWithMax;
import Model.Fad;
import Model.Lager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

public class FlytFadGUI extends Tab {
    private final IntegerValidationWithMax hylderValidation = new IntegerValidationWithMax("Hylder");
    private final IntegerValidationWithMax reolerValidation = new IntegerValidationWithMax("Reoler");
    private final Input inputReol = new Input("Reol", reolerValidation);
    private final Input inputHylde = new Input("Hylde", hylderValidation);

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
        pickerLager.addObserver(hylderValidation);
        pickerLager.addObserver(reolerValidation);

        pane.add(inputReol,1,5);
        pane.add(inputHylde,1,6);
        inputReol.addObserver(btnFlyt);
        inputHylde.addObserver(btnFlyt);
        pane.add(btnFlyt,1,7);
    }
}
