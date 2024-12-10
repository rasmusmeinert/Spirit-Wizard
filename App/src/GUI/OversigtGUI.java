package GUI;

import Controller.Controller;
import GUI.Components.InfoBox;
import GUI.Components.ObjectListWithMessage;
import GUI.Components.SearchList;
import Model.Fad;
import Model.NewMake;
import Model.WhiskyProdukt;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;

public class OversigtGUI extends Tab {
    private final TabPane tabPane = new TabPane();

    private final InfoBox infoBox = new InfoBox();

    private final SearchList<Fad> olFad = new SearchList<>();
    private final SearchList<NewMake> olNewMake = new SearchList<>();
    private final SearchList<WhiskyProdukt> olWhiskyProdukt = new SearchList<>();



    public OversigtGUI(String s) {
        super(s);
        GridPane pane = new GridPane();
        initContent(pane);
        setContent(pane);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(15));
        pane.setHgap(15);
        pane.setVgap(15);
        pane.setAlignment(Pos.BASELINE_CENTER);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab fadTab = new Tab("Fad");
        olFad.getItems().setAll(Controller.getFade());
        olFad.addObserver(infoBox);
        fadTab.setContent(olFad);

        Tab newMakeTab = new Tab("New Make");
        olNewMake.getItems().setAll(Controller.getNewMakes());
        olNewMake.addObserver(infoBox);
        newMakeTab.setContent(olNewMake);

        Tab produktTab = new Tab ("Produkt");
        olWhiskyProdukt.getItems().setAll(Controller.getWhiskyProukter());
        olWhiskyProdukt.addObserver(infoBox);
        produktTab.setContent(olWhiskyProdukt);


        Label lblInfo = new Label("Info");
        lblInfo.setStyle("-fx-font-weight: bold");
        tabPane.getTabs().setAll(fadTab,newMakeTab,produktTab);
        tabPane.setOnMouseClicked(e -> changeTab());
        infoBox.setMinHeight(300);
        GridPane.setValignment(infoBox, VPos.TOP);
        pane.add(tabPane,0,0,1,2);
        pane.add(lblInfo,1,0);
        pane.add(infoBox,1,1);
    }

    private void changeTab() {
        olFad.getSelectionModel().clearSelection();
        olNewMake.getSelectionModel().clearSelection();
        olWhiskyProdukt.getSelectionModel().clearSelection();
    }
}
