package GUI.Components;

import javafx.collections.ListChangeListener;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class ObjectList<T> extends ListView {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyButtons(boolean disable) {
        for (Observer observer : observers) {
            if (observer.getClass().equals(CustomButton.class)) {
                observer.update(disable);
            }
        }
    }

    public void notifyPickers() {
        for (Observer observer : observers) {
            if (observer.getClass().equals(Picker.class)) {
                observer.update(getItems());
            }
        }
    }


    public ObjectList() {
        setMaxWidth(200);
        setMaxHeight(150);
        getSelectionModel().selectedItemProperty().addListener(e -> selectionChange());
        getItems().addListener((ListChangeListener) e -> notifyPickers());
    }

    public void selectionChange() {
        if (getSelectionModel().getSelectedItem() != null) {
            notifyButtons(false);
        } else {
            notifyButtons(true);
        }
    }

    public void listChange(){
        notifyPickers();
        getSelectionModel().select(0);
    }
}
