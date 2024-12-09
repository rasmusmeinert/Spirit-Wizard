package GUI.Components;

import Model.Printable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class ConfirmationWindow extends Alert {

    public ConfirmationWindow(Printable object) {
        super(AlertType.CONFIRMATION);
        ButtonType btnBekræft = new ButtonType("Bekræft", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnAnuller = new ButtonType("Anuller", ButtonBar.ButtonData.CANCEL_CLOSE);
        getButtonTypes().setAll(btnAnuller,btnBekræft);
        setHeaderText("Bekræft oprettelse");
        setContentText(object.print());
    }
}
