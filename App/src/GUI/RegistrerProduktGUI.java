package GUI;

import Controller.Controller;
import GUI.Components.*;
import GUI.Components.DynamicLabels.AlderLabel;
import GUI.Components.DynamicLabels.AntalFlaskerLabel;
import GUI.Components.DynamicLabels.TypeLabel;
import GUI.Components.Validations.*;
import GUI.Components.Validations.MængdeValidation;
import GUI.Components.Validations.Validation;
import GUI.Components.CreateButton;
import Model.Påfyldning;
import Model.WhiskyProdukt;
import Model.Tapning;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RegistrerProduktGUI extends Tab implements Observer {
    private final Validation mængdeValidation = new MængdeValidation();
    private final Input inputMængde = new Input("Mængde:", mængdeValidation);
    private final Input inputFortynding = new Input("Fortynding (Liter):", new NumberValidationWithZero());
    private final Input inputAlkoholProcent = new Input("ABV (%):", new NumberValidation());
    private final Input inputFlaskeStørrelse = new Input("Flaskestørrelse (Liter): ", new NumberValidation());
    private final Input inputNavn = new Input("Navn:", new AdresseValidation());
    private final InfoBox påfyldningsInfo = new InfoBox();
    private final Picker<Påfyldning> pickerPåfyldninger = new Picker<>(Controller.getTapbarePåfyldninger(), new PåfyldningsUpdater());
    private final ObjectListWithMessage<Tapning> lvwValgtePåfyldninger = new ObjectListWithMessage<>();
    private final TextArea txtABeskerivelse = new TextArea();
    private final CustomButton btnAddPåfyldning = new CustomButton("+");
    private final CustomButton btnRemovePåfyldning = new CustomButton("-");
    private final CreateButton btnOpret = new CreateButton("Opret");
    private final TypeLabel lblType = new TypeLabel("Type: ");
    private final AlderLabel lblAlder = new AlderLabel("Alder: ");
    private final AntalFlaskerLabel lblAntalFlakser = new AntalFlaskerLabel("Antal flasker: ", inputFlaskeStørrelse.getLabelText(), inputFortynding.getLabelText());

    public RegistrerProduktGUI(String s) {
        super(s);
        GridPane pane = new GridPane();
        selectedProperty().addListener(e -> clearContent());
        initContent(pane);
        setContent(pane);
        Controller.addObserver(this);
    }

    private void clearContent() {
        inputFlaskeStørrelse.clear();
        inputAlkoholProcent.clear();
        inputFortynding.clear();
        inputMængde.clear();
        inputNavn.clear();
        txtABeskerivelse.clear();
        lvwValgtePåfyldninger.getItems().clear();
        pickerPåfyldninger.getSelectionModel().select(0);
    }

    public void initContent(GridPane pane) {
        pane.setPadding(new Insets(15));
        pane.setAlignment(Pos.BASELINE_CENTER);
        pane.setHgap(15);
        pane.setVgap(15);

        //==================== Påfyldninger/Fade ==============================================//

        Label lblFade = new Label("Fade");
        lblFade.setStyle("-fx-font-weight: bold");
        pickerPåfyldninger.addObserver(påfyldningsInfo);
        pickerPåfyldninger.addObserver((Observer) mængdeValidation);
        pickerPåfyldninger.addObserver(inputMængde);
        HBox mængdeBox = new HBox(inputMængde, btnAddPåfyldning);
        mængdeBox.setSpacing(15);
        inputMængde.addObserver(btnAddPåfyldning);
        btnAddPåfyldning.setOnAction(e -> addPåfyldning());

        VBox fadeBox = new VBox(lblFade, pickerPåfyldninger, påfyldningsInfo, mængdeBox);
//        fadeBox.setPadding(new Insets(15));
        fadeBox.setSpacing(15);
        pane.add(fadeBox, 0, 0);


        //====================== Valgte fade ==================

        Label lblValgteFade = new Label("Valgte fade");
        lblValgteFade.setStyle("-fx-font-weight: bold");
        ComboBox usynligComboBox = new ComboBox();
        usynligComboBox.setVisible(false);
        lvwValgtePåfyldninger.addObserver(pickerPåfyldninger);
        lvwValgtePåfyldninger.addObserver(btnRemovePåfyldning);
        lvwValgtePåfyldninger.addObserver(btnOpret);
        lvwValgtePåfyldninger.addObserver(lblType);
        lvwValgtePåfyldninger.addObserver(lblAlder);
        lvwValgtePåfyldninger.addObserver(lblAntalFlakser);
        lvwValgtePåfyldninger.addObserver(btnOpret);

        btnRemovePåfyldning.setOnAction(e -> removePåfyldning());
        HBox removeBtnBox = new HBox(btnRemovePåfyldning);
        VBox valgteFadeBox = new VBox(lblValgteFade, usynligComboBox, lvwValgtePåfyldninger, removeBtnBox);
        valgteFadeBox.setSpacing(15);
        lvwValgtePåfyldninger.setSpacing(-17);
        pane.add(valgteFadeBox, 1, 0);


        //=================== Inputs og seperators ===========================//

        Separator horizontalSeparator = new Separator();
        horizontalSeparator.setPrefWidth(300);
        pane.add(horizontalSeparator, 0, 1, 2, 1);
        int inputWidth = 70;

        inputFortynding.addObserver(lblType);
        inputFortynding.addObserver(lblAntalFlakser);
        inputFortynding.setTextFieldPrefWidth(inputWidth);

        inputAlkoholProcent.addObserver(btnOpret);
        inputAlkoholProcent.addObserver(btnOpret);
        inputAlkoholProcent.setTextFieldPrefWidth(inputWidth);

        inputFlaskeStørrelse.setTextFieldPrefWidth(inputWidth);
        inputFlaskeStørrelse.addObserver(lblAntalFlakser);
        inputFlaskeStørrelse.addObserver(btnOpret);

        HBox inputBox = new HBox(inputFlaskeStørrelse, inputFortynding, inputAlkoholProcent);

        pane.add(inputBox, 0, 2, 2, 1);
        inputBox.setSpacing(15);

        Label lblBeskrivelse = new Label("Beskrivelse:");

        VBox navnBeskrivelseBox = new VBox(inputNavn, lblBeskrivelse, txtABeskerivelse);

        inputNavn.setAlignment(Pos.BASELINE_LEFT);
        inputNavn.addObserver(btnOpret);

        txtABeskerivelse.setMaxHeight(75);
        txtABeskerivelse.setMaxWidth(300);
        pane.add(navnBeskrivelseBox, 0, 6, 1, 2);

        Separator horizontalSeparator2 = new Separator();
        horizontalSeparator2.setPrefWidth(300);
        pane.add(horizontalSeparator2, 0, 3, 2, 1);


        //=========================== Produktinformation via dynamiske labels ===============================//
        Label lblProduktInformation = new Label("Produktinformation");
        lblProduktInformation.setStyle("-fx-font-weight: bold;");
        pane.add(lblProduktInformation, 0, 4);
        HBox dynamiskeLabelsBox = new HBox(lblAntalFlakser, lblType, lblAlder);
        dynamiskeLabelsBox.setSpacing(60);
        pane.add(dynamiskeLabelsBox, 0, 5, 2, 1);

        btnOpret.setAlignment(Pos.CENTER);
        btnOpret.setOnAction(e -> opretWhiskyProdukt());
        pane.add(btnOpret, 1, 6);
        GridPane.setHalignment(btnOpret, HPos.CENTER);
    }

    public void addPåfyldning() {
        Tapning tapning = new Tapning(inputMængde.getTextAsDouble(), (Påfyldning) pickerPåfyldninger.getSelectionModel().getSelectedItem());
        lvwValgtePåfyldninger.getItems().add(tapning);
        inputMængde.clear();

    }

    private void removePåfyldning() {
        lvwValgtePåfyldninger.removeSelectedItem();
    }

    @Override
    public void update(Object message) {
        pickerPåfyldninger.getItems().setAll(Controller.getTapbarePåfyldninger());
    }


    public void opretWhiskyProdukt() {
        ArrayList<Tapning> tapninger = new ArrayList<>(lvwValgtePåfyldninger.getItems());
        WhiskyProdukt whiskyProdukt = new WhiskyProdukt(inputNavn.getText(), inputAlkoholProcent.getTextAsDouble(), inputFlaskeStørrelse.getTextAsDouble(), txtABeskerivelse.getText(), inputFortynding.getTextAsDouble(), tapninger);
        ConfirmationWindow alert = new ConfirmationWindow(whiskyProdukt);
        alert.showAndWait().ifPresent(response -> {
            if (response == alert.getButtonTypes().get(1)) {
                Controller.createWhiskyProdukt(inputNavn.getText(), inputAlkoholProcent.getTextAsDouble(), inputFlaskeStørrelse.getTextAsDouble(), txtABeskerivelse.getText(), inputFortynding.getTextAsDouble(), tapninger);
                clearContent();
            }
        });
    }
}

