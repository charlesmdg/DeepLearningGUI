package ihm.controls;

import common.Tools;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class DeepVBox extends VBox {
    public DeepVBox(boolean bordered) {
        super();
        Tools.addBorder(this, bordered);
    }

    public void add(Region region) {
        this.getChildren().add(region);
    }
}
