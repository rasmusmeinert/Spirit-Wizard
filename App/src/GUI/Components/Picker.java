package GUI.Components;

import Model.Printable;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Picker<T> extends ComboBox {
    private final List<Observer> observers = new ArrayList<>();

    public Picker(Collection list) {
        getItems().setAll(list);
        setOnAction(e -> notifyObservers());
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

    //Todo
    //Hold øje med controller, og opdater hvis noget skabes osv.
}
