package GUI.Components;


import GUI.Observer;
import Model.Printable;
import javafx.scene.control.TextArea;

public class InfoBox extends TextArea implements Observer {
    public InfoBox() {
        this.setMaxWidth(300);
        this.setMaxHeight(200);
        this.setEditable(false);
        this.setMouseTransparent(true);
    }

    @Override
    public void update(Printable printable) {
        this.setText(printable.print());

    }

}
