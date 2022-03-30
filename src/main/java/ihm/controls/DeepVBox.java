package ihm.controls;

import common.Tools;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class DeepVBox extends VBox {
    public DeepVBox(boolean bordered){
        super();
        if(bordered)
            Tools.addBorder(this);
    }
}
