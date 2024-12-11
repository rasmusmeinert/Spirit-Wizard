package GUI;

import Controller.Controller;
import GUI.Components.*;
import GUI.Components.Validations.MængdeValidation;
import GUI.Components.Validations.StringValidation;
import GUI.Components.Validations.Validation;
import Model.Fad;
import Model.MængdePåfyldt;
import Model.NewMake;
import Model.Påfyldning;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class PåfyldningsGui extends Tab implements Observer {
    private final Validation mængdeValidation = new MængdeValidation();
    private final Input inputMængde = new Input("Mængde:", mængdeValidation);

    private final Input inputMedarbejder = new Input("Medarbejder:", new StringValidation());

    private final CheckBox cbxFlytFad = new CheckBox("Flyt Fad?");

    private final InfoBox ibNewMakeInfo = new InfoBox();
    private final InfoBox ibFadInfo = new InfoBox();

    private final Picker<NewMake> pickerNewMakes = new Picker<>(Controller.getAktuelleNewMakes(), new MængdeUpdater());
    private final Picker<Fad> pickerFad = new Picker<>(Controller.getTommeFade());

    private final Validation fadValidation = new MængdeValidation();
    private final ObjectListWithMessage<MængdePåfyldt> olValgteNewMakes = new ObjectListWithMessage<MængdePåfyldt>(fadValidation);

    private final CustomButton btnAddNewMake = new CustomButton("+");
    private final CustomButton btnRemoveNewMake = new CustomButton("-");
    private final CreateButton btnOpret = new CreateButton();


    public PåfyldningsGui(String s) {
        super(s);
        GridPane pane = new GridPane();
        initContent(pane);
        selectedProperty().addListener(e -> clearContent());
        setContent(pane);
        Controller.addObserver(this);
    }

    private void clearContent() {
        inputMængde.clear();
        inputMedarbejder.clear();
        olValgteNewMakes.getItems().clear();
        cbxFlytFad.setSelected(false);
        pickerFad.getSelectionModel().select(0);
        pickerNewMakes.getSelectionModel().select(0);
    }

    public void initContent(GridPane pane) {
        pane.setPadding(new Insets(15));
        pane.setHgap(15);
        pane.setVgap(15);
        pane.setAlignment(Pos.BASELINE_CENTER);

        //==================== New Makes ==============================================//

        Label lblNewMakes = new Label("New Makes");
        lblNewMakes.setStyle("-fx-font-weight: bold");
        pickerNewMakes.addObserver(ibNewMakeInfo);
        pickerNewMakes.addObserver((Observer) mængdeValidation);
        pickerNewMakes.addObserver(inputMængde);
        inputMængde.addObserver(btnAddNewMake);
        btnAddNewMake.setOnAction(e -> addNewMake());
        HBox mængdeBox = new HBox(inputMængde,btnAddNewMake);
        VBox newMakesBox = new VBox(lblNewMakes,pickerNewMakes,ibNewMakeInfo,mængdeBox);
        mængdeBox.setSpacing(15);
        newMakesBox.setSpacing(15);
        pane.add(newMakesBox,0,0);

        //==================== Valgte NewMakes ========================================//

        Label lblValgteNewMakes = new Label("Valgte NewMakes");
        lblValgteNewMakes.setStyle("-fx-font-weight: bold");
        ComboBox usynligComboBox = new ComboBox();
        usynligComboBox.setVisible(false);
        olValgteNewMakes.addObserver(btnRemoveNewMake);
        olValgteNewMakes.addObserver(pickerNewMakes);
        olValgteNewMakes.addObserver(btnOpret);
        btnRemoveNewMake.setOnAction(e -> removeNewMake());
        HBox removeBtnBox = new HBox(btnRemoveNewMake);
        VBox valgteNewMakesBox = new VBox(lblValgteNewMakes,usynligComboBox,olValgteNewMakes,removeBtnBox);
        valgteNewMakesBox.setSpacing(15);
        olValgteNewMakes.setSpacing(-17);
        pane.add(valgteNewMakesBox,1,0);


        //=================== Separators ==============================================//

        Separator horizontalSeparator = new Separator();
        horizontalSeparator.setPrefWidth(300);
        pane.add(horizontalSeparator,0,1,2,1);

        //==================== Fade =================================================//

        Label lblFad = new Label("Fad");
        lblFad.setStyle("-fx-font-weight: bold");
        pane.add(lblFad, 0, 2);
        pane.add(pickerFad, 0, 3);
        pickerFad.addObserver(ibFadInfo);
        pickerFad.addObserver((Observer) fadValidation);
        pickerFad.addObserver(olValgteNewMakes);
        pane.add(ibFadInfo, 0, 4, 2, 1);

        //============================ Medarbejder / Fadflyt / Opret ===============//

        VBox opretBox = new VBox();
        opretBox.setAlignment(Pos.TOP_CENTER);
        opretBox.setSpacing(15);
        opretBox.getChildren().addAll(inputMedarbejder, cbxFlytFad, btnOpret);
        inputMedarbejder.addObserver(btnOpret);
        pane.add(opretBox, 1, 4);
        btnOpret.setOnAction(e -> createPåfyldning());
    }

    //Fjerne en NewMake fra de valgte Newmakes
    private void removeNewMake() {
        olValgteNewMakes.removeSelectedItem();
    }

    //Tilføj en newMake til valgte newMakes, med mængde
    //Todo put ind i ObjectList??
    public void addNewMake() {
        NewMake valgteNewMake = (NewMake) pickerNewMakes.getSelectionModel().getSelectedItem();
        double mængde = Double.parseDouble(inputMængde.getText());
        MængdePåfyldt mængdePåfyldt = Controller.createMængdePåfyldt(valgteNewMake, mængde);
        olValgteNewMakes.getItems().add(mængdePåfyldt);
        inputMængde.clear();

    }


    public void createPåfyldning() {
        Fad fad = (Fad) pickerFad.getSelectionModel().getSelectedItem();
        String medarbejder = inputMedarbejder.getText();
        ArrayList<MængdePåfyldt> valgteNewMakes = olValgteNewMakes.getAllItems();
        Påfyldning påfyldning = new Påfyldning(medarbejder, LocalDate.now(), fad, valgteNewMakes);
        ConfirmationWindow alert = new ConfirmationWindow(påfyldning);
        alert.showAndWait().ifPresent(response -> {
            if (response == alert.getButtonTypes().get(1)) {
                Controller.createPåfyldning(medarbejder, LocalDate.now(), fad, valgteNewMakes);
                clearContent();
            }
        });
    }

    @Override
    public void update(Object message) {
        pickerNewMakes.getItems().setAll(Controller.getAktuelleNewMakes());
        pickerFad.getItems().setAll(Controller.getTommeFade());
    }
}
