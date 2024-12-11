package GUI.Components;


import Controller.Controller;
import Model.Fad;
import Model.Printable;
import javafx.scene.control.TextArea;


public class InfoBox extends TextArea implements Observer {
    /**
     * Creates an InfoBox (TextArea) that can observe an object, that sends a Printable object as an message
     */
    public InfoBox() {
        setMaxWidth(300);
        setMaxHeight(150);
        setEditable(false);
        setMouseTransparent(false);
        setWrapText(true);
    }


    @Override
    public void update(Object message) {
        if (message == null) {
            setText("");
        } else if (message.getClass().equals(Fad.class)) {
            StringBuilder sb = new StringBuilder();
            Printable printableFad = (Printable) message;
            sb.append(printableFad.print());
            if (((Fad) message).isPåfyldt()) {
                sb.append("\n \nPåfyldning: \n" + Controller.getPåfyldningByFad((Fad) message).print());
            }
            setText(sb.toString());

        } else {
            Printable printableMessage = (Printable) message;
            setText(printableMessage.print());
        }
    }

    @Override
    protected double computePrefHeight(double v) {
        return super.computePrefHeight(v);
    }
}
