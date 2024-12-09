package GUI.Components;

import javafx.scene.control.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class CustomCheckBox extends CheckBox {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void notifyObservers(){
        observers.forEach(observer -> observer.update(isSelected()));
    }


    public CustomCheckBox(String s) {
        super(s);
        selectedProperty().addListener(e -> notifyObservers());
    }
}
