package ihm.controls;

import common.Tools;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class DeepVBox extends VBox {
    public DeepVBox(boolean bordered){
        super();
        if(bordered)
            Tools.addBorder(this);
    }

    public void add(Region region){
        this.getChildren().add(region);
    }

}
