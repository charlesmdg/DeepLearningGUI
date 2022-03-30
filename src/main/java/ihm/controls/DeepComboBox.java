package ihm.controls;

import common.Constants;
import common.Tools;
import javafx.scene.control.ComboBox;

public class DeepComboBox extends ComboBox<String> {
    public DeepComboBox(){
        super();
    }

    public DeepComboBox(String[] options){
        super();
        this.getItems().addAll(options);
        //Todo
        this.getEditor().setFont(Constants.NORMAL_FONT);
        Tools.setWidth(this, Constants.COMBOBOX_WIDTH);
        this.setEditable(false);
    }

}
