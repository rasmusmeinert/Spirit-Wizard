package GUI.Components.DynamicLabels;


import GUI.Components.Observer;
import javafx.scene.control.Label;

public abstract class DynamicLabel extends Label implements Observer {
    String labelName;
    public DynamicLabel(String text) {
        super(text);
        this.labelName = text;
    }

}
