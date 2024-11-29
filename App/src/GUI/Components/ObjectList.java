package GUI.Components;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class ObjectList<T> extends ListView {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void notifyObserver(boolean disable){
        for (Observer observer : observers){
            observer.update(disable);
        }
    }

    public ObjectList() {
        setMaxWidth(200);
        setMaxHeight(150);
        getSelectionModel().selectedItemProperty().addListener(e -> selectionChange());
    }

    public void selectionChange() {
        if (getSelectionModel().getSelectedItem() != null) {
            notifyObserver(false);
        } else {
            notifyObserver(true);
        }
    }
}
