package ihm.controls;

import common.Tools;
import javafx.scene.layout.Pane;

public class DeepPane extends Pane {
    public DeepPane(boolean bordered){
        super();
        if(bordered)
            Tools.addBorder(this);
    }
}
