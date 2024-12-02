package GUI;

import Controller.Controller;
import GUI.Components.InfoBox;
import GUI.Components.Input;
import GUI.Components.ObjectList;
import GUI.Components.Picker;
import Model.Fad;
import Model.MængdePåfyldt;
import Model.NewMake;
import Storage.ListStorage;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PåfyldningsGui extends Application {
    private final List<MængdePåfyldt> valgteNewMakes = new ArrayList<>();

    private final Input inputMængde = new Input("Mængde");
    private final Input inputMedarbejder = new Input("Medarbejder");

    private final CheckBox cbxFlytFad = new CheckBox("Flyt Fad?");

    private final InfoBox ibNewMakeInfo = new InfoBox();
    private final InfoBox ibFadInfo = new InfoBox();

    private final Picker<NewMake> pickerNewMakes = new Picker<>();
    private final Picker<Fad> pickerFad = new Picker<>();

    private final ObjectList<MængdePåfyldt> lvwValgteNewMakes = new ObjectList();

    private final Button btnAddNewMake = new Button("+");
    private final Button btnRemoveNewMake = new Button("-");
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

        //==================== New Makes ==============================================//

        Label lblNewMakes = new Label("New makes");
        pane.add(lblNewMakes, 0, 0);
        pane.add(pickerNewMakes, 0, 1);
        pickerNewMakes.getItems().setAll(Controller.getNewMakes());
        pickerNewMakes.addObserver(ibNewMakeInfo);
        pane.add(ibNewMakeInfo, 0, 2, 2, 1);
        pane.add(inputMængde, 0, 3);
        pane.add(btnAddNewMake, 1, 3);
        btnAddNewMake.setOnAction(e -> addNewMake());

        Label lblValgteNewMakes = new Label("Valgte new makes");
        pane.add(lblValgteNewMakes, 2, 0);
        GridPane.setHalignment(lblValgteNewMakes, HPos.CENTER);
        pane.add(lvwValgteNewMakes, 2, 2);
        GridPane.setHalignment(lvwValgteNewMakes, HPos.CENTER);
        pane.add(btnRemoveNewMake,2,3);
        btnRemoveNewMake.setOnAction(e -> removeNewMake());

        //==================== Fade =================================================//

        Label lblFad = new Label("Ledige fad");
        pane.add(lblFad, 0, 4);
        pane.add(pickerFad, 0, 5);
        pickerFad.addObserver(ibFadInfo);
        pane.add(ibFadInfo, 0, 6, 2, 1);

        //============================ Medarbejder / Fadflyt / Opret ===============//

        VBox opretBox = new VBox();
        opretBox.setAlignment(Pos.TOP_CENTER);
        opretBox.setSpacing(35);
        btnOpret.setMinSize(100, 50);
        pickerFad.getItems().setAll(Controller.getFade());
        opretBox.getChildren().addAll(inputMedarbejder, cbxFlytFad, btnOpret);
        pane.add(opretBox, 2, 6);
        btnOpret.setOnAction(e -> createPåfyldning());
    }

    //TODO
    // Virker ikke ordenligt endnu
    private void removeNewMake() {
        if (!valgteNewMakes.isEmpty()){
            valgteNewMakes.remove(lvwValgteNewMakes.getSelectionModel().getSelectedIndex());
            lvwValgteNewMakes.getItems().setAll(valgteNewMakes);
        }
    }

    //TODO
    //Måske kan det her gøres pænere
    public void addNewMake() {
        NewMake valgteNewMake = (NewMake) pickerNewMakes.getSelectionModel().getSelectedItem();
        boolean valgtFør = false;
        for (MængdePåfyldt mængdePåfyldt : valgteNewMakes) {
            if (valgteNewMake.equals(mængdePåfyldt.getNewMake())) {
                valgtFør = true;
            }
        }
        if (!valgtFør) {
            double mængde = Double.parseDouble(inputMængde.getTextField().getText());
            MængdePåfyldt mængdePåfyldt = Controller.createMængdePåfyldt(valgteNewMake,mængde);
            valgteNewMakes.add(mængdePåfyldt);
            lvwValgteNewMakes.getItems().setAll(valgteNewMakes);
        }
    }

    public void createPåfyldning(){
        Fad fad = (Fad) pickerFad.getSelectionModel().getSelectedItem();
        String medarbejder = inputMedarbejder.getTextField().getText();
        Controller.createPåfyldning(medarbejder, LocalDate.now(),fad,valgteNewMakes);
        System.out.println(Controller.getPåfyldninger());
    }
}
