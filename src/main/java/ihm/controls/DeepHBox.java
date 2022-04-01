package ihm.controls;

import common.Tools;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class DeepHBox extends HBox {
    public DeepHBox(boolean bordered){
        super();
        if(bordered)
            Tools.addBorder(this);
    }

    public void add(Region region){
        this.getChildren().add(region);
    }
}
