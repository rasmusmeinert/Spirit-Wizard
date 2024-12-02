package GUI.Components;

import Controller.Controller;
import Model.MængdePåfyldt;
import Model.NewMake;
import Model.Printable;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Picker<T> extends ComboBox implements Observer {
    private final List<Observer> observers = new ArrayList<>();

    public Picker(Collection list) {
        getItems().setAll(list);
        setOnAction(e -> onAction());
        getSelectionModel().select(0);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(getSelectionModel().getSelectedItem());
        }
    }

    @Override
    //TODO lav til interface feldt, afhængig af hvilke typer der arbejdes med
    public void update(Object message) {
        List<NewMake> nyListe = new ArrayList<>();
        List<MængdePåfyldt> mængder = (List<MængdePåfyldt>) message;
        for (Object newMake : Controller.getAktuelleNewMakes()) {
            boolean found = false;
            for (MængdePåfyldt mængdePåfyldt : mængder) {
                if (mængdePåfyldt.getNewMake().equals(newMake)) {
                    found = true;
                }
            }
            if (!found) {
                nyListe.add((NewMake) newMake);
            }
        }
        getItems().setAll(nyListe);
        getSelectionModel().select(0);
    }

    public void onAction(){
        notifyObservers();
        if (getItems().isEmpty()){
            setDisable(true);
        } else {
            setDisable(false);
        }
    }
}
