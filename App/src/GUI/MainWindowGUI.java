package GUI;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainWindowGUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Spirit Wizard");
        BorderPane pane = new BorderPane();

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab tab1 = new RegistrerFadGUI("Opret Fad");
        Tab tab2 = new RegistrerNewMakeGUI("Opret NewMake");
        Tab tab3 = new RegistrerLagerGUI("Opret Lager");
        Tab tab4 = new PåfyldningsGui("Opret Påfyldning");
        Tab tab5 = new RegistrerProduktGUI("Opret Whisky Produkt");
        Tab tab6 = new FlytFadGUI("Flyt Fad");
        Tab tab7 = new SøgGUI("Søg Fad");
        tabPane.getTabs().addAll(tab1,tab2,tab3, tab4,tab5,tab6,tab7);

        pane.setCenter(tabPane);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
