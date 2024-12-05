package GUI.Components;

import Model.MængdePåfyldt;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;


/**
 * A Listview that holds objects,
 * @param <T>
 */
public class ObjectListWithMessage<T> extends VBox implements Observer {
    private final List<Observer> observers = new ArrayList<>();
    private final ListView<T> listView = new ListView<>();
    private final Label lblErrorMessage = new Label("");
    private Validation validator;


    //====================== Observers =======================================//

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Notify butons that should be disabled if the list is empty
     * @param disable
     */
    public void notifyButtons(boolean disable) {
        for (Observer observer : observers) {
            if (observer.getClass().equals(CustomButton.class)) {
                observer.update(disable);
            }
        }
    }


    /**
     * Notify any create buttons with the ObjectList contains valid objects
     * @param disable
     */
    public void notifyCreateButton(boolean disable) {
        for (Observer observer : observers) {
            if (observer.getClass().equals(CreateButton.class)) {
                observer.update(new UpdateMessage(this, disable));
            }
        }
    }


    /**
     * Notify any observing pickers, so they can change their contents to match this objectlist
     */
    public void notifyPickers() {
        for (Observer observer : observers) {
            if (observer.getClass().equals(Picker.class)) {
                observer.update(listView.getItems());
            }
        }
    }

    //=============================== Update ==============================================//
    @Override
    public void update(Object message) {
        checkIfValid();
    }

    //================================================================================//

    public ObjectListWithMessage(Validation validator) {
        setSpacing(15);
        setMaxWidth(200);
        setMaxHeight(150);
        listView.setMinHeight(150);
        listView.getSelectionModel().selectedItemProperty().addListener(e -> selectionChange());
        listView.getItems().addListener((ListChangeListener) e -> listChange());
        getChildren().addAll(listView, lblErrorMessage);
        this.validator = validator;

        lblErrorMessage.setStyle("-fx-text-fill: red");
        this.setAlignment(Pos.CENTER);
    }

    public ObjectListWithMessage() {
        setSpacing(15);
        setMaxWidth(200);
        setMaxHeight(150);
        listView.setMinHeight(150);
        listView.getSelectionModel().selectedItemProperty().addListener(e -> selectionChange());
        listView.getItems().addListener((ListChangeListener) e -> listChange());
        getChildren().addAll(listView, lblErrorMessage);

        lblErrorMessage.setStyle("-fx-text-fill: red");
        this.setAlignment(Pos.CENTER);
    }


    public void selectionChange() {
        if (listView.getSelectionModel().getSelectedItem() != null) {
            notifyButtons(false);
        } else {
            notifyButtons(true);
        }
    }

    public void listChange() {
        notifyPickers();
        checkIfValid();
        listView.getSelectionModel().select(0);
    }

    //TODO Gad godt at putte det her i et interfache
    public void checkIfValid() {
        if (!getItems().isEmpty()) {
            if (getItems().get(0).getClass().equals(MængdePåfyldt.class)) {
                double totalMængde = 0;
                for (Object object : listView.getItems()) {
                    MængdePåfyldt mængde = (MængdePåfyldt) object;
                    totalMængde += mængde.getMængde();
                }
                if (validator.isValid(String.valueOf(totalMængde))) {
                    lblErrorMessage.setText("");
                    notifyCreateButton(false);
                } else {
                    lblErrorMessage.setText("Samlede mængde for høj");
                    notifyCreateButton(true);
                }
            } else {
                notifyCreateButton(false);
            }
        } else {
            lblErrorMessage.setText("");
            notifyCreateButton(true);

        }
    }


    public void removeSelectedItem() {
        removeObject(getSelectedItem());
        checkIfValid();
    }

    public void removeObject(Object object) {
        listView.getItems().remove(object);
    }

    public Object getSelectedItem() {
        return listView.getSelectionModel().getSelectedItem();
    }

    public List<T> getItems() {
        return listView.getItems();
    }


}

