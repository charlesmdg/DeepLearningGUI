package ihm.controls;

import common.Tools;
import javafx.scene.control.TextField;


public class DeepTextField extends TextField {
    public DeepTextField(boolean editable){
        super();
        this.setEditable(editable);
        Tools.setStyle(this);
    }
}
