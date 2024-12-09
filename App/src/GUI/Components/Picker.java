package GUI.Components;

import Controller.Controller;
import Model.MængdePåfyldt;
import Model.NewMake;
import Model.Printable;
import Model.Påfyldning;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * A ComboBox, with a ListUpdater, that makes sure the correct items are available.
 * If set to observe a ObjectList it will not contain the items in corresponding Objectlist
 * @param <T>
 */
public class Picker<T> extends ComboBox implements Observer {
    private final List<Observer> observers = new ArrayList<>();
    private ListUpdater listUpdater;

    public Picker(Collection list, ListUpdater listUpdater) {
        getItems().setAll(list);
        setOnAction(e -> onAction());
        getSelectionModel().select(0);
        this.listUpdater = listUpdater;
        onAction();
    }

    public Picker(Collection list) {
        getItems().setAll(list);
        setOnAction(e -> onAction());
        getSelectionModel().select(0);
        onAction();
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
    public void update(Object message) {
        getItems().setAll(listUpdater.update(message));
        onAction();
        getSelectionModel().select(0);
    }

    public void onAction() {
        notifyObservers();
        if (getItems().isEmpty()) {
            setDisable(true);
        } else {
            setDisable(false);
        }
    }
}
