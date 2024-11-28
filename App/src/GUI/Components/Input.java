package GUI.Components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Input extends HBox {
    private final Label label = new Label();
    private final TextField textField = new TextField();
    private final Label errorMessage = new Label("");

    public Input(String labelString) {
        this.setSpacing(10);
        this.textField.setMaxWidth(150);
        this.setAlignment(Pos.BASELINE_LEFT);
        label.setText(labelString);
        this.getChildren().addAll(label,textField,errorMessage);
    }

    public TextField getTextField() {
        return textField;
    }
}
