package GUI.Components;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A button that observes multiple other objects, is enabled if all the observed objects are valid
 */
public class CreateButton extends Button implements Observer {
    Map<Object, Boolean> flags = new HashMap<Object, Boolean>();

    public CreateButton() {
        setMinSize(100, 50);
        setText("Opret");
        setDisable(true);
    }

    public CreateButton(String s) {
        super(s);
        setMinSize(100, 50);
        setDisable(true);
    }

    @Override
    public void update(Object message) {
        UpdateMessage updateMessage = (UpdateMessage) message;

        //Tilføjer et flag per object der sender beskeder til knappen
        flags.put(updateMessage.getObject(), (Boolean) updateMessage.getMessage());
        boolean valid = true;
        //Looper igennem flag, hvis den finder et der ikke er valid, så sluk for knap
        for (boolean flag : flags.values()) {
            if (flag) {
                valid = false;
            }
        }
        setDisable(!valid);
    }
}
