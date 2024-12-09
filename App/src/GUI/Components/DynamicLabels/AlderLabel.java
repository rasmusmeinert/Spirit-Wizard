package GUI.Components.DynamicLabels;

import Model.Påfyldning;
import com.sun.javafx.collections.ObservableListWrapper;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public class AlderLabel extends DynamicLabel{

    public AlderLabel(String text) {
        super(text);
    }

    @Override
    public void update(Object object) {
        if (object.getClass().equals(ObservableListWrapper.class)) {
            List<Påfyldning> liste = (List)object;
            if (liste.isEmpty()) {
                this.setText(labelName);
            }
            else {
                Påfyldning ældstePåfyldning = liste.get(0);
                for (Object o : liste) {
                    Påfyldning påfyldning = (Påfyldning) o;
                    if (((Påfyldning) o).getDato().getYear() > ældstePåfyldning.getDato().getYear()) {
                        ældstePåfyldning = (Påfyldning) o;
                    }
                }
                int alder = LocalDate.now().getYear() - ældstePåfyldning.getDato().getYear();
                this.setText(labelName + alder + " år");
            }
        }
    }
}
