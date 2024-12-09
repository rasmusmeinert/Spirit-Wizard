package GUI;

import GUI.Components.*;
import GUI.Components.Validations.IntegerValidation;
import GUI.Components.Validations.NumberValidation;
import GUI.Components.Validations.StringValidation;
import Model.Fad;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SøgGUI extends Tab {
    private final Input inputNummer = new Input("Nummer", new IntegerValidation());
    private final Input inputType = new Input("Træ Type", new StringValidation());
    private final Input inputStørrelse = new Input("Størrelse", new NumberValidation());
    private final Input inputIndhold = new Input("Tidligere Indhold", new StringValidation());
    private final CustomCheckBox cbxPåfyldt = new CustomCheckBox("Påfyldt?");

    private final SearchList<Fad> lvwResultater = new SearchList<>();
    private final InfoBox ibFad = new InfoBox();




    public SøgGUI(String s) {
        super(s);
        GridPane pane = new GridPane();
        selectedProperty().addListener(e -> clearContent());
        initContent(pane);
        setContent(pane);
    }

    private void clearContent() {
        inputIndhold.clear();
        inputStørrelse.clear();
        inputType.clear();
        inputNummer.clear();
        cbxPåfyldt.setSelected(false);
    }

    public void initContent(GridPane pane) {
//        pane.setGridLinesVisible(true);
//        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(15));
        pane.setHgap(15);
        pane.setVgap(10);

        Label lblOpretLager = new Label("Søg Fad");
        lblOpretLager.setStyle("-fx-font-weight: bold");
        pane.add(lblOpretLager, 0, 0);
        pane.add(cbxPåfyldt, 0, 1);
        pane.add(inputNummer, 0, 2);
        pane.add(inputType, 0, 3);
        pane.add(inputStørrelse, 0, 4);
        pane.add(inputIndhold, 0, 5);
        int inputWidth = 100;
        inputNummer.setWidth(inputWidth);
        inputType.setWidth(inputWidth);
        inputStørrelse.setWidth(inputWidth);
        inputIndhold.setWidth(inputWidth);
        Label lblResultater = new Label("Resultater");
        lblResultater.setStyle("-fx-font-weight: bold");
        pane.add(lblResultater, 0, 6);
        pane.add(lvwResultater, 0, 7);
        lvwResultater.setMaxHeight(250);

        cbxPåfyldt.addObserver(lvwResultater);
        inputNummer.addObserver(lvwResultater);
        inputType.addObserver(lvwResultater);
        inputStørrelse.addObserver(lvwResultater);
        inputIndhold.addObserver(lvwResultater);

        Label lblFadInfo = new Label("Fad Info");
        lblFadInfo.setStyle("-fx-font-weight: bold");
        pane.add(lblFadInfo, 1, 0);
        pane.add(ibFad,1,1,1,7);
        ibFad.setMinHeight(538);
        ibFad.setMaxWidth(200);

        lvwResultater.addObserver(ibFad);
    }

}
