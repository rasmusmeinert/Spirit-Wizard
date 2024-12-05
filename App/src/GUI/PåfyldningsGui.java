package GUI;

import Controller.Controller;
import GUI.Components.*;
import GUI.Components.Validations.MængdeValidation;
import GUI.Components.Validations.StringValidation;
import GUI.Components.Validations.Validation;
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

import java.time.LocalDate;

public class PåfyldningsGui extends Application {
    private final Validation mængdeValidation = new MængdeValidation();
    private final Input inputMængde = new Input("Mængde", mængdeValidation);

    private final Input inputMedarbejder = new Input("Medarbejder", new StringValidation());

    private final CheckBox cbxFlytFad = new CheckBox("Flyt Fad?");

    private final InfoBox ibNewMakeInfo = new InfoBox();
    private final InfoBox ibFadInfo = new InfoBox();

    private final Picker<NewMake> pickerNewMakes = new Picker<>(Controller.getAktuelleNewMakes(), new MængdeUpdater());
    private final Picker<Fad> pickerFad = new Picker<>(Controller.getTommeFade());

    private final Validation fadValidation = new MængdeValidation();
    private final ObjectListWithMessage<MængdePåfyldt> olValgteNewMakes = new ObjectListWithMessage<>(fadValidation);

    private final CustomButton btnAddNewMake = new CustomButton("+");
    private final CustomButton btnRemoveNewMake = new CustomButton("-");
    private final CreateButton btnOpret = new CreateButton();

    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        initContent(pane);
        pane.setGridLinesVisible(true);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
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
        pickerNewMakes.addObserver(inputMængde);
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
        pane.add(olValgteNewMakes, 2, 2 , 1, 2);
        olValgteNewMakes.addObserver(btnRemoveNewMake);
        olValgteNewMakes.addObserver(pickerNewMakes);
        olValgteNewMakes.addObserver(btnOpret);
        GridPane.setHalignment(olValgteNewMakes, HPos.CENTER);
        GridPane.setValignment(olValgteNewMakes, VPos.TOP);
        pane.add(btnRemoveNewMake, 2, 3);
        GridPane.setValignment(btnRemoveNewMake, VPos.TOP);
        btnRemoveNewMake.setOnAction(e -> removeNewMake());

        //==================== Fade =================================================//

        Label lblFad = new Label("Fad");
        lblFad.setStyle("-fx-font-weight: bold");
        pane.add(lblFad, 0, 4);
        pane.add(pickerFad, 0, 5);
        pickerFad.addObserver(ibFadInfo);
        pickerFad.addObserver((Observer) fadValidation);
        pickerFad.addObserver(olValgteNewMakes);
        pane.add(ibFadInfo, 0, 6, 2, 1);

        //============================ Medarbejder / Fadflyt / Opret ===============//

        VBox opretBox = new VBox();
        opretBox.setAlignment(Pos.TOP_CENTER);
        opretBox.setSpacing(15);
        opretBox.getChildren().addAll(inputMedarbejder, cbxFlytFad, btnOpret);
        inputMedarbejder.addObserver(btnOpret);
        pane.add(opretBox, 2, 6);
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
        Controller.createPåfyldning(medarbejder, LocalDate.now(), fad, olValgteNewMakes.getItems());
        System.out.println(Controller.getPåfyldninger());
    }
}
