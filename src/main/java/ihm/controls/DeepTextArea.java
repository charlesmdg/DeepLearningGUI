package ihm.controls;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;


public class DeepTextArea extends TextArea {
    public DeepTextArea(){
        super();
        this.setEditable(false);
        this.textProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue,
                                Object newValue) {
                DeepTextArea.this.setScrollTop(Double.MAX_VALUE);
            }
        });
    }
}
