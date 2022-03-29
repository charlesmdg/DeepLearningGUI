package ihm.controls;

import common.Constants;
import javafx.scene.control.ComboBox;

public class MComboBox extends ComboBox<String> {
    public MComboBox(){
        super();
    }

    public MComboBox(String[] options){
        super();
        this.getItems().addAll(options);
        //Todo
        this.getEditor().setFont(Constants.NORMAL_FONT);
        this.setEditable(true);
    }

}
