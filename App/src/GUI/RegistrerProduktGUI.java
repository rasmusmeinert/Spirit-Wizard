package GUI;

import Controller.Controller;
import GUI.Components.*;
import Model.Påfyldning;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegistrerProduktGUI extends Application {
    private final Validation MængdeValidation = new MængdeValidation();
    private final Validation StringValidation = new StringValidation();
    private final Validation NumberValidation = new NumberValidation();
    private final Input inputMængde = new Input("Mængde: ", MængdeValidation);
    private final Input inputFortynding = new Input("Fortynding: ", NumberValidation);
    private final Input inputAlkoholProcent = new Input("ABV (%):", NumberValidation);
    private final Input inputFlaskeStørrelse = new Input("Flaskestørrelse (cl):", NumberValidation );
    private final Input inputNavn = new Input("Navn:", StringValidation);
    private final InfoBox påfyldningsInfo = new InfoBox();
    private final Picker<Påfyldning> pickerPåfyldninger = new Picker<>(Controller.getTapbarePåfyldninger(), new PåfyldningsUpdater());
    private final ObjectListWithMessage<Påfyldning> lvwValgtePåfyldninger = new ObjectListWithMessage<>();
    private final TextArea txtABeskerivelse = new TextArea();
    private final CustomButton btnAddPåfyldning = new CustomButton("+");
    private final CustomButton btnRemovePåfyldning = new CustomButton("-");
    private final CreateButton btnOpret = new CreateButton("Opret");

    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void initContent(GridPane pane) {
//        pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(15));
        pane.setHgap(15);
        pane.setVgap(15);

        //==================== Påfyldninger/Fade ==============================================//

        Label lblNewMakes = new Label("Fade");
        pane.add(lblNewMakes, 0, 0);
        pane.add(pickerPåfyldninger, 0, 1);
        pickerPåfyldninger.addObserver(påfyldningsInfo);
        pickerPåfyldninger.addObserver((Observer) MængdeValidation);
        pickerPåfyldninger.addObserver(inputMængde);
        pane.add(påfyldningsInfo, 0, 2, 2, 1);
        pane.add(inputMængde, 0, 3);
        inputMængde.addObserver(btnAddPåfyldning);
        pane.add(btnAddPåfyldning, 1, 3);

        //====================== Valgte fade ==================

        Label lblValgteNewMakes = new Label("Valgte fade");
        pane.add(lblValgteNewMakes, 2, 0);
        GridPane.setHalignment(lblValgteNewMakes, HPos.CENTER);
        pane.add(lvwValgtePåfyldninger, 2, 2);
        lvwValgtePåfyldninger.addObserver(pickerPåfyldninger);
        lvwValgtePåfyldninger.addObserver(btnRemovePåfyldning);
        lvwValgtePåfyldninger.addObserver(btnOpret);
        GridPane.setHalignment(lvwValgtePåfyldninger, HPos.CENTER);
        pane.add(btnRemovePåfyldning, 2, 3);
        btnRemovePåfyldning.setOnAction(e -> removePåfyldning());

        //=================== Fortynding, Alkoholprocent og seperators ===========================//

        Separator horizontalSeparator = new Separator();
        horizontalSeparator.setPrefWidth(300);
        pane.add(horizontalSeparator, 0, 4, 3, 1);

        pane.add(inputFortynding, 0, 5);
        inputFortynding.addObserver(btnOpret);
        pane.add(inputAlkoholProcent, 1, 5);
        inputAlkoholProcent.addObserver(btnOpret);
        btnAddPåfyldning.setOnAction(e -> addPåfyldning());

        Separator horizontalSeparator2 = new Separator();
        horizontalSeparator2.setPrefWidth(300);
        pane.add(horizontalSeparator2, 0, 6, 3, 1);


        //=========================== Produktinformation ===============================//
        Label lblProduktInformation = new Label("Produktinformation");
        lblProduktInformation.setStyle("-fx-font-weight: bold;");
        pane.add(lblProduktInformation, 0, 7);

        pane.add(inputFlaskeStørrelse, 0, 8);
        inputFlaskeStørrelse.addObserver(btnOpret);

        Label lblAntalFlasker = new Label("Antal flasker: ");
        pane.add(lblAntalFlasker, 1, 8);

        Label lblType = new Label("Type: ");
        pane.add(lblType, 2, 8);

        pane.add(inputNavn, 0, 9);
        inputNavn.addObserver(btnOpret);

        Label lblAlder = new Label("Alder: ");
        pane.add(lblAlder, 2, 9);

        Label lblBeskrivelse = new Label("Beskrivelse:");
        pane.add(lblBeskrivelse, 0, 10);
        lblBeskrivelse.setAlignment(Pos.TOP_LEFT);
        pane.add(txtABeskerivelse, 0, 11);


        //============================ Opret ===============//

        //Kan dette simplificeres da der ikke skal være andet sammen med opret knappen?
        pane.add(btnOpret, 2, 10);
        //btnOpret.setOnAction(e -> createPåfyldning());
    }

    public void addPåfyldning() {
        Påfyldning valgtePåfyldning = (Påfyldning) pickerPåfyldninger.getSelectionModel().getSelectedItem();
        lvwValgtePåfyldninger.getItems().add(valgtePåfyldning);
        inputMængde.clear();

    }

    //TODO
    // Virker ikke ordenligt endnu
    private void removePåfyldning() {
        lvwValgtePåfyldninger.removeSelectedItem();
    }
}
