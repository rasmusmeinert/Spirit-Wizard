package GUI.Components.DynamicLabels;

import Model.Påfyldning;
import Model.Tapning;
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
            List<Tapning> liste = (List)object;

            if (liste.isEmpty()) {
                this.setText(labelName);
            }
            else {
                Påfyldning ældstePåfyldning = liste.get(0).getPåfyldning();
                for (Tapning tapning : liste) {
                    Påfyldning påfyldning = tapning.getPåfyldning();
                    if (påfyldning.getDato().getYear() > ældstePåfyldning.getDato().getYear()) {
                        ældstePåfyldning = påfyldning;
                    }
                }
                int alder = LocalDate.now().getYear() - ældstePåfyldning.getDato().getYear();
                this.setText(labelName + alder + " år");
            }
        }
    }
}
