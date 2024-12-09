package GUI.Components;

import Controller.Controller;
import Model.Fad;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a list that contains info to search for a "Fad"
 * @param <T>
 */
public class SearchList<T> extends ListView implements Observer {
    private final List<Observer> observers = new ArrayList<>();
    private Integer nummer;
    private String træType = "";
    private Double størrelse;
    private String tidligereIndhold = "";
    private boolean isPåfyldt = false;


    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(Object object) {
        observers.forEach(observer -> observer.update(object));
    }


    /**
     * Updates the variables needed for searching, and conducts a search based on those
     * @param message
     */
    public void update(Object message) {
        if (message.getClass().equals(UpdateMessage.class)) {
            UpdateMessage updateMessage = (UpdateMessage) message;
            String messageText = (String) updateMessage.getMessage();
            switch ((String) updateMessage.getObject()) {
                case "Nummer" -> nummer = messageText == null ? null : Integer.parseInt(messageText);
                case "Træ Type" -> træType = messageText;
                case "Størrelse" -> størrelse = messageText == null ? null : Double.parseDouble(messageText);
                case "Tidligere Indhold" -> tidligereIndhold = messageText;
            }

        } else {
            isPåfyldt = (boolean) message;
        }
        search();
    }

    //=============================================================================

    public SearchList() {
        setMinHeight(100);
        getSelectionModel().selectedItemProperty().addListener(e -> selectionChange());
    }

    private void selectionChange() {
        notifyObservers(getSelectionModel().getSelectedItem());
    }

    public void search() {
        List<Fad> søgning = Controller.søgFad(nummer, træType, størrelse, tidligereIndhold, isPåfyldt);
        getItems().setAll(søgning);
    }
}
