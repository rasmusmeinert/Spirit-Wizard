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
import Model.Tapning;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class RegistrerProduktGUI extends Application {
    private final Validation mængdeValidation = new MængdeValidation();
    private final Input inputMængde = new Input("Mængde: ", mængdeValidation);
    private final Input inputFortynding = new Input("Fortynding (Liter): ", new NumberValidationWithZero());
    private final Input inputAlkoholProcent = new Input("ABV (%):", new NumberValidation());
    private final Input inputFlaskeStørrelse = new Input("Flaskestørrelse (Liter): ", new NumberValidation() );
    private final Input inputNavn = new Input("Navn:", new StringValidation());
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

    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        initContent(pane);
        pane.setGridLinesVisible(false);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void initContent(GridPane pane) {
        pane.setPadding(new Insets(15));
        pane.setHgap(15);
        pane.setVgap(15);

        //==================== Påfyldninger/Fade ==============================================//

        Label lblFade = new Label("Fade");
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
        pane.add(valgteFadeBox, 1, 0);

//        lblFade.setStyle("-fx-border-color: red;");
//        pickerPåfyldninger.setStyle("-fx-border-color: blue;");
//        påfyldningsInfo.setStyle("-fx-border-color: green;");
//        mængdeBox.setStyle("-fx-border-color: yellow;");
//
//        lblValgteFade.setStyle("-fx-border-color: red;");
//        usynligComboBox.setStyle("-fx-border-color: blue;");
//        lvwValgtePåfyldninger.setStyle("-fx-border-color: green;");
//        btnRemovePåfyldning.setStyle("-fx-border-color: yellow;");



        //=================== Inputs og seperators ===========================//

        Separator horizontalSeparator = new Separator();
        horizontalSeparator.setPrefWidth(300);
        pane.add(horizontalSeparator, 0, 1, 2, 1);

        inputFortynding.addObserver(lblType);
        inputFortynding.addObserver(lblAntalFlakser);
        inputFortynding.setTextFieldPrefWidth(45);

        inputAlkoholProcent.addObserver(btnOpret);
        inputAlkoholProcent.addObserver(btnOpret);
        inputAlkoholProcent.setTextFieldPrefWidth(45);

        inputFlaskeStørrelse.setTextFieldPrefWidth(45);
        inputFlaskeStørrelse.addObserver(lblAntalFlakser);
        inputFlaskeStørrelse.addObserver(btnOpret);

        HBox inputBox = new HBox(inputFlaskeStørrelse, inputFortynding, inputAlkoholProcent);

        pane.add(inputBox, 0, 2, 2, 1);
        inputBox.setSpacing(15);

        Label lblBeskrivelse = new Label("Beskrivelse:");

        VBox navnBeskrivelseBox = new VBox(inputNavn, lblBeskrivelse, txtABeskerivelse);

        inputNavn.addObserver(btnOpret);

        txtABeskerivelse.setMaxHeight(75);
        txtABeskerivelse.setMaxWidth(300);
        pane.add(navnBeskrivelseBox, 0, 6);

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

//        Label lblBeskrivelse = new Label("Beskrivelse:");
//        pane.add(lblBeskrivelse, 0, 10);
//        lblBeskrivelse.setAlignment(Pos.TOP_LEFT);
//        pane.add(txtABeskerivelse, 0, 11);


        //============================ Opret ===============//

        //Kan dette simplificeres da der ikke skal være andet sammen med opret knappen?
        btnOpret.setAlignment(Pos.CENTER);
        btnOpret.setOnAction(e -> opretWhiskyProdukt());
        pane.add(btnOpret, 1, 6);
        GridPane.setHalignment(btnOpret, HPos.CENTER);
//        btnOpret.setOnAction(e -> createPåfyldning());
    }

    public void addPåfyldning() {
        Tapning tapning = new Tapning(inputMængde.getTextAsDouble(), (Påfyldning) pickerPåfyldninger.getSelectionModel().getSelectedItem());
        lvwValgtePåfyldninger.getItems().add(tapning);
        inputMængde.clear();

    }

    //TODO
    // Virker ikke ordenligt endnu
    private void removePåfyldning() {
        lvwValgtePåfyldninger.removeSelectedItem();
    }
    public void opretWhiskyProdukt() {
        ArrayList<Tapning> tapninger = new ArrayList<>(lvwValgtePåfyldninger.getItems());
        Controller.createWhiskyProdukt(inputNavn.getText(), inputAlkoholProcent.getTextAsDouble(), inputFlaskeStørrelse.getTextAsDouble(), txtABeskerivelse.getText(), inputFortynding.getTextAsDouble(), tapninger);
        lvwValgtePåfyldninger.getItems().clear();
        inputNavn.clear();
        txtABeskerivelse.clear();
    }
}
