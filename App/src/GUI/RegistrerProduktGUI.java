package GUI;

import Controller.Controller;
import GUI.Components.*;
import Model.Fad;
import Model.MængdePåfyldt;
import Model.NewMake;
import Model.Påfyldning;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistrerProduktGUI extends Application {
    private final List<Påfyldning> valgtePåfyldninger = new ArrayList<>();
    private Validation MængdeValidation;
    private final Input inputMængde = new Input("Mængde: ", MængdeValidation);
    private final InfoBox påfyldningsInfo = new InfoBox();
    private final Picker<Påfyldning> pickerPåfyldninger = new Picker<>(Controller.getPåfyldninger());
    private final ObjectList<MængdePåfyldt> lvwValgtePåfyldninger = new ObjectList();
    private final Button btnAddPåfyldning = new Button("+");
    private final Button btnRemovePåfyldning = new Button("-");
    private final Button btnOpret = new Button("Opret");

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
        pickerPåfyldninger.getItems().setAll(Controller.getTapbarePåfyldninger());
        pickerPåfyldninger.addObserver(påfyldningsInfo);
        pane.add(påfyldningsInfo, 0, 2, 2, 1);
        pane.add(inputMængde, 0, 3);
        pane.add(btnAddPåfyldning, 1, 3);
        //btnAddPåfyldning.setOnAction(e -> addNewMake());

        Label lblValgteNewMakes = new Label("Valgte fade");
        pane.add(lblValgteNewMakes, 2, 0);
        GridPane.setHalignment(lblValgteNewMakes, HPos.CENTER);
        pane.add(lvwValgtePåfyldninger, 2, 2);
        GridPane.setHalignment(lvwValgtePåfyldninger, HPos.CENTER);
        pane.add(btnRemovePåfyldning,2,3);
        btnRemovePåfyldning.setOnAction(e -> removeNewMake());

        //============================ Opret ===============//

        //Kan dette simplificeres da der ikke skal være andet sammen med opret knappen?
        VBox opretBox = new VBox();
        opretBox.setAlignment(Pos.TOP_CENTER);
        opretBox.setSpacing(35);
        btnOpret.setMinSize(100, 50);
        opretBox.getChildren().addAll(btnOpret);
        pane.add(opretBox, 2, 6);
        //btnOpret.setOnAction(e -> createPåfyldning());
    }

    //TODO
    // Virker ikke ordenligt endnu
    private void removeNewMake() {
        if (!valgtePåfyldninger.isEmpty()){
            valgtePåfyldninger.remove(lvwValgtePåfyldninger.getSelectionModel().getSelectedIndex());
            lvwValgtePåfyldninger.getItems().setAll(valgtePåfyldninger);
        }
    }

    //TODO
    //Måske kan det her gøres pænere
//    public void addNewMake() {
//        NewMake valgteNewMake = (NewMake) pickerPåfyldninger.getSelectionModel().getSelectedItem();
//        boolean valgtFør = false;
//        for (MængdePåfyldt mængdePåfyldt : valgtePåfyldninger) {
//            if (valgteNewMake.equals(mængdePåfyldt.getNewMake())) {
//                valgtFør = true;
//            }
//        }
//        if (!valgtFør) {
//            double mængde = Double.parseDouble(inputMængde.getTextField().getText());
//            MængdePåfyldt mængdePåfyldt = Controller.createMængdePåfyldt(valgteNewMake,mængde);
//            valgtePåfyldninger.add(mængdePåfyldt);
//            lvwValgtePåfyldninger.getItems().setAll(valgtePåfyldninger);
//        }
//    }

//    public void createPåfyldning(){
//        Fad fad = (Fad) pickerFad.getSelectionModel().getSelectedItem();
//        String medarbejder = inputMedarbejder.getTextField().getText();
//        Controller.createPåfyldning(medarbejder, LocalDate.now(),fad,valgtePåfyldninger);
//        System.out.println(Controller.getPåfyldninger());
//    }
}
