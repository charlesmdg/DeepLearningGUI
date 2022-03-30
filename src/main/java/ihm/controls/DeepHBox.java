package ihm.controls;

import common.Tools;
import javafx.scene.layout.HBox;

public class DeepHBox extends HBox {
    public DeepHBox(boolean bordered){
        super();
        if(bordered)
            Tools.addBorder(this);

    }
}
