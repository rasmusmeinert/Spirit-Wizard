package GUI;

import Controller.Controller;
import GUI.Components.*;
import Model.Fad;
import Model.MængdePåfyldt;
import Model.NewMake;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PåfyldningsGui extends Application {
    private final List<MængdePåfyldt> valgteNewMakes = new ArrayList<>();
    private final List<NewMake> aktuelleNewMakes = Controller.getAktuelleNewMakes();


    private final Validation mængdeValidation = new MængdeValidation();
    private final Input inputMængde = new Input("Mængde", mængdeValidation);

    private final Input inputMedarbejder = new Input("Medarbejder", new StringValidation());

    private final CheckBox cbxFlytFad = new CheckBox("Flyt Fad?");

    private final InfoBox ibNewMakeInfo = new InfoBox();
    private final InfoBox ibFadInfo = new InfoBox();

    //TODO
    //Hent Aktuelle NewMakes, og Tomme Fade, ikke alle fade og newMakes
    private final Picker<NewMake> pickerNewMakes = new Picker<>(aktuelleNewMakes);
    private final Picker<Fad> pickerFad = new Picker<>(Controller.getFade());

    private final ObjectList<MængdePåfyldt> lvwValgteNewMakes = new ObjectList();

    private final CustomButton btnAddNewMake = new CustomButton("+");
    private final CustomButton btnRemoveNewMake = new CustomButton("-");
    private final CustomButton btnOpret = new CustomButton("Opret");

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

        //==================== New Makes ==============================================//

        Label lblNewMakes = new Label("New Makes");
        lblNewMakes.setStyle("-fx-font-weight: bold");
        pane.add(lblNewMakes, 0, 0);
        pane.add(pickerNewMakes, 0, 1);
        pickerNewMakes.addObserver(ibNewMakeInfo);
        pickerNewMakes.addObserver((Observer) mængdeValidation);
        pane.add(ibNewMakeInfo, 0, 2, 2, 1);
        pane.add(inputMængde, 0, 3);
        inputMængde.addObserver(btnAddNewMake);
        pane.add(btnAddNewMake, 1, 3);
        GridPane.setValignment(btnAddNewMake, VPos.TOP);
        btnAddNewMake.setOnAction(e -> addNewMake());

        //==================== Valgte NewMakes ========================================//

        Label lblValgteNewMakes = new Label("Valgte NewMakes");
        lblValgteNewMakes.setStyle("-fx-font-weight: bold");
        pane.add(lblValgteNewMakes, 2, 0);
        GridPane.setHalignment(lblValgteNewMakes, HPos.CENTER);
        pane.add(lvwValgteNewMakes, 2, 2);
        lvwValgteNewMakes.addObserver(btnRemoveNewMake);
        GridPane.setHalignment(lvwValgteNewMakes, HPos.CENTER);
        pane.add(btnRemoveNewMake, 2, 3);
        GridPane.setValignment(btnRemoveNewMake, VPos.TOP);
        btnRemoveNewMake.setOnAction(e -> removeNewMake());

        //==================== Fade =================================================//

        Label lblFad = new Label("Fad");
        lblFad.setStyle("-fx-font-weight: bold");
        pane.add(lblFad, 0, 4);
        pane.add(pickerFad, 0, 5);
        pickerFad.addObserver(ibFadInfo);
        pane.add(ibFadInfo, 0, 6, 2, 1);

        //============================ Medarbejder / Fadflyt / Opret ===============//

        VBox opretBox = new VBox();
        opretBox.setAlignment(Pos.TOP_CENTER);
        opretBox.setSpacing(15);
        btnOpret.setMinSize(100, 50);
        opretBox.getChildren().addAll(inputMedarbejder, cbxFlytFad, btnOpret);
        pane.add(opretBox, 2, 6);
        btnOpret.setOnAction(e -> createPåfyldning());
    }

    //Fjerne en NewMake fra de valgte Newmakes
    //Todo this also super duper sucks
    private void removeNewMake() {
        MængdePåfyldt valgteNewMake = (MængdePåfyldt) lvwValgteNewMakes.getSelectionModel().getSelectedItem();
        valgteNewMakes.remove(lvwValgteNewMakes.getSelectionModel().getSelectedIndex());
        lvwValgteNewMakes.getItems().setAll(valgteNewMakes);
        aktuelleNewMakes.add(valgteNewMake.getNewMake());
        pickerNewMakes.getItems().setAll(aktuelleNewMakes);
        pickerNewMakes.getSelectionModel().select(0);
    }

    //TODO
    //Måske kan det her gøres MEGET pænere, men lige nu virker det jo
    public void addNewMake() {
        NewMake valgteNewMake = (NewMake) pickerNewMakes.getSelectionModel().getSelectedItem();
        double mængde = Double.parseDouble(inputMængde.getText());
        MængdePåfyldt mængdePåfyldt = Controller.createMængdePåfyldt(valgteNewMake, mængde);
        valgteNewMakes.add(mængdePåfyldt);
        lvwValgteNewMakes.getItems().setAll(valgteNewMakes);


        //Todo this sucks
        aktuelleNewMakes.remove(valgteNewMake);
        pickerNewMakes.getItems().setAll(aktuelleNewMakes);
        pickerNewMakes.getSelectionModel().select(0);

        inputMængde.clear();

    }

    public void createPåfyldning() {
        Fad fad = (Fad) pickerFad.getSelectionModel().getSelectedItem();
        String medarbejder = inputMedarbejder.getText();
        Controller.createPåfyldning(medarbejder, LocalDateTime.now(), fad, valgteNewMakes);
        System.out.println(Controller.getPåfyldninger());
    }
}
