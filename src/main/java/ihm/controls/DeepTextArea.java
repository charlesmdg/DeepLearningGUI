package ihm.controls;

import common.Tools;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;


public class DeepTextArea extends TextArea {
    public DeepTextArea(){
        super();
        this.setEditable(false);
        this.textProperty().addListener((ChangeListener<Object>)
                (observable, oldValue, newValue) -> DeepTextArea.this.setScrollTop(Double.MAX_VALUE));
        Tools.setStyle(this);
    }
}
