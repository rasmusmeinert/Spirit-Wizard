package GUI.Components;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateButton extends Button implements Observer {
    Map<Object, boolean> flags = new HashMap<Object, boolean>();

    public CreateButton() {
        setMinSize(100, 50);
        setText("Opret");
    }

    public CreateButton(String s) {
        super(s);
        setMinSize(100,50);
    }

    @Override
    public void update(Object message) {
//        boolean valid = true;
//        for (boolean flag : flags){
//            if (!flag){
//                valid = false;
//            }
//        }
//        setDisable(!valid);

    }
}
