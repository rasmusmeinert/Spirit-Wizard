package GUI.Components;


import Controller.Controller;
import Model.Fad;
import Model.Lager;
import Model.Printable;
import Model.Reol;
import javafx.scene.control.Control;
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
            Fad fad = (Fad) message;
            StringBuilder sb = new StringBuilder();
            Printable printableFad = (Printable) message;
            sb.append(printableFad.print());
            if (fad.getReol() != null) {
                Lager lager = Controller.getLagerByFad(fad);
                Reol reol = fad.getReol();
                int hylde = 0;
                for (int i = 0; i < reol.getHylder().length - 1; i++) {
                    System.out.println(i);
                    if (reol.getHylder()[i] != null && reol.getHylder()[i].equals(fad)) {
                        hylde = i;
                    }

                }
                sb.append("\nLager: " + lager.getNavn());
                sb.append("\nReol: " + reol.getNummer());
                sb.append(" Hylde: " + hylde);
            }
            if (fad.isPåfyldt()) {
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
