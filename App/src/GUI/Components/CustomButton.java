package GUI.Components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;


/**
 * A button that is disabled and enabled depening on the object it observes
 */
public class CustomButton extends Button implements Observer {

    public CustomButton(String s) {
        super(s);
        setDisable(true);
    }

    @Override
    public void update(Object disable) {
        this.setDisable((Boolean)disable);
    }
}
