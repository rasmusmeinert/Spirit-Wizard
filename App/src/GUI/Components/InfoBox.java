package GUI.Components;


import Model.Printable;
import javafx.scene.control.TextArea;

public class InfoBox extends TextArea implements Observer {
    public InfoBox() {
        setMaxWidth(300);
        setMaxHeight(150);
        setEditable(false);
        setMouseTransparent(true);
    }


    @Override
    public void update(Object message) {
        if (message == null){
            setText("");
        } else {
            Printable printableMessage = (Printable) message;
            setText(printableMessage.print());
        }
    }
}
