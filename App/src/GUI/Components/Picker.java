package GUI.Components;

import GUI.Observer;
import Model.Printable;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.List;

public class Picker<T> extends ComboBox {
    private final List<Observer> observers = new ArrayList<>();

    public Picker() {
        this.setOnAction(e -> notifyObservers());
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    private void notifyObservers(){
        for (Observer observer : observers){
            observer.update((Printable) this.getSelectionModel().getSelectedItem());
        }
    }

    //Todo
    //Hold øje med controller, og opdater hvis noget skabes osv.
}
